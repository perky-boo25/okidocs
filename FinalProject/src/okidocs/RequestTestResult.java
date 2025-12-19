package okidocs;

import javax.swing.*;

public class RequestTestResult {

    public static void request(JPanel parent) {

        int studentId = Session.getStudentId();

        if (studentId == -1) {
            JOptionPane.showMessageDialog(parent, "Please login first.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                parent,
                "Request your medical test results?\nProcessing may take 5–10 business days.",
                "Confirm Request",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        boolean requested = RequestDAO.submitRequest(
                studentId,
                java.sql.Date.valueOf(java.time.LocalDate.now())
        );

        if (requested) {
            JOptionPane.showMessageDialog(
                    parent,
                    "Request submitted successfully.\nResults will be available in 5–10 business days."
            );
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "You have already submitted a request or an error occurred."
            );
        }
    }
}
