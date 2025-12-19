package okidocs;

import java.sql.*;

public class StudentDAO {

    public static boolean validateLogin(int studentId, String password) {

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
            e.printStackTrace();
            return false;
        }
    }

    public static String getStudentName(int studentId) {

        String sql = "SELECT student_name FROM students WHERE student_id = ?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("student_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
