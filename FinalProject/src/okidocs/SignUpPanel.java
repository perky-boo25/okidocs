package okidocs;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import okidocs.app.MainApp;

public class SignUpPanel extends JPanel {

    public SignUpPanel(MainApp app) {

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel logo1 = new JLabel("OKIDOCS");
        logo1.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        logo1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Sign-up");
        title.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = createInputField("Name");
        JTextField studentNumField = createInputField("Student Number");
        JPasswordField passwordField = createPasswordField("Password");

        JButton submitBtn = createMainButton("Oki");
        JButton backBtn = createLinkButton("Log in");

        //CHANGED IT HERE
       submitBtn.addActionListener(e -> {

            String name = nameField.getText();
            String studentNum = studentNumField.getText();
            String password = new String(passwordField.getPassword());

            // CALL BACKEND SIGNUP HOOK
            boolean ok = registerUser(name, studentNum, password);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Account created!\nYou can now log in.");
                app.showLoginPage();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Signup failed.\nReplace this once DB exists.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        backBtn.addActionListener(e -> app.showLoginPage());

        centerPanel.add(logo1);
        centerPanel.add(title);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(nameField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(studentNumField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(passwordField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(submitBtn);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(backBtn);

        add(centerPanel, BorderLayout.CENTER);
    }

    //ADDED FUNCTION
    private boolean registerUser(String name, String studentNum, String password) {

        // TODO: Add MySQL insert query or validation here
        // Example:
        // return Database.createUser(name, studentNum, password);

        // TEMPORARY LOGIC FOR NOW:
        if (name.isEmpty() || studentNum.isEmpty() || password.isEmpty()) {
            return false;
        }

        // Accept all non-empty signups for now
        return true;
    }


    private JTextField createInputField(String title) {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(400, 45));
        field.setMaximumSize(new Dimension(400, 45));
        field.setBorder(BorderFactory.createTitledBorder(title));
        return field;
    }

    private JPasswordField createPasswordField(String title) {
        JPasswordField field = new JPasswordField();
        field.setPreferredSize(new Dimension(400, 45));
        field.setMaximumSize(new Dimension(400, 45));
        field.setBorder(BorderFactory.createTitledBorder(title));
        return field;
    }

    private JButton createMainButton(String text) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(new Color(220, 220, 220));
        btn.setFocusPainted(false);
        return btn;
    }

    private JButton createLinkButton(String text) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        return btn;
    }
}
