package okidocs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class AppointmentDAO {

    public static boolean bookAppointment(
            String studentNum,
            Date date,
            Time time) {

        String sql = """
            INSERT INTO appointments
            (student_number, appointment_date, appointment_time)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentNum);
            ps.setDate(2, date);
            ps.setTime(3, time);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // slot already taken (unique constraint)
            return false;
        }
    }
}
