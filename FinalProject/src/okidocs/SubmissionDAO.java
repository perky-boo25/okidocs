package okidocs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubmissionDAO {

    public static boolean submitFile(
            String studentNum,
            String fileName,
            String filePath,
            String type) {

        String sql = """
            INSERT INTO submissions
            (student_number, file_name, file_path, submission_type)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentNum);
            ps.setString(2, fileName);
            ps.setString(3, filePath);
            ps.setString(4, type);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
