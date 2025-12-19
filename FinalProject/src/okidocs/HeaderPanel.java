package okidocs;

import java.awt.*;
import javax.swing.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel(MainApp app, Runnable backAction) {

        setLayout(new BorderLayout());
        setBackground(new Color(255, 184, 28));
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // LOGO (LEFT)
        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/okidocs/notOkay.png") );
        Image scaled = icon.getImage().getScaledInstance(180, 60, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(scaled));

        add(logo, BorderLayout.WEST);

        // BACK BUTTON (RIGHT)
        JButton backBtn = new JButton("Back");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(110, 9, 38));
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backBtn.addActionListener(e -> backAction.run());

        add(backBtn, BorderLayout.EAST);
    }
}
