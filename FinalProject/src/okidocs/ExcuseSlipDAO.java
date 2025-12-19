package okidocs;

import java.sql.*;

public class ExcuseSlipDAO {

    public static boolean submitSlip(
            int studentId,
            String submissionType,
            String pdfPath
    ) {

        String sql = """
            INSERT INTO submission
            (student_id, submission_type, pdf_path)
            VALUES (?, ?, ?)
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setString(2, submissionType);
            ps.setString(3, pdfPath);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
