package okidocs;

import java.awt.*;
import javax.swing.*;

public class AppointmentsPage extends JPanel {

    private JComboBox<String> typeDropdown;
    private JTextField nameField;
    private JTextField dateField;
    private JComboBox<String> timeDropdown;

    public AppointmentsPage(MainApp app) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // HEADER
        add(createHeaderBar(app), BorderLayout.NORTH);

        // CENTER PANEL (uses BoxLayout to center everything)
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

        // Name field
        nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Name"));
        nameField.setMaximumSize(new Dimension(300, 50));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Date (YYYY-MM-DD)
        dateField = new JTextField();
        dateField.setBorder(BorderFactory.createTitledBorder("Date (YYYY-MM-DD)"));
        dateField.setMaximumSize(new Dimension(300, 50));
        dateField.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        // Add components centered
        center.add(Box.createVerticalGlue());
        center.add(makeLabel("Appointment Type:"));
        center.add(typeDropdown);
        center.add(Box.createVerticalStrut(15));
        center.add(nameField);
        center.add(Box.createVerticalStrut(15));
        center.add(makeLabel("Date:"));
        center.add(dateField);
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

    // HEADER (NO DECOMPILE COMMENT)
    private JPanel createHeaderBar(MainApp app) {

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 184, 28));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Load image (simple, reliable path)
        ImageIcon icon = new ImageIcon("src/okidocs/notOkay.png");
        Image scaled = icon.getImage().getScaledInstance(160, 50, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));

        header.add(logo, BorderLayout.WEST);

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(new Color(110, 9, 38));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> app.showHomePage());

        header.add(backBtn, BorderLayout.EAST);

        return header;
    }

    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }

    private void handleSubmit() {

        String dateText = dateField.getText().trim();
        String timeText = (String) timeDropdown.getSelectedItem();

        if (dateText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.");
            return;
        }

        int studentId = Session.getStudentId();

        if(studentId == -1){
            JOptionPane.showMessageDialog(this, "Please login first.");
            return;
        }

        try {
            //Convert to SQL Date and Time
            java.sql.Date date = java.sql.Date.valueOf(dateText);
            java.sql.Time time = java.sql.Time.valueOf(convertTime(timeText));
            

            boolean booked = AppointmentDAO.bookAppointment(
                studentId, date, time
        );

        if (booked) {
            JOptionPane.showMessageDialog(this, "Appointment booked!");
        } else {
            JOptionPane.showMessageDialog(this, "Time slot already taken.");
        }

    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, "Invalid date format.");
    }
}

    private String convertTime(String time) {
    return switch (time) {
        case "08:00 AM" -> "08:00:00";
        case "08:30 AM" -> "08:30:00";
        case "09:00 AM" -> "09:00:00";
        case "09:30 AM" -> "09:30:00";
        case "10:00 AM" -> "10:00:00";
        case "10:30 AM" -> "10:30:00";
        case "11:00 AM" -> "11:00:00";
        case "01:00 PM" -> "13:00:00";
        case "01:30 PM" -> "13:30:00";
        case "02:00 PM" -> "14:00:00";
        case "02:30 PM" -> "14:30:00";
        case "03:00 PM" -> "15:00:00";
        case "03:30 PM" -> "15:30:00";
        case "04:00 PM" -> "16:00:00";
        case "04:30 PM" -> "16:30:00";
        default -> "08:00:00";
    };
}

}