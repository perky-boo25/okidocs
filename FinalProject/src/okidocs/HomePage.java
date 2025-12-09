package okidocs;

import java.awt.*;
import javax.swing.*;

public class HomePage extends JPanel {

    public HomePage(MainApp app) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // MAIN PANEL (BorderLayout)
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(Color.WHITE);

        // CENTER: Login button + sign-up text
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);

        Dimension size = new Dimension(200, 45);

        // LOGIN BUTTON
        JButton btn1 = new JButton("Documents");
        btn1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn1.setBackground(new Color(98, 0, 238));
        btn1.setForeground(Color.WHITE);
        btn1.setFocusPainted(false);
        btn1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn1.setMaximumSize(size);

        JButton btn2 = new JButton("Set Appointment");
        btn2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn2.setBackground(new Color(98, 0, 238));
        btn2.setForeground(Color.WHITE);
        btn2.setFocusPainted(false);
        btn2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn2.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn2.setMaximumSize(size);

        JButton btn3 = new JButton("Submit Files");
        btn3.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn3.setBackground(new Color(98, 0, 238));
        btn3.setForeground(Color.WHITE);
        btn3.setFocusPainted(false);
        btn3.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn3.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn3.setMaximumSize(size);

        // ADD CONNECTORS HERE
        btn1.addActionListener(e -> app.showDownloadablePage());
        btn2.addActionListener(e -> app.showAppointmentsPage());
        btn3.addActionListener(e -> app.showSubmissionPage());
        
        center.add(Box.createVerticalGlue());
        center.add(btn1);
        center.add(Box.createVerticalStrut(10));
        center.add(btn2);
        center.add(Box.createVerticalStrut(10));
        center.add(btn3);
        center.add(Box.createVerticalGlue());

        main.add(center);
        add(main);
    }
}
