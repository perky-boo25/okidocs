package okidocs;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SubmissionPage extends JPanel {

    private JComboBox<String> docTypeDropdown;
    private JTextField fileField;   // shows file name
    private File selectedFile;      // file chosen by user

    public SubmissionPage(MainApp app) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // HEADER BAR
        add(createHeaderBar(app), BorderLayout.NORTH);

        // CENTER PANEL
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));
        center.setBackground(Color.WHITE);

        // 1) DOCUMENT TYPE DROPDOWN
        docTypeDropdown = new JComboBox<>(new String[]{
            "Excuse Slip",
            "Health Examination Form",
            "Other Hospital Records"
        });
        docTypeDropdown.setMaximumSize(new Dimension(400, 40));
        docTypeDropdown.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 2) FILE CHOOSER
        JButton chooseFileBtn = new JButton("Choose File");
        chooseFileBtn.setBackground(new Color(98, 0, 238));
        chooseFileBtn.setForeground(Color.WHITE);
        chooseFileBtn.setFocusPainted(false);
        chooseFileBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        fileField = new JTextField();
        fileField.setEditable(false);
        fileField.setMaximumSize(new Dimension(400, 40));
        fileField.setBorder(BorderFactory.createTitledBorder("Selected File"));

        chooseFileBtn.addActionListener(e -> openFileChooser());

        // 3) SUBMIT BUTTON
        JButton submitBtn = new JButton("Oki");
        submitBtn.setBackground(new Color(98, 0, 238));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        submitBtn.setMaximumSize(new Dimension(120, 40));
        submitBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        submitBtn.addActionListener(e -> handleSubmit());

        // Add Components
        center.add(makeLabel("What type of document?"));
        center.add(docTypeDropdown);
        center.add(Box.createVerticalStrut(20));

        center.add(chooseFileBtn);
        center.add(Box.createVerticalStrut(10));
        center.add(fileField);
        center.add(Box.createVerticalStrut(25));

        center.add(submitBtn);

        add(center, BorderLayout.CENTER);
    }

    // HEADER WITH BACK BUTTON (same style as DownloadablePage)
    private JPanel createHeaderBar(MainApp app) {

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 184, 28));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // LOGO
        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon("notOkay.png");
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

    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    // OPEN FILE CHOOSER
    private void openFileChooser() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            fileField.setText(selectedFile.getAbsolutePath());
        }
    }

    //  BACKEND PLACEHOLDER — THE CODER WILL IMPLEMENT STORAGE LATER
    private boolean submitFile(String type, File file) {

        // TODO: Save file to database or storage directory
        // RETURN TRUE if uploaded successfully
        // RETURN FALSE if duplicate or invalid

        // TEMPORARY LOGIC:
        return file != null && file.exists();
    }

    // HANDLE SUBMISSION BUTTON
    private void handleSubmit() {
        String type = (String) docTypeDropdown.getSelectedItem();

        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please choose a file first!");
            return;
        }

        if (submitFile(type, selectedFile)) {
            JOptionPane.showMessageDialog(this, "File submitted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Submission failed. Please try again.");
        }
    }
}
