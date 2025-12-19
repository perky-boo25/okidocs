package okidocs; 

import java.awt.*;
import javax.swing.*;

public final class MainApp {

    //main application frame
    private final JFrame frame;

    public MainApp() {

        //frame set-up 
        frame = new JFrame("OKIDOCS");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        //starts with welcome page
        showWelcomePage();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }

    // Screen Navigation methods
    public void showWelcomePage() {
        frame.setContentPane(new WelcomePage(this));
        refresh();
    }

    public void showLoginPage() {
        frame.setContentPane(new LoginPanel(this));
        refresh();
    }

    public void showSignUpPage() {
        frame.setContentPane(new SignUpPanel(this));
        refresh();
    }

    public void showHomePage() {
        frame.setContentPane(new HomePage(this));
        refresh();
    }

    public void showDownloadablePage() {
        frame.setContentPane(new DownloadablePage(this));
        refresh();
    }

    public void showAppointmentsPage() {
        frame.setContentPane(new AppointmentsPage(this));
        refresh();
    }

    public void showSubmissionPage() {
        frame.setContentPane(new SubmissionPage(this));
        refresh();
    }

    //Refreshes the current screen
    private void refresh() {
        frame.revalidate();
        frame.repaint();
    }

    public void showTestResultPage() {
    frame.setContentPane(new TestResultPage(this));
    refresh();
}
}
