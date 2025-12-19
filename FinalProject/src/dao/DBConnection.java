package okidocs.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    protected Connection conn;

    public DBConnection() {
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/okidocs?serverTimezone=UTC",
                "root",
                "YOUR_PASSWORD"
            );
            System.out.println("Database connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
