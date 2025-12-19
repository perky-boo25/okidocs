package okidocs;

import java.awt.*;
import javax.swing.*;

public class TestResultPage extends JPanel {

    public TestResultPage(MainApp app) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(new HeaderPanel(app, app::showHomePage), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        JButton requestBtn = new JButton("Request Test Result");
        JButton viewBtn = new JButton("View Test Results");

        requestBtn.addActionListener(e -> RequestTestResult.request(this));
        viewBtn.addActionListener(e -> ViewResults.view(this));

        styleBtn(requestBtn);
        styleBtn(viewBtn);
        
        center.add(requestBtn);
        center.add(Box.createVerticalStrut(15));
        center.add(viewBtn);

        add(center, BorderLayout.CENTER);
    }

    private void styleBtn(JButton btn) {
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(260, 45));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(110, 9, 38));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
    }
}