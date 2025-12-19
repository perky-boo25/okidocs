package okidocs;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewResults {

    public static void show(Component parent) {

        int studentId = Session.getStudentId();

        if (studentId == -1) {
            JOptionPane.showMessageDialog(parent, "Please login first.");
            return;
        }

        StudentInfo info = StudentInfoDAO.getStudentInfo(studentId);

        //FALLBACK: FAKE STUDENT
        if (info == null) {
            info = FakeStudentInfo.get(studentId);
        }

        List<MedicalResult> results = TestResultDAO.getResults(studentId);

        //FALLBACK TO FAKE DATA IF DB IS EMPTY
        if(results.isEmpty()) {
            results = FakeMedicalResults.getResults();
        }

        JPanel container = new JPanel(new BorderLayout(10,10));
        JTextArea header = new JTextArea(
            "Name: " + info.getName() + "\n" +
            "Student ID: " + info.getStudentId() + 
            "\nDate Released: " + info.getDateReleased()
        );

        header.setEditable(false);
        header.setBackground(container.getBackground());
        header.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        header.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        container.add(header, BorderLayout.NORTH);

        String[] columns = {"Test Name", "Category", "Result", "Normal Range", "Remarks", "Doctor Remark", "Date Released"};

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // READ-ONLY TABLE
            }
        };

        for (MedicalResult r : results) {
            model.addRow(new Object[]{
                    r.getTestName(),
                    r.getCategory(),
                    r.getResultValue(),
                    r.getNormalRange(),
                    r.getRemarks(),
                    r.getDoctorRemark(),
                    r.getDateReleased()
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(24);
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(750, 350));

        container.add(tableScroll, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(
                parent,
                container,
                "Medical Test Results",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
