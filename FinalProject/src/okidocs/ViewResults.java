package okidocs;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewResults {

    // Entry point called from button
    public static void show(Component parent) {

        int studentId = Session.getStudentId();

        // Check login
        if (studentId == -1) {
            JOptionPane.showMessageDialog(parent, "Please login first.");
            return;
        }

        // TEMP: request check (replace later)
        if (!TestResultDAO.hasRequested(studentId)) {
            JOptionPane.showMessageDialog(parent,
                    "You have not requested your test results yet.");
            return;
        }

        // TEMP: release check (replace later)
        if (!TestResultDAO.isReleased(studentId)) {
            JOptionPane.showMessageDialog(parent,
                    "Results will be available after 5–10 business days.");
            return;
        }

        // REAL student info from DB
        StudentInfo info = StudentInfoDAO.getStudentInfo(studentId);

        if (info == null) {
            JOptionPane.showMessageDialog(parent, "Student information not found.");
            return;
        }

        // Main container
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(createHeader(info));
        content.add(createSection("Vital Signs", "VITALS"));
        content.add(createSection("Complete Blood Count", "CBC"));
        content.add(createSection("Urinalysis", "URINALYSIS"));
        content.add(createSection("Chest X-Ray", "XRAY"));
        content.add(createDoctorRemark());

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setPreferredSize(new Dimension(700, 500));

        JOptionPane.showMessageDialog(
                parent,
                scrollPane,
                "Medical Test Results",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Student header info
    private static JPanel createHeader(StudentInfo info) {

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Name: " + info.getName()));
        panel.add(new JLabel("Student ID: " + info.getStudentId()));
        panel.add(new JLabel("Date Requested: " + info.getDateRequested()));
        panel.add(new JLabel("Date Released: " + info.getDateReleased()));
        panel.add(new JLabel("Doctor: Dr. Samaniego"));

        return panel;
    }

    // Section wrapper
    private static JPanel createSection(String title, String category) {

        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(title);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));

        section.add(label);
        section.add(createTable(category));

        return section;
    }

    // Medical results table
    private static JScrollPane createTable(String category) {

        List<MedicalResult> list = FakeMedicalResults.getResults(category);

        String[] columns = {"Test", "Result", "Normal Range", "Remarks"};
        Object[][] data = new Object[list.size()][4];

        for (int i = 0; i < list.size(); i++) {
            MedicalResult r = list.get(i);
            data[i][0] = r.getTestName();
            data[i][1] = r.getResultValue();
            data[i][2] = r.getNormalRange();
            data[i][3] = r.getRemarks();
        }

        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.setRowHeight(22);

        return new JScrollPane(table);
    }

    // Doctor remark section
    private static JTextArea createDoctorRemark() {

        JTextArea area = new JTextArea(
                "Doctor's Remark:\n" +
                "Physically fit for school activities.\n\n" +
                "— Dr. Samaniego"
        );

        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return area;
    }
}
