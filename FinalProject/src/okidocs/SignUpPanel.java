package okidocs;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

            String name = nameField.getText().trim();
            String studentNum = studentNumField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty() || studentNum.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            int studentId;
            try {
                studentId = Integer.parseInt(studentNum);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Student number must be numeric.");
                return;
            }

            boolean success = FakeAuthService.register(studentId, name, password);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Account created (TEMP MODE).\nYou may now log in.");
                app.showLoginPage();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Student ID already exists.",
                        "Sign-up Failed",
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

    if (name.isEmpty() || studentNum.isEmpty() || password.isEmpty()) {
        return false;
    }

    try {
        int studentId = Integer.parseInt(studentNum);

        return StudentDAO.register(
                studentId,
                name,
                password
        );

    } catch (NumberFormatException e) {
        return false;
    }
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
