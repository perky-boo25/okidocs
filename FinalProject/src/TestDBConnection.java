import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/okidocs",
                "root",
                "mySQLpassword123"
            );
            System.out.println("CONNECTED TO MYSQL");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
