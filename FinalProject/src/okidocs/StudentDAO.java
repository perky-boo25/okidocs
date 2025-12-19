package okidocs;

import java.sql.*;

public class StudentDAO {

    // ===============================
    // SIGN UP
    // ===============================
    public static boolean register(
            int studentId,
            String name,
            String password
    ) {

        String sql = """
            INSERT INTO students (student_id, student_name, acc_password)
            VALUES (?, ?, ?)
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // Duplicate student_id or DB error
            return false;
        }
    }

    // ===============================
    // LOG IN
    // ===============================
    public static boolean validateLogin(
            int studentId,
            String password
    ) {

        String sql = """
            SELECT student_id
            FROM students
            WHERE student_id = ? AND acc_password = ?
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            return false;
        }
    }
}
