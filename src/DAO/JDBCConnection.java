
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
    public static Connection getJDBCConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QuanLyQuanCafeJava;username=sa; password=sa");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
