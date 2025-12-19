package okidocs;

import java.sql.*;

public class ExcuseSlipDAO {

    public static boolean submitSlip(
            int studentId, Date dateAbsent, String pdfPath) {

        String sql = """
            INSERT INTO excuse_slips
            (student_id, date_absent, status, pdf_path)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setDate(2, dateAbsent);
            ps.setString(3, "PENDING");
            ps.setString(4, pdfPath);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
