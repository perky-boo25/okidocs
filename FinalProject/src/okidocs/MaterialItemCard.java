package okidocs;

import java.awt.*;
import javax.swing.*;

public class MaterialItemCard extends JPanel {

    // displays document title
    private final JLabel titleLabel = new JLabel();

    //download action button
    private final JButton downloadBtn = new JButton("Download");

    public MaterialItemCard(DocumentItem item) {

        //Layout
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Border Styling
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(6, 6, 6, 6),
                BorderFactory.createLineBorder(new Color(230, 230, 230))
        ));

        // title styling
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        titleLabel.setForeground(new Color(33, 33, 33));
        titleLabel.setText(item.getTitle());
        add(titleLabel, BorderLayout.WEST);

        //download button styling
        downloadBtn.setBackground(new Color(110, 9, 38));
        downloadBtn.setForeground(Color.WHITE);
        downloadBtn.setFocusPainted(false);
        downloadBtn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        downloadBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(downloadBtn, BorderLayout.EAST);

        // Hover effect
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBackground(new Color(245, 245, 245));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBackground(Color.WHITE);
            }
        });

        // Allow parent to attach download behavior
        downloadBtn.setActionCommand(item.getUrl());
    }

    //Allows parent to attach download behavior
    public JButton getButton() {
        return downloadBtn;
    }
}
