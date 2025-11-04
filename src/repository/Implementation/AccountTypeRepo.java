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
import enums.AccountTypeQuery;
import exceptions.NotFoundException;
import models.AccountType;
import repository.RepoInterface;

public class AccountTypeRepo implements RepoInterface<AccountType> {

    private static AccountTypeRepo instance;

    private final Map<Integer, AccountType> accountTypes = new ConcurrentHashMap<>();
    private final Set<Integer> unUpdateAccountTypeIds = new ConcurrentHashMap<Integer, Boolean>().newKeySet();

    private AccountTypeRepo() {}

    public static AccountTypeRepo getInstance() {
        if (instance == null) {
            instance = new AccountTypeRepo();
        }
        return instance;
    }

    @Override
    public void loadAll() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(AccountTypeQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AccountType at = new AccountType();
                at.setAccountTypeId(rs.getInt("account_type_id"));
                at.setTypeName(rs.getString("type_name"));
                at.setMinBalance(rs.getBigDecimal("min_balance"));
                at.setInterestRate(rs.getBigDecimal("interest_rate"));

                accountTypes.put(at.getAccountTypeId(), at);
            }

        } catch (SQLException e) {
            System.out.println("Error loading Account Types: " + e.getMessage());
        }
    }

    @Override
    public List<AccountType> getAll() {
        return new ArrayList<>(accountTypes.values());
    }

    @Override
    public AccountType getById(int id) {
        AccountType at = accountTypes.get(id);
        if (at == null) throw new NotFoundException("Account Type not found with ID: " + id);
        return at;
    }

    @Override
    public void add(AccountType at) {
        int id = accountTypes.size() + 1;
        at.setAccountTypeId(id);
        addOrUpdate(at);
    }

    @Override
    public void update(AccountType at) {
        addOrUpdate(at);
    }

    private void addOrUpdate(AccountType at) {
        accountTypes.put(at.getAccountTypeId(), at);
        unUpdateAccountTypeIds.add(at.getAccountTypeId());
    }

    @Override
    public void deleteById(int id) {
        accountTypes.remove(id);
        unUpdateAccountTypeIds.add(id);
    }

    @Override
    public void syncChanges() {
        for (int id : unUpdateAccountTypeIds) {
            AccountType at = getById(id);
            try (Connection conn = DBConnection.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(AccountTypeQuery.INSERT.getQuery())) {

                ps.setInt(1, at.getAccountTypeId());
                ps.setString(2, at.getTypeName());
                ps.setBigDecimal(3, at.getMinBalance());
                ps.setBigDecimal(4, at.getInterestRate());

                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error syncing Account Type: " + e.getMessage());
            }
        }
    }
}
