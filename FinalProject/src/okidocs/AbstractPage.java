package okidocs;

import java.awt.*;
import javax.swing.*;

/**
 * Abstract base class for all main pages.
 * Handles common layout and header behavior.
 */
public abstract class AbstractPage extends JPanel {

    protected final MainApp app;

    public AbstractPage(MainApp app) {
        this.app = app;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Shared header for all pages
        add(new HeaderPanel(app, app::showHomePage), BorderLayout.NORTH);

        // Delegate center content to subclasses
        add(createCenterPanel(), BorderLayout.CENTER);
    }

    // Subclasses must define their own UI
    protected abstract JPanel createCenterPanel();
}
