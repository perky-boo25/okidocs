package okidocs;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class ViewResults {

    public static void show(Component parent) {

        int studentId = Session.getStudentId();

        if (studentId == -1) {
            JOptionPane.showMessageDialog(parent, "Please login first.");
            return;
        }

        if (!TestResultDAO.hasResults(studentId)) {
            JOptionPane.showMessageDialog(
                    parent,
                    "No test results available yet."
            );
            return;
        }

        StudentInfo info = StudentInfoDAO.getStudentInfo(studentId);

        if (info == null) {
            JOptionPane.showMessageDialog(parent, "Student information not found.");
            return;
        }

        List<MedicalResult> results = TestResultDAO.getResults(studentId);

        //FALLBACK TO FAKE DATA IF DB IS EMPTY
        if(results.isEmpty()) {
            results = FakeMedicalResults.getResults();
        }

        JTextArea area = new JTextArea();
        area.setEditable(false);

        area.append("Name: " + info.getName() + "\n");
        area.append("Student ID: " + info.getStudentId() + "\n");
        area.append("Date Released: " + info.getDateReleased() + "\n\n");

        for (MedicalResult r : results) {
            area.append(r.toString() + "\n\n");
        }

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(700, 500));

        JOptionPane.showMessageDialog(
                parent,
                scroll,
                "Medical Test Results",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
