package src.model.dao;
import src.model.dto.Account;
import java.sql.Connection;
import java.sql.SQLException;

import src.Context.AppContext;

public class AccountDAO {
    private static final Connection conn = AppContext.getConnection();
    private static AccountDAO accountDao;

    private AccountDAO() {

    }

public static AccountDAO getInstance(){
    if(accountDao==null){
        accountDao=new AccountDAO();
    }
    return accountDao;
}

public void createAccount(Account account)throws SQLException{
        String sql="INSERT INTO account(account_number, customer_id, branch_id, account_type_id, balance, status) VALUES (?, ?, ?, ?, ?, ?)";
}
}
