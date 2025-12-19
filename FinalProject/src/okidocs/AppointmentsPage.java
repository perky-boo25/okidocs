package okidocs;

import java.awt.*;
import javax.swing.*;

public class AppointmentsPage extends JPanel {

    private final JComboBox<String> typeDropdown;
    private final JTextField studentIdField;
    private final JSpinner dateSpinner;
    private final JComboBox<String> timeDropdown;

    public AppointmentsPage(MainApp app) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // HEADER
        add(new HeaderPanel(app, app::showHomePage), BorderLayout.NORTH);

        // CENTER PANEL
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(20, 250, 20, 250));

        // Dropdown: Type
        typeDropdown = new JComboBox<>(new String[]{
                "General Check-up",
                "Dental Check-up"
        });
        typeDropdown.setMaximumSize(new Dimension(300, 40));
        typeDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Student ID field (display only – login is via Session)
        studentIdField = new JTextField();
        studentIdField.setBorder(BorderFactory.createTitledBorder("Student ID"));
        studentIdField.setMaximumSize(new Dimension(300, 50));
        studentIdField.setAlignmentX(Component.CENTER_ALIGNMENT);
        studentIdField.setEditable(true);

        // Date spinner
        dateSpinner = new JSpinner(new SpinnerDateModel(
                new java.util.Date(),
                null,
                null,
                java.util.Calendar.DAY_OF_MONTH
        ));

        JSpinner.DateEditor editor =
                new JSpinner.DateEditor(dateSpinner, "dd-MM-yyyy");
        dateSpinner.setEditor(editor);

        dateSpinner.setMaximumSize(new Dimension(300, 40));
        dateSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Time dropdown
        timeDropdown = new JComboBox<>(new String[]{
                "08:00 AM", "08:30 AM",
                "09:00 AM", "09:30 AM",
                "10:00 AM", "10:30 AM",
                "11:00 AM",
                "01:00 PM", "01:30 PM",
                "02:00 PM", "02:30 PM",
                "03:00 PM", "03:30 PM",
                "04:00 PM", "04:30 PM"
        });
        timeDropdown.setMaximumSize(new Dimension(300, 40));
        timeDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Note
        JLabel note = new JLabel("HSU has lunch break at 11:30 AM to 1:00 PM");
        note.setForeground(Color.RED);
        note.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Submit button
        JButton submit = new JButton("Oki");
        submit.setBackground(new Color(98, 0, 238));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Segoe UI", Font.BOLD, 16));
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.setMaximumSize(new Dimension(120, 40));

        submit.addActionListener(e -> handleSubmit());

        // Add components
        center.add(Box.createVerticalGlue());
        center.add(makeLabel("Appointment Type:"));
        center.add(typeDropdown);
        center.add(Box.createVerticalStrut(15));
        center.add(studentIdField);
        center.add(Box.createVerticalStrut(15));
        center.add(makeLabel("Date (DD-MM-YYYY):"));
        center.add(dateSpinner);
        center.add(Box.createVerticalStrut(15));
        center.add(makeLabel("Time:"));
        center.add(timeDropdown);
        center.add(Box.createVerticalStrut(15));
        center.add(note);
        center.add(Box.createVerticalStrut(20));
        center.add(submit);
        center.add(Box.createVerticalGlue());

        add(center, BorderLayout.CENTER);
    }

    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }

    private void handleSubmit() {

        int studentId = Session.getStudentId();

        if (studentId == -1) {
            JOptionPane.showMessageDialog(this, "Please login first.");
            return;
        }

        java.util.Date selectedDate = (java.util.Date) dateSpinner.getValue();
        String timeText = (String) timeDropdown.getSelectedItem();

        java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
        String timeSlot = convertTime(timeText);

        // Check slot first
        if (AppointmentDAO.isSlotTaken(sqlDate, timeSlot)) {
            JOptionPane.showMessageDialog(this, "Time slot already taken.");
            return;
        }

        boolean booked = AppointmentDAO.bookAppointment(
                studentId,
                sqlDate,
                timeSlot
        );

        JOptionPane.showMessageDialog(
                this,
                booked ? "Appointment booked!" : "Booking failed."
        );
    }

    private String convertTime(String time) {
        return switch (time) {
            case "08:00 AM" -> "08:00";
            case "08:30 AM" -> "08:30";
            case "09:00 AM" -> "09:00";
            case "09:30 AM" -> "09:30";
            case "10:00 AM" -> "10:00";
            case "10:30 AM" -> "10:30";
            case "11:00 AM" -> "11:00";
            case "01:00 PM" -> "13:00";
            case "01:30 PM" -> "13:30";
            case "02:00 PM" -> "14:00";
            case "02:30 PM" -> "14:30";
            case "03:00 PM" -> "15:00";
            case "03:30 PM" -> "15:30";
            case "04:00 PM" -> "16:00";
            case "04:30 PM" -> "16:30";
            default -> "08:00";
        };
    }
}
