package okidocs;

import java.awt.*;
import javax.swing.*;

// Custom renderer for JList to display DocumentItem with a download button
public class CustomListRenderer extends JPanel implements ListCellRenderer<DocumentItem> {

    // UI Components
    private final JLabel titleLabel = new JLabel();
    private final JButton downloadBtn = new JButton("Download");

    // Colors
    private final Color purple = new Color(153, 102, 153);
    private final Color selectedColor = new Color(180, 150, 180);

    public CustomListRenderer() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        add(titleLabel, BorderLayout.WEST);
        add(downloadBtn, BorderLayout.EAST);

        //Prevent button from stealing focus
        downloadBtn.setFocusable(false);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends DocumentItem> list,
            DocumentItem item,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        //set document title
        titleLabel.setText(item.getTitle());

        //change colors based on selection
        setBackground(isSelected ? selectedColor : purple);
        titleLabel.setForeground(isSelected ? Color.BLACK : Color.WHITE);

        // keep button style consistent
        downloadBtn.setBackground(Color.WHITE);

        return this;
    }
}
