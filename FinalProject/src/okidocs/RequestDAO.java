package okidocs;

import java.sql.*;

public class RequestDAO {

    public static boolean submitRequest(int studentId, Date dateRequested) {

        String sql = """
            INSERT INTO requests
            (student_id, date_requested)
            VALUES (?, ?)
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setDate(2, dateRequested);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // Duplicate request or DB error
            e.printStackTrace();
            return false;
        }
    }
}
