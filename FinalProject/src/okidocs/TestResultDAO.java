package okidocs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestResultDAO {

    public static boolean hasResults(int studentId) {

        String sql = """
            SELECT test_id
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
            SELECT test_type, result, date_released
            FROM test_results
            WHERE student_id = ?
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new MedicalResult(
                    rs.getString("test_type"),
                    rs.getString("result"),
                    rs.getDate("date_released")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
