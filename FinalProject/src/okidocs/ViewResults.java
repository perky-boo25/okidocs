package okidocs;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewResults {
    
    public static void show(JPanel parent){

        add(new HeaderPanel(app, app::showHomePage), BorderLayout.NORTH);

        int studentId = Session.getStudentId();

        if (studentId == -1){
            JOptionPane.showMessageDialog(parent, "Please login first.");
            return;
        }

        //Temp: To be changed
        if (!TestResultDAO.hasRequested(studentId)) {
            JOptionPane.showMessageDialog(parent,
                    "You have not requested your test results yet.");
            return;
        }

        //TEMP: To be changed
        if (!TestResultDAO.isReleased(studentId)) {
            JOptionPane.showMessageDialog(parent,
                    "Results will be available after 5–10 business days.");
            return;
        }

        StudentInfo info = TestResultDAO.getStudentInfo(studentId);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(createHeader(info));
        content.add(createSection("Vital Signs", "VITALS"));
        content.add(createSection("CBC", "CBC"));
        content.add(createSection("Urinalysis", "URINALYSIS"));
        content.add(createSection("Chest X-Ray", "XRAY"));

        content.add(createDoctorRemark());

        JScrollPane scroll = new JScrollPane(content);
        scroll.getVerticalScrollBar().setUnitIncrement(15);
        scroll.setBorder(null);
        parent.add(scroll, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(parent, scroll, "Medical Test Results", JOptionPane.INFORMATION_MESSAGE);

        private static JPanel createHeader(StudentInfo info) {

            JPanel panelInfo = new JPanel(new GridLayout(0, 1));
            panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            panelInfo.add(new JLabel("Name: " + info.getName()));
            panelInfo.add(new JLabel("Student ID: " + info.getStudentId()));
            panelInfo.add(new JLabel("Date Requested: "+ info.getDateRequested().toString()));
            panelInfo.add(new JLabel("Date Released: " + info.getDateReleased().toString()));
            panelInfo.add(new JLabel("Doctor: Dr. Samaniego"));

            return panelInfo;

    }

    private static JPanel createSection(String title, String category){

        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(title);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));

        section.add(label);
        section.add(createTable(category));

        return section;
    }

    private static JScrollPane createTable(String category){

        List<MedicalResult> list = TestResultDAO.getResults(Session.getStudentId(), category);

        String[] cols = {"Test", "Result", "Normal Range", "Remarks"};

        Object[][] data = new Object[list.size()][4];
    
        for(int i = 0; i < list.size(); i++){
            MedicalResult r = list.get(i);
            data[i][0] = r.getTestName();
            data[i][1] = r.getResultValue();
            data[i][2] = r.getNormalRange();
            data[i][3] = r.getRemarks();
        }

        JTable table = new JTable(data, cols);
        table.setEnabled(false);
        table.setRowHeight(22);

        return new JScrollPane(table);
    }

    private static JTextArea createDoctorRemark(){

        JTextArea area = new JTextArea("Doctor's Remark:\nPhysically fit for school activities.\n\n— Dr. Samaniego");
        area.setEditable(false);
        area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        return area;
    }

}
