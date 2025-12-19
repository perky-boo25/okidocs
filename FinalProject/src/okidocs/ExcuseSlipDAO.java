package okidocs;

import java.sql.*;

public class ExcuseSlipDAO {

    public static boolean submitSlip(
            int studentId,
            Date absentDate,
            String pdfPath) {

        String sql = """
            INSERT INTO excuse_slips
            (student_id, date_absent, status, pdf_path)
            VALUES (?, ?, 'PENDING', ?)
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setDate(2, absentDate);
            ps.setString(3, pdfPath);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }
}
