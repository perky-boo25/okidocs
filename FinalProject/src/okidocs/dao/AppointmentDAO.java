package okidocs.dao;

import model.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO extends DBConnection {

    public AppointmentDAO() {
        super(); // calls DatabaseConnection constructor
    }

    // Add a new appointment
    public void addAppointment(Appointment appt) {
        try {
            String sql = "INSERT INTO appointments(studentID, date, timeslot, status) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, appt.getstudentID());
            pst.setDate(2, appt.getDate());
            pst.setString(3, appt.getTimeslot());
            pst.setString(4, appt.getStatus());
            pst.executeUpdate();
            System.out.println("Appointment added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update appointment status
    public void updateStatus(int appointmentID, String status) {
        try {
            String sql = "UPDATE appointments SET status = ? WHERE appointmentID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, status);
            pst.setInt(2, appointmentID);
            pst.executeUpdate();
            System.out.println("Appointment status updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete appointment
    public void deleteAppointment(int appointmentID) {
        try {
            String sql = "DELETE FROM appointments WHERE appointmentID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, appointmentID);
            pst.executeUpdate();
            System.out.println("Appointment deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fetch all appointments for a student
    public List<Appointment> getAppointmentsByStudent(int studentID) {
        List<Appointment> appointments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM appointments WHERE studentID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, studentID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("appointmentID");
                java.sql.Date date = rs.getDate("date");
                String timeslot = rs.getString("timeslot");
                String status = rs.getString("status");

                appointments.add(new Appointment(id, studentID, date, timeslot, status));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Optional: Fetch all appointments (for staff)
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("appointmentID");
                int studentID = rs.getInt("studentID");
                java.sql.Date date = rs.getDate("date");
                String timeslot = rs.getString("timeslot");
                String status = rs.getString("status");

                appointments.add(new Appointment(appointmentID, studentID, date, timeslot, status));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointments;
    }
}
