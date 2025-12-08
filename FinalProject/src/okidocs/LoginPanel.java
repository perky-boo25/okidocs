package okidocs;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends JPanel {

    public LoginPanel(MainApp app) {

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("OKIDOCS");
        logo.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel = new JLabel("Log-in");
        loginLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField studentNumField = createInputField("Student Number");
        JPasswordField passwordField = createPasswordField("Password");

        JButton loginBtn = createMainButton("Oki");
        JButton goToSignup = createLinkButton("Create Account");

         //  LOGIN BUTTON LOGIC 
        loginBtn.addActionListener(e -> {

            String studentNum = studentNumField.getText();
            String password = new String(passwordField.getPassword());

            // CALL THE EMPTY BACKEND FUNCTION
            if (validateLogin(studentNum, password)) {
                app.showHomePage(); // go to homepage
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid login.\n(This message will be replaced once database is added)",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        goToSignup.addActionListener(e -> app.showSignUpPage());

        centerPanel.add(logo);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(loginLabel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(studentNumField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(passwordField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(loginBtn);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(goToSignup);

        add(centerPanel, BorderLayout.CENTER);
    }

    // ➤ BACKEND HOOK #1 — LOGIN VALIDATION (CODER WILL EDIT LATER)
    private boolean validateLogin(String studentNum, String password) {

        // TODO: CONNECT DATABASE HERE
        // Example:
        // return Database.verifyUser(studentNum, password);

        // TEMPORARY KEYWORD PASS: (for testing only)
        return studentNum.equals("test") && password.equals("pass");

        // Replace the above simple check later with real DB logic!
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
