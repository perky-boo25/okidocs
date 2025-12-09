package okidocs;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import javax.swing.*;

public class DownloadablePage extends JPanel {

    private final JPanel containerPanel = new JPanel();

    public DownloadablePage(MainApp app) {

        setLayout(new BorderLayout());

        add(createHeaderBar(app), BorderLayout.NORTH);
        add(createScrollArea(), BorderLayout.CENTER);
    }

    private JPanel createHeaderBar(MainApp app) {

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 184, 28));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/okidocs/notOkay.png"));
        Image scaled = icon.getImage().getScaledInstance(180, 60, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(scaled));
        header.add(logo, BorderLayout.WEST);

        JButton backBtn = new JButton("Back");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(110, 9, 38));
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backBtn.addActionListener(e -> app.showHomePage());
        header.add(backBtn, BorderLayout.EAST);

        return header;
    }

    private JScrollPane createScrollArea() {

        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBackground(Color.WHITE);

        loadCards();

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);

        return scrollPane;
    }

    private void loadCards() {

        ArrayList<DocumentItem> items = new ArrayList<>();

        items.add(new DocumentItem("Lab Report",
                "https://drive.google.com/uc?export=download&id=1KAW5zG6Z7jHpXa6BlTWqJQxyXVsBo8Tv"));

        items.add(new DocumentItem("Periodic Health Examination Form (Upperclass men)",
                "https://drive.google.com/uc?export=download&id=1_1G9PwK4vU7KLKaXvbHjim8R-nv8dAhS"));

        items.add(new DocumentItem("Entrance Health Examination Form (Freshmen)",
                "https://drive.google.com/uc?export=download&id=18weDSzdRk0jSAUoHbJgoUWNvKfqlPQpd"));

        items.add(new DocumentItem("Dental Health Record",
                "https://drive.google.com/uc?export=download&id=1hJwnn8tlmJtwTUqUz7H1JwgftQAfYFYq"));

        items.add(new DocumentItem("Excuse Slip",
                "https://drive.google.com/uc?export=download&id=1j1k9LeFXBzZL26CtPsDDVqwAudBDnWMQ"));

        for (DocumentItem item : items) {

            MaterialItemCard card = new MaterialItemCard(item);
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

            // When clicking Download
            card.getButton().addActionListener(e -> openLink(e.getActionCommand()));

            containerPanel.add(card);
            containerPanel.add(Box.createVerticalStrut(10));
        }
    }

    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error opening link.");
        }
    }
}
