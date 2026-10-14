package vn.fit.de05.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/CompanyDB";
    private static final String USER = "root";
    private static final String PASS = "123456";
    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL,USER,PASS);
    }
}
