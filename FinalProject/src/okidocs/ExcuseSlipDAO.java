package okidocs;

import java.sql.*;

public class ExcuseSlipDAO {

    public static boolean submit(int studentId, Date date,
                                 String type, String path) {

        String sql = """
            INSERT INTO excuse_slips
            (student_id, date_absent, doc_type, pdf_path)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setDate(2, date);
            ps.setString(3, type);
            ps.setString(4, path);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }
}
