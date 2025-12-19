package okidocs;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class SubmissionPage extends JPanel {

    private JComboBox<String> docTypeDropdown;
    private JTextField fileField;
    private File selectedFile;

    public SubmissionPage(MainApp app) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(new HeaderPanel(app, app::showHomePage), BorderLayout.NORTH);


        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(20, 250, 20, 250));

        docTypeDropdown = new JComboBox<>(new String[]{
                "Excuse Slip",
                "Health Examination Form",
                "Other Hospital Records"
        });
        docTypeDropdown.setMaximumSize(new Dimension(350, 40));
        docTypeDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton chooseFileBtn = new JButton("Choose File");
        chooseFileBtn.setBackground(new Color(110, 9, 38));
        chooseFileBtn.setForeground(Color.WHITE);
        chooseFileBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        chooseFileBtn.addActionListener(e -> openFileChooser());

        fileField = new JTextField();
        fileField.setEditable(false);
        fileField.setBorder(BorderFactory.createTitledBorder("Selected File"));
        fileField.setMaximumSize(new Dimension(350, 50));
        fileField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton submitBtn = new JButton("Oki");
        submitBtn.setBackground(new Color(110, 9, 38));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setMaximumSize(new Dimension(120, 40));
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        submitBtn.addActionListener(e -> handleSubmit());

        center.add(Box.createVerticalGlue());
        center.add(makeLabel("What type of document?"));
        center.add(docTypeDropdown);
        center.add(Box.createVerticalStrut(15));
        center.add(chooseFileBtn);
        center.add(Box.createVerticalStrut(10));
        center.add(fileField);
        center.add(Box.createVerticalStrut(20));
        center.add(submitBtn);
        center.add(Box.createVerticalGlue());

        add(center, BorderLayout.CENTER);
    }

    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return lbl;
    }

    private void openFileChooser() {
        JFileChooser chooser = new JFileChooser();
        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            fileField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void handleSubmit() {

    if (selectedFile == null) {
        JOptionPane.showMessageDialog(this, "Please choose a file first.");
        return;
    }

    int studentId = Session.getStudentId();
   
    if (studentId == -1){
        JOptionPane.showMessageDialog(this, "Please login first.");
        return;
    }

    String docType = (String) docTypeDropdown.getSelectedItem();
    String fileName = selectedFile.getName();
    String filePath = selectedFile.getAbsolutePath();

    boolean success = ExcuseSlipDAO.submitSlip(
            studentId, java.sql.Date.valueOf(java.time.LocalDate.now()), filePath);
    

    if (success) {
        JOptionPane.showMessageDialog(this, "File submitted successfully!");
        selectedFile = null;
        fileField.setText("");
    } else {
        JOptionPane.showMessageDialog(this, "Submission failed.");
    }
}
}