package src.Context;
import java.sql.Connection;

import src.model.dao.AccountDAO;
import src.util.DBConfig;
public class AppContext {
    private AppContext()
    {

    }
    //dbConnection
    public static Connection getConnection(){
        return DBConfig.getInstance();
    }
    public static AccountDAO getAccountDAO()
    {
        return AccountDAO.getInstance();

    }
}
