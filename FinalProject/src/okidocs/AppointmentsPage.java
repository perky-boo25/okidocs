package okidocs;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppointmentsPage extends JPanel {

    private final JComboBox<String> appointmentType;
    private final JTextField nameField;
    private final JSpinner datePicker;
    private final JSpinner timePicker;

    public AppointmentsPage(MainApp app) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // HEADER
        add(createHeaderBar(app), BorderLayout.NORTH);

        // CENTER FORM PANEL
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));
        form.setBackground(Color.WHITE);

        // 1) APPOINTMENT TYPE DROPDOWN
        appointmentType = new JComboBox<>(new String[]{
                "General Check-up",
                "Dental Check-up"
        });
        styleComponent(appointmentType);

        // 2) NAME FIELD
        nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Name"));
        nameField.setMaximumSize(new Dimension(400, 50));

        // 3) DATE PICKER
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        datePicker = new JSpinner(dateModel);
        datePicker.setEditor(new JSpinner.DateEditor(datePicker, "yyyy-MM-dd"));
        datePicker.setMaximumSize(new Dimension(400, 50));

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // 4) TIME PICKER (30 minutes interval)
        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
        timePicker = new JSpinner(timeModel);
        timePicker.setEditor(new JSpinner.DateEditor(timePicker, "hh:mm a"));
        timePicker.setMaximumSize(new Dimension(400, 50));

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // 5) NOTE LABEL
        JLabel note = new JLabel("HSU has lunch break at 11:30 AM to 1:00 PM");
        note.setForeground(Color.RED);
        note.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 6) SUBMIT BUTTON
        JButton submit = new JButton("Oki");
        submit.setFont(new Font("Segoe UI", Font.BOLD, 18));
        submit.setBackground(new Color(98, 0, 238));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setMaximumSize(new Dimension(150, 45));
        submit.setAlignmentX(Component.LEFT_ALIGNMENT);

        submit.addActionListener(e -> handleSubmit());

        // ADD COMPONENTS TO FORM
        form.add(label("Appointment Type:"));
        form.add(appointmentType);
        form.add(Box.createVerticalStrut(15));

        form.add(nameField);
        form.add(Box.createVerticalStrut(15));

        form.add(dateLabel);
        form.add(datePicker);
        form.add(Box.createVerticalStrut(15));

        form.add(timeLabel);
        form.add(timePicker);
        form.add(Box.createVerticalStrut(15));

        form.add(note);
        form.add(Box.createVerticalStrut(30));

        form.add(submit);

        add(form, BorderLayout.CENTER);
    }

    private JPanel createHeaderBar(MainApp app) {

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 184, 28));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // LOGO
        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon("notOkay.png");   // your image
        Image scaled = icon.getImage().getScaledInstance(180, 60, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(scaled));

        header.add(logo, BorderLayout.WEST);

        // BACK BUTTON
        JButton backBtn = new JButton("Back");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(110, 9, 38));
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backBtn.addActionListener(e -> app.showHomePage());
        header.add(backBtn, BorderLayout.EAST);

        return header;
    }

    // LABEL HELPER
    private JLabel label(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    // STYLE HELPER
    private void styleComponent(JComponent c) {
        c.setMaximumSize(new Dimension(400, 50));
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    // ⭐ BACKEND HOOK FOR FUTURE DATABASE CONNECTION ⭐
    private boolean isAppointmentAvailable(Date datetime) {
        // TODO: check DB here

        // TEMPORARY SIMULATION:
        // For example, disallow 12 PM timeslot
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time = sdf.format(datetime);

        return !time.equals("12:00");  // fake unavailable time
    }

    private void handleSubmit() {

        String appt = (String) appointmentType.getSelectedItem();
        String name = nameField.getText();

        Date date = (Date) datePicker.getValue();
        Date time = (Date) timePicker.getValue();

        // COMBINE DATE + TIME
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        Calendar t = Calendar.getInstance();
        t.setTime(time);

        c.set(Calendar.HOUR_OF_DAY, t.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, t.get(Calendar.MINUTE));

        Date finalDateTime = c.getTime();

        // CHECK AVAILABILITY
        if (isAppointmentAvailable(finalDateTime)) {
            JOptionPane.showMessageDialog(this, "Appointment set!");
        } else {
            JOptionPane.showMessageDialog(this, "Time in specific date is not available");
        }
    }
}
