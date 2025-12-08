package okidocs;

import java.awt.*;
import javax.swing.*;

public class WelcomePage extends JPanel {

    public WelcomePage(MainApp app) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //MAIN PANEL (BorderLayout)
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(Color.WHITE);

        //TOP: Image + text
        ImageIcon img = new ImageIcon("title.png");

        JLabel imgLabel = new JLabel("by print(\"hello world!\")", img, JLabel.CENTER);
        imgLabel.setVerticalTextPosition(JLabel.BOTTOM);
        imgLabel.setHorizontalTextPosition(JLabel.CENTER);
        imgLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        imgLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        main.add(imgLabel, BorderLayout.NORTH);

        //CENTER: Login Button + sign up text 
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(new Color(255, 255, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        // LOGIN BUTTON
        JButton loginBtn = new JButton("Log-in");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setBackground(new Color(98, 0, 238));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        //  CONNECT TO LoginPanel 
        loginBtn.addActionListener(e -> app.showLoginPage());

        gbc.gridy = 0;
        center.add(loginBtn, gbc);

        //SIGN UP LABEL
        JLabel signUp = new JLabel("Don't have an account yet? Sign up.");
        signUp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        signUp.setForeground(new Color(80, 80, 80));

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 20, 0);
        center.add(signUp, gbc);

        signUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.showSignUpPage();
            }
        });

        main.add(center, BorderLayout.CENTER);
        add(main);
    }
}
