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

        // CENTER WRAPPER (for perfect centering)
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(Color.WHITE);

        // FORM
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);

        // FORCE CENTER ALIGNMENT FOR ALL COMPONENTS
        form.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension fieldSize = new Dimension(350, 40);

        // APPOINTMENT TYPE
        JLabel lblType = makeLabel("Appointment Type:");
        lblType.setAlignmentX(Component.CENTER_ALIGNMENT);

        typeDropdown = new JComboBox<>(new String[]{
            "General Check-up",
            "Dental Check-up"
        });
        typeDropdown.setMaximumSize(fieldSize);
        typeDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // NAME FIELD
        nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Name"));
        nameField.setMaximumSize(fieldSize);
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // DATE FIELD
        dateField = new JTextField();
        dateField.setBorder(BorderFactory.createTitledBorder("Date (YYYY-MM-DD)"));
        dateField.setMaximumSize(fieldSize);
        dateField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TIME DROPDOWN (30-min intervals)
        timeDropdown = new JComboBox<>(new String[]{
            "08:00 AM", "08:30 AM",
            "09:00 AM", "09:30 AM",
            "10:00 AM", "10:30 AM",
            "11:00 AM",
            // lunch break (not allowed but left visible)
            "01:00 PM", "01:30 PM",
            "02:00 PM", "02:30 PM",
            "03:00 PM", "03:30 PM",
            "04:00 PM", "04:30 PM"
        });
        timeDropdown.setMaximumSize(fieldSize);
        timeDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // NOTE LABEL
        JLabel note = new JLabel("HSU has lunch break at 11:30 AM to 1:00 PM");
        note.setForeground(Color.RED);
        note.setAlignmentX(Component.CENTER_ALIGNMENT);

        // SUBMIT BUTTON
        JButton submit = new JButton("Oki");
        submit.setBackground(new Color(98, 0, 238));
        submit.setForeground(Color.WHITE);
        submit.setMaximumSize(new Dimension(120, 40));
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.addActionListener(e -> handleSubmit());

        // ADD COMPONENTS TO FORM
        form.add(lblType);
        form.add(typeDropdown);
        form.add(Box.createVerticalStrut(15));

        form.add(nameField);
        form.add(Box.createVerticalStrut(15));

        form.add(dateField);
        form.add(Box.createVerticalStrut(15));

        form.add(timeDropdown);
        form.add(Box.createVerticalStrut(15));

        form.add(note);
        form.add(Box.createVerticalStrut(20));

        form.add(submit);

        // ADD FORM TO CENTER WRAPPER
        wrapper.add(form);

        add(wrapper, BorderLayout.CENTER);
    }

    // HEADER BAR
    private JPanel createHeaderBar(MainApp app) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 184, 28));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel logo = new JLabel(new ImageIcon("notOkay.png"));
        header.add(logo, BorderLayout.WEST);

        JButton back = new JButton("Back");
        back.setBackground(new Color(110, 9, 38));
        back.setForeground(Color.WHITE);
        back.addActionListener(e -> app.showHomePage());
        header.add(back, BorderLayout.EAST);

        return header;
    }

    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }

    // EMPTY BACKEND METHOD FOR FUTURE CODERS
    private boolean isAvailable(String date, String time) {
        // TODO: Add MySQL logic here
        return true; // temporary placeholder
    }

    private void handleSubmit() {
        String name = nameField.getText().trim();
        String date = dateField.getText().trim();
        String time = (String) timeDropdown.getSelectedItem();

        if (name.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields!");
            return;
        }

        if (isAvailable(date, time)) {
            JOptionPane.showMessageDialog(this, "Appointment set!");
        } else {
            JOptionPane.showMessageDialog(this, "Time not available.");
        }
    }
}
