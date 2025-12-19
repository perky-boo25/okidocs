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

            String studentNum = studentNumField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (studentNum.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all fields.");
                return;
            }

            int studentId;
            try {
                studentId = Integer.parseInt(studentNum);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Student number must be numeric.");
                return;
            }

            if (StudentDAO.validateLogin(studentId, password)) {

                // 🔐 SAVE SESSION
                Session.setStudentId(studentId);

                JOptionPane.showMessageDialog(this, "Login successful!");
                app.showHomePage();

            } else {
                JOptionPane.showMessageDialog(this,
                    "Invalid student number or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
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
