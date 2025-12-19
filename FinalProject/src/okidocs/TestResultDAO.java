package okidocs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestResultDAO {

    public static boolean hasResults(int studentId) {

        String sql = """
            SELECT 1
            FROM test_results
            WHERE student_id = ?
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<MedicalResult> getResults(int studentId) {

        List<MedicalResult> list = new ArrayList<>();

        String sql = """
            SELECT test_name, category, result_value,
                   normal_range, remarks, date_released, dr_remark
            FROM test_results
            WHERE student_id = ?
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new MedicalResult(
                        rs.getString("test_name"),
                        rs.getString("category"),
                        rs.getString("result_value"),
                        rs.getString("normal_range"),
                        rs.getString("remarks"),
                        rs.getDate("date_released"),
                        rs.getString("dr_remark")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
