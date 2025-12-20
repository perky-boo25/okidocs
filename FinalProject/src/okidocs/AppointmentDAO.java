package okidocs;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
            e.printStackTrace();
            return false;
        }
    }

    // ⭐ BUSINESS RULES LIVE HERE
    public static boolean isSlotTaken(Date date, String timeSlot) {

        LocalDate selectedDate = date.toLocalDate();
        LocalDate today = LocalDate.now();

        // 1️⃣ Past dates → automatically taken
        if (selectedDate.isBefore(today)) {
            return true;
        }

        // 2️⃣ Today → check if time slot already passed
        if (selectedDate.isEqual(today)) {

            LocalTime now = LocalTime.now();
            LocalTime slotTime = parseTimeSlot(timeSlot);

            if (slotTime != null && slotTime.isBefore(now)) {
                return true; // past time today → taken
            }
        }

        // 3️⃣ Otherwise → check DB
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
            return true; // fail-safe
        }
    }

    // 🔧 Helper method to extract time
    private static LocalTime parseTimeSlot(String timeSlot) {
        try {
            // Handles "08:00" or "08:00–09:00"
            String start = timeSlot.split("[-–]")[0].trim();
            return LocalTime.parse(start);
        } catch (Exception e) {
            return null; // invalid format → ignore time check
        }
    }
}
