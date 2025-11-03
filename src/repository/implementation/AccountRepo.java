package src.repository.implementation;
import src.repository.interfaces.RepoInterface;
import src.repository.enums.QueryEnum;
import src.model.dto.Account;
import src.util.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepo implements RepoInterface<Account> {

    private final Connection conn;

    public AccountRepo() {
        this.conn = DBConfig.getConnection();
    }
    @Override
public boolean add(Account account) {
    try (PreparedStatement stmt = conn.prepareStatement(QueryEnum.INSERT_ACCOUNT.getQuery())) {
        stmt.setString(1, account.getAccountNumber());
        stmt.setInt(2, account.getCustomerId());
        stmt.setInt(3, account.getBranchId());
        stmt.setInt(4, account.getAccountTypeId());
        stmt.setDouble(5, account.getBalance());
        stmt.setTimestamp(6, account.getCreatedAt());
        stmt.setString(7, account.getStatus());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


 @Override
public Account getById(int id) {
    try (PreparedStatement stmt = conn.prepareStatement(QueryEnum.SELECT_ACCOUNT_BY_ID.getQuery())) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Account(
                rs.getInt("account_id"),
                rs.getString("account_number"),
                rs.getInt("customer_id"),
                rs.getInt("branch_id"),
                rs.getInt("account_type_id"),
                rs.getDouble("balance"),
                rs.getTimestamp("created_at"),
                rs.getString("status")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

@Override
public boolean update(Account account) {
    try (PreparedStatement stmt = conn.prepareStatement(QueryEnum.UPDATE_ACCOUNT.getQuery())) {
        stmt.setDouble(1, account.getBalance());
        stmt.setInt(2, account.getAccountTypeId());
        stmt.setString(3, account.getStatus());
        stmt.setInt(4, account.getAccountId());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

@Override
public boolean deleteById(int id) {
    try (PreparedStatement stmt = conn.prepareStatement(QueryEnum.DELETE_ACCOUNT.getQuery())) {
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



@Override
public List<Account> getAll() {
    List<Account> list = new ArrayList<>();
    try (PreparedStatement stmt = conn.prepareStatement(QueryEnum.SELECT_ALL_ACCOUNTS.getQuery())) {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(new Account(
                rs.getInt("account_id"),
                rs.getString("account_number"),
                rs.getInt("customer_id"),
                rs.getInt("branch_id"),
                rs.getInt("account_type_id"),
                rs.getDouble("balance"),
                rs.getTimestamp("created_at"),
                rs.getString("status")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

}