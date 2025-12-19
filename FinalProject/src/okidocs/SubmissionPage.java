package okidocs;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class SubmissionPage extends AbstractPage {

    // ───────────── UI COMPONENTS ─────────────
    private JComboBox<String> docTypeDropdown;
    private JTextField fileField;

    // selected file reference
    private File selectedFile;

    // ───────────── CONSTRUCTOR ─────────────
    public SubmissionPage(MainApp app) {
        super(app); // AbstractPage handles layout + header
    }

    // ───────────── CENTER CONTENT ─────────────
    @Override
    protected JPanel createCenterPanel() {

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(20, 250, 20, 250));

        // document type dropdown
        docTypeDropdown = new JComboBox<>(new String[]{
                "Excuse Slip",
                "Health Examination Form",
                "Other Hospital Records"
        });
        docTypeDropdown.setMaximumSize(new Dimension(350, 40));
        docTypeDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // file chooser button
        JButton chooseFileBtn = new JButton("Choose File");
        chooseFileBtn.setBackground(new Color(110, 9, 38));
        chooseFileBtn.setForeground(Color.WHITE);
        chooseFileBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseFileBtn.addActionListener(e -> openFileChooser());

        // selected file display
        fileField = new JTextField();
        fileField.setEditable(false);
        fileField.setBorder(BorderFactory.createTitledBorder("Selected File"));
        fileField.setMaximumSize(new Dimension(350, 50));
        fileField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // submit button
        JButton submitBtn = new JButton("Oki");
        submitBtn.setBackground(new Color(110, 9, 38));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setMaximumSize(new Dimension(120, 40));
        submitBtn.addActionListener(e -> handleSubmit());

        // layout order
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

        return center;
    }

    // ───────────── LABEL HELPER ─────────────
    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return lbl;
    }

    // ───────────── FILE CHOOSER ─────────────
    private void openFileChooser() {

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf")
        );

        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            fileField.setText(selectedFile.getName());
        }
    }

    // ───────────── SUBMISSION LOGIC ─────────────
    private void handleSubmit() {

        // validate file selection
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please choose a file first.");
            return;
        }

        // session validation
        int studentId = Session.getStudentId();
        if (studentId == -1) {
            JOptionPane.showMessageDialog(this, "Please login first.");
            return;
        }

        // confirm submission
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Submit this file?",
                "Confirm Submission",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // prepare submission data
        String filePath = selectedFile.getAbsolutePath();
        String docType = (String) docTypeDropdown.getSelectedItem();

        boolean success = ExcuseSlipDAO.submitSlip(
                studentId,
                docType,
                filePath
        );

        // feedback
        if (success) {
            JOptionPane.showMessageDialog(this, "File submitted successfully!");
            selectedFile = null;
            fileField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Submission failed.");
        }
    }
}
