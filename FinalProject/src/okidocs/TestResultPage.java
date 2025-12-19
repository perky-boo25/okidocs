package okidocs;

import java.awt.*;
import javax.swing.*;

public class TestResultPage extends AbstractPage {

    // ───────────── CONSTRUCTOR ─────────────
    public TestResultPage(MainApp app) {
        super(app); // inherit layout + header from AbstractPage
    }

    // ───────────── CENTER CONTENT ─────────────
    @Override
    protected JPanel createCenterPanel() {

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        // request test result button
        JButton requestBtn = new JButton("Request Test Result");
        requestBtn.addActionListener(e -> RequestTestResult.request(center));
        styleBtn(requestBtn);

        // view test results button
        JButton viewBtn = new JButton("View Test Results");
        viewBtn.addActionListener(e -> ViewResults.show(center));
        styleBtn(viewBtn);

        // layout order
        center.add(requestBtn);
        center.add(Box.createVerticalStrut(15));
        center.add(viewBtn);

        return center;
    }

    // ───────────── BUTTON STYLING ─────────────
    private void styleBtn(JButton btn) {
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(260, 45));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(110, 9, 38));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
    }
}
