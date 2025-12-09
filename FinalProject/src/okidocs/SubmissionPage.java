package okidocs;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class SubmissionPage extends JPanel {

    private JComboBox<String> docType;
    private JTextField fileField;
    private File selectedFile;

    public SubmissionPage(MainApp app) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(createHeader(app), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel createHeader(MainApp app) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255,184,28));
        header.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));

        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon("notOkay.png");
        Image scaled = icon.getImage().getScaledInstance(180,60,Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(scaled));
        header.add(logo, BorderLayout.WEST);

        JButton back = new JButton("Back");
        back.setBackground(new Color(110,9,38));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.addActionListener(e -> app.showHomePage());
        header.add(back, BorderLayout.EAST);

        return header;
    }

    private JPanel createCenterPanel() {

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(20,250,20,250));

        // Dropdown label
        JLabel typeLabel = new JLabel("What type of document?");
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        docType = new JComboBox<>(new String[]{
            "Excuse Slip",
            "Health Examination Form",
            "Other Hospital Records"
        });
        docType.setMaximumSize(new Dimension(400,40));
        docType.setAlignmentX(Component.CENTER_ALIGNMENT);

        // File chooser field
        fileField = new JTextField();
        fileField.setEditable(false);
        fileField.setBorder(BorderFactory.createTitledBorder("Selected File"));
        fileField.setMaximumSize(new Dimension(400,45));
        fileField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton chooseBtn = new JButton("Choose File");
        chooseBtn.setBackground(new Color(98,0,238));
        chooseBtn.setForeground(Color.WHITE);
        chooseBtn.setFocusPainted(false);
        chooseBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseBtn.addActionListener(e -> openChooser());

        JButton submit = new JButton("Oki");
        submit.setBackground(new Color(98,0,238));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setMaximumSize(new Dimension(120,40));
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.addActionListener(e -> handleSubmit());

        // ADD COMPONENTS
        center.add(typeLabel);
        center.add(docType);
        center.add(Box.createVerticalStrut(20));

        center.add(chooseBtn);
        center.add(Box.createVerticalStrut(10));

        center.add(fileField);
        center.add(Box.createVerticalStrut(30));

        center.add(submit);

        return center;
    }

    private void openChooser() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            fileField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void handleSubmit() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please choose a file.");
            return;
        }

        JOptionPane.showMessageDialog(this, "File submitted successfully!");
    }
}
