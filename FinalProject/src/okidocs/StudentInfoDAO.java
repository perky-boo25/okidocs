package okidocs;

import java.sql.*;

public class StudentInfoDAO {

    public static StudentInfo getStudentInfo(int studentId) {

        String sql = """
            SELECT s.student_name,
                   MIN(t.date_released) AS requested,
                   MAX(t.date_released) AS released
            FROM students s
            LEFT JOIN test_results t ON s.student_id = t.student_id
            WHERE s.student_id = ?
            GROUP BY s.student_id
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new StudentInfo(
                    rs.getString("student_name"),
                    studentId,
                    rs.getDate("requested"),
                    rs.getDate("released")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
