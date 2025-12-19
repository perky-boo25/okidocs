package okidocs;

import java.sql.*;

public class AppointmentDAO {

    public static boolean bookAppointment(
            int studentId, Date date, String timeSlot) {

        String sql = """
            INSERT INTO appointments
            (student_id, date, time_slot, status)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setDate(2, date);
            ps.setString(3, timeSlot);
            ps.setString(4, "BOOKED");

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // duplicate slot / FK failure ends here
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isSlotTaken(Date date, String timeSlot) {

        String sql = """
            SELECT appointment_id
            FROM appointments
            WHERE date = ? AND time_slot = ?
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, date);
            ps.setString(2, timeSlot);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}
