package okidocs;

import java.sql.*;

public class AppointmentDAO {

    public static boolean bookAppointment(
            int studentId, Date date, Time time) {

        String sql = """
            INSERT INTO appointments
            (student_id, appointment_date, appointment_time)
            VALUES (?, ?, ?,)
        """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setDate(2, date);
            ps.setTime(3, time);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
