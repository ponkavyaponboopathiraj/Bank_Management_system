package src.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/ Bank_management_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to PostgreSQL database!");
            }
        } catch (Exception e) {
            System.out.println("Database connection failed" + e.getMessage());
        }
        return connection;
    }
    public static Connection getInstance()
    {
        return getInstance();
    }


}
