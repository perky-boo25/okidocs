package okidocs;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SubmissionPage extends JPanel {

    private JComboBox<String> docTypeDropdown;
    private JTextField fileField;
    private File selectedFile;

    public SubmissionPage(MainApp app) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(createHeaderBar(app), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel createHeaderBar(MainApp app) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 184, 28));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Load logo from classpath: put notOkay.png in src/okidocs/
        JLabel logo = new JLabel();
        java.net.URL logoUrl = getClass().getResource("/okidocs/notOkay.png");
        if (logoUrl != null) {
            ImageIcon icon = new ImageIcon(logoUrl);
            Image scaled = icon.getImage().getScaledInstance(180, 60, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(scaled));
        } else {
            logo.setText("OKIDOCS");
            logo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        }
        header.add(logo, BorderLayout.WEST);

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

    private JPanel createCenterPanel() {
        // outer panel to center the inner form vertically & horizontally
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(Color.WHITE);

        // inner form using BoxLayout stacked vertically and centered
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        form.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Document type dropdown
        JLabel lblType = makeLabel("What type of document?");
        docTypeDropdown = new JComboBox<>(new String[]{
                "Excuse Slip",
                "Health Examination Form",
                "Other Hospital Records"
        });
        docTypeDropdown.setMaximumSize(new Dimension(420, 40));
        docTypeDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // File chooser button + text field
        JButton chooseFileBtn = new JButton("Choose File");
        chooseFileBtn.setBackground(new Color(98, 0, 238));
        chooseFileBtn.setForeground(Color.WHITE);
        chooseFileBtn.setFocusPainted(false);
        chooseFileBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseFileBtn.setMaximumSize(new Dimension(150, 36));

        fileField = new JTextField();
        fileField.setEditable(false);
        fileField.setMaximumSize(new Dimension(420, 40));
        fileField.setBorder(BorderFactory.createTitledBorder("Selected File"));
        fileField.setAlignmentX(Component.CENTER_ALIGNMENT);

        chooseFileBtn.addActionListener(e -> openFileChooser());

        // Submit button
        JButton submitBtn = new JButton("Oki");
        submitBtn.setBackground(new Color(98, 0, 238));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        submitBtn.setMaximumSize(new Dimension(120, 40));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.addActionListener(e -> handleSubmit());

        // vertical spacing and adding components centered
        form.add(lblType);
        form.add(Box.createVerticalStrut(8));
        form.add(docTypeDropdown);
        form.add(Box.createVerticalStrut(18));

        form.add(chooseFileBtn);
        form.add(Box.createVerticalStrut(8));
        form.add(fileField);
        form.add(Box.createVerticalStrut(24));

        form.add(submitBtn);

        // place form in center of outer panel
        outer.add(form, new GridBagConstraints());
        return outer;
    }

    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    private void openFileChooser() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            fileField.setText(selectedFile.getAbsolutePath());
        }
    }

    // backend placeholder - coder implements actual storage logic here
    private boolean submitFile(String type, File file) {
        // TODO: store file and record metadata into DB or server
        // For now, accept existing file as success
        return file != null && file.exists();
    }

    private void handleSubmit() {
        String type = (String) docTypeDropdown.getSelectedItem();
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please choose a file first.");
            return;
        }
        boolean ok = submitFile(type, selectedFile);
        if (ok) {
            JOptionPane.showMessageDialog(this, "File submitted successfully!");
            // optional: clear inputs
            selectedFile = null;
            fileField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Submission failed. Please try again.");
        }
    }
}
