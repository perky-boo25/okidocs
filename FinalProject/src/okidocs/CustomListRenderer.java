package okidocs;

import java.awt.*;
import javax.swing.*;

public class CustomListRenderer extends JPanel implements ListCellRenderer<DocumentItem> {

    private final JLabel titleLabel = new JLabel();
    private final JButton downloadBtn = new JButton("Download");

    private final Color purple = new Color(153, 102, 153);
    private final Color selectedColor = new Color(180, 150, 180);

    public CustomListRenderer() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        add(titleLabel, BorderLayout.WEST);
        add(downloadBtn, BorderLayout.EAST);
        downloadBtn.setFocusable(false);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends DocumentItem> list,
            DocumentItem item,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        titleLabel.setText(item.getTitle());

        setBackground(isSelected ? selectedColor : purple);
        titleLabel.setForeground(isSelected ? Color.BLACK : Color.WHITE);

        downloadBtn.setBackground(Color.WHITE);

        return this;
    }
}
