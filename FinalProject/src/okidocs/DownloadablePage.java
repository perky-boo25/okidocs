package okidocs;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import javax.swing.*;

public class DownloadablePage extends AbstractPage {

    public DownloadablePage(MainApp app) {
        super(app);
    }

    // ───────────── CENTER CONTENT ─────────────
    @Override
    protected JPanel createCenterPanel() {

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);

        JScrollPane scrollPane = createScrollArea();
        wrapper.add(scrollPane, BorderLayout.CENTER);

        return wrapper;
    }

    // ───────────── SCROLLABLE AREA ─────────────
    private JScrollPane createScrollArea() {

        // ✅ LOCAL panel (NOT a field)
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBackground(Color.WHITE);

        loadCards(containerPanel);

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);

        return scrollPane;
    }

    // ───────────── LOAD DOCUMENT CARDS ─────────────
    private void loadCards(JPanel containerPanel) {

        ArrayList<DocumentItem> items = new ArrayList<>();

        items.add(new DocumentItem(
                "Lab Report",
                "https://drive.google.com/uc?export=download&id=1KAW5zG6Z7jHpXa6BlTWqJQxyXVsBo8Tv"
        ));

        items.add(new DocumentItem(
                "Periodic Health Examination Form (Upperclass men)",
                "https://drive.google.com/uc?export=download&id=1_1G9PwK4vU7KLKaXvbHjim8R-nv8dAhS"
        ));

        items.add(new DocumentItem(
                "Entrance Health Examination Form (Freshmen)",
                "https://drive.google.com/uc?export=download&id=18weDSzdRk0jSAUoHbJgoUWNvKfqlPQpd"
        ));

        items.add(new DocumentItem(
                "Dental Health Record",
                "https://drive.google.com/uc?export=download&id=1hJwnn8tlmJtwTUqUz7H1JwgftQAfYFYq"
        ));

        items.add(new DocumentItem(
                "Excuse Slip",
                "https://drive.google.com/uc?export=download&id=1j1k9LeFXBzZL26CtPsDDVqwAudBDnWMQ"
        ));

        for (DocumentItem item : items) {

            MaterialItemCard card = new MaterialItemCard(item);
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

            card.getButton().addActionListener(
                    e -> openLink(e.getActionCommand())
            );

            containerPanel.add(card);
            containerPanel.add(Box.createVerticalStrut(10));
        }
    }

    // ───────────── OPEN LINK ─────────────
    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error opening link.");
        }
    }
}
