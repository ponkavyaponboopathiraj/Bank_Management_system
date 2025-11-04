package repository.Implementation;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import DBConfig.DBConnection;
import enums.TransactionQuery;
import exceptions.NotFoundException;
import models.Account;
import models.Transaction;
import repository.RepoInterface;

public class TransactionRepo implements RepoInterface<Transaction> {

    private static TransactionRepo instance;
    private final Map<Long, Transaction> transactions = new ConcurrentHashMap<>();
    private final Set<Long> unSyncedTransactionIds = ConcurrentHashMap.newKeySet();

    private TransactionRepo() {}

    public static TransactionRepo getInstance() {
        if (instance == null) {
            instance = new TransactionRepo();
        }
        return instance;
    }

    @Override
    public void loadAll() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(TransactionQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Account account = AccountRepo.getInstance().getById(rs.getInt("account_id"));

                Transaction t = new Transaction();
                t.setTransactionId(rs.getLong("transaction_id"));
                t.setAccount(account);
                t.setTransactionType(rs.getString("transaction_type"));
                t.setAmount(rs.getDouble("amount"));
                t.setDescription(rs.getString("description"));

                Timestamp ts = rs.getTimestamp("transaction_date");
                if (ts != null)
                    t.setTransactionDate(ts.toLocalDateTime());

                t.setBalanceAfter(rs.getDouble("balance_after"));
                t.setToAccount(rs.getString("to_account"));

                transactions.put(t.getTransactionId(), t);
            }

        } catch (SQLException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }

    @Override
    public List<Transaction> getAll() {
        return new ArrayList<>(transactions.values());
    }

    @Override
    public Transaction getById(int id) {
        Transaction t = transactions.get((long) id);
        if (t == null)
            throw new NotFoundException("Transaction not found for ID: " + id);
        return t;
    }

    @Override
    public void add(Transaction t) {
        transactions.put(t.getTransactionId(), t);
        unSyncedTransactionIds.add(t.getTransactionId());
    }

    @Override
    public void update(Transaction t) {
        transactions.put(t.getTransactionId(), t);
        unSyncedTransactionIds.add(t.getTransactionId());
    }

    @Override
    public void deleteById(int id) {
        transactions.remove((long) id);
        unSyncedTransactionIds.add((long) id);
    }

    public void syncChanges() {
        for (long id : unSyncedTransactionIds) {
            Transaction t = getById((int) id);

            try (Connection conn = DBConnection.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(TransactionQuery.INSERT.getQuery())) {

                ps.setInt(1, t.getAccount().getAccountId());
                ps.setString(2, t.getTransactionType());
                ps.setDouble(3, t.getAmount());
                ps.setString(4, t.getDescription());
                ps.setTimestamp(5, Timestamp.valueOf(t.getTransactionDate()));
                ps.setDouble(6, t.getBalanceAfter());
                ps.setString(7, t.getToAccount());
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error syncing transaction: " + e.getMessage());
            }
        }
    }
}
