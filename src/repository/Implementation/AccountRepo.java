package repository.Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import DBConfig.DBConnection;
import enums.AccountQuery;
import exceptions.NotFoundException;
import models.Account;
import models.AccountType;
import models.Branch;
import models.Customer;
import repository.RepoInterface;

public class AccountRepo implements RepoInterface<Account> {

    private static AccountRepo instance;

    private AccountRepo() {}

    public static AccountRepo getInstance() {
        if (instance == null) {
            instance = new AccountRepo();
        }
        return instance;
    }

    private final Map<Integer, Account> accounts = new ConcurrentHashMap<>();
    private final Set<Integer> unSyncedIds = ConcurrentHashMap.newKeySet();

    @Override
    public void loadAll() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(AccountQuery.LOADALL.getQuery())) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                try {
                    AccountType accountType = AccountTypeRepo.getInstance().getById(rs.getInt("account_type_id"));
                    Branch branch = BranchRepo.getInstance().getById(rs.getInt("branch_id"));
                    Customer customer = CustomerRepo.getInstance().getById(rs.getInt("customer_id"));

                    Account account = new Account();
                    account.setAccountId(rs.getInt("account_id"));
                    account.setAccountNumber(rs.getString("account_number"));
                    account.setBalance(rs.getDouble("balance"));
                    account.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    account.setStatus(rs.getString("is_active"));
                    account.setAccountType(accountType);
                    account.setBranch(branch);
                    account.setCustomer(customer);

                    accounts.put(account.getAccountId(), account);

                } catch (NotFoundException e) {
                    System.out.println("Account load failed: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    @Override
    public List<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public void add(Account account) {
        int id = accounts.size() + 1;
        account.setAccountId(id);
        addOrUpdate(account);
    }

    @Override
    public void update(Account account) {
        addOrUpdate(account);
    }

    private void addOrUpdate(Account account) {
        accounts.put(account.getAccountId(), account);
        unSyncedIds.add(account.getAccountId());
    }

    @Override
    public void deleteById(int id) {
        accounts.remove(id);
        unSyncedIds.add(id);
    }

    public Account getById(int id) {
        Account account = accounts.get(id);
        if (account == null) {
            throw new NotFoundException("Account not found with ID: " + id);
        }
        return account;
    }

    @Override
    public void syncChanges() {
        if (unSyncedIds.isEmpty()) {
            return;
        }

        try (Connection conn = DBConnection.getInstance().getConnection()) {
            for (int id : unSyncedIds) {
                Account account = accounts.get(id);
                if (account == null) continue;

                try (PreparedStatement ps = conn.prepareStatement(AccountQuery.INSERT.getQuery())) {
                    ps.setInt(1, account.getAccountId());
                    ps.setString(2, account.getAccountNumber());
                    ps.setDouble(3, account.getBalance());
                    ps.setInt(4, account.getAccountType().getAccountTypeId());
                    ps.setInt(5, account.getBranch().getBranchId());
                    ps.setInt(6, account.getCustomer().getUser().getUserId());
                    ps.setString(7, account.getStatus());
                    ps.setTimestamp(8, java.sql.Timestamp.valueOf(account.getCreatedAt()));

                    ps.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error syncing account changes: " + e.getMessage());
        }

        unSyncedIds.clear();
    }
}
