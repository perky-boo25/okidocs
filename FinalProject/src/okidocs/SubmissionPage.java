package okidocs;

import java.awt.*;
import java.io.File;
import javax.swing.*;

import okidocs.app.MainApp;

public class SubmissionPage extends JPanel {

    // UI COMPONENTS
    private JComboBox<String> docTypeDropdown;
    private JTextField fileField;

    // Selected File Reference
    private File selectedFile;

    public SubmissionPage(MainApp app) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // HEADER
        add(new HeaderPanel(app, app::showHomePage), BorderLayout.NORTH);

        // CENTER PANEL
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(20, 250, 20, 250));

        // Document Type Dropdown
        docTypeDropdown = new JComboBox<>(new String[]{
                "Excuse Slip",
                "Health Examination Form",
                "Other Hospital Records"
        });
        docTypeDropdown.setMaximumSize(new Dimension(350, 40));
        docTypeDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // File Chooser Button and Field
        JButton chooseFileBtn = new JButton("Choose File");
        chooseFileBtn.setBackground(new Color(110, 9, 38));
        chooseFileBtn.setForeground(Color.WHITE);
        chooseFileBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        chooseFileBtn.addActionListener(e -> openFileChooser());

        //Displays chosen file name
        fileField = new JTextField();
        fileField.setEditable(false);
        fileField.setBorder(BorderFactory.createTitledBorder("Selected File"));
        fileField.setMaximumSize(new Dimension(350, 50));
        fileField.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Submit button
        JButton submitBtn = new JButton("Oki");
        submitBtn.setBackground(new Color(110, 9, 38));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setMaximumSize(new Dimension(120, 40));
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        submitBtn.addActionListener(e -> handleSubmit());

        //Layout order
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

    // Creates a centered label
    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return lbl;
    }

    //Opens file chooser and stores selected file
    private void openFileChooser() {
        JFileChooser chooser = new JFileChooser();

        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));
        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            fileField.setText(selectedFile.getName());
        }
    }

    //File Submission Logic
    private void handleSubmit() {

    //Validate file selection
    if (selectedFile == null) {
        JOptionPane.showMessageDialog(this, "Please choose a file first.");
        return;
    }

    //Get logged-in student
    int studentId = Session.getStudentId();
   
    if (studentId == -1){
        JOptionPane.showMessageDialog(this, "Please login first.");
        return;
    }

    //Confirm Submission
    int confirm = JOptionPane.showConfirmDialog(this, "Submit this file?", "Confirm Submission", JOptionPane.YES_NO_OPTION);

    if(confirm != JOptionPane.YES_OPTION){
        return; 
    }

    String docType = (String) docTypeDropdown.getSelectedItem();

    //Prepare data
    String filePath = selectedFile.getAbsolutePath();

    boolean success = ExcuseSlipDAO.submitSlip(
            studentId, 
            java.sql.Date.valueOf(java.time.LocalDate.now()),
            docType,
            filePath
        );

    //Result feedback
    if (success) {
        JOptionPane.showMessageDialog(this, "File submitted successfully!");
        selectedFile = null;
        fileField.setText("");
    } else {
        JOptionPane.showMessageDialog(this, "Submission failed.");
    }
}
}