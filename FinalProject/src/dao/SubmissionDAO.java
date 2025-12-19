package okidocs.dao;

import model.Submission;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubmissionDAO extends DBConnection {

    public SubmissionDAO() {
        super(); // initialize DB connection
    }

    // Add a submission (PDF path stored)
    public void addSubmission(Submission submission) {
        try {
            String sql = """
                INSERT INTO submissions
                (appointmentID, studentID, submissionType, pdfPath)
                VALUES (?, ?, ?, ?)
            """;

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, submission.getAppointmentID());
            pst.setInt(2, submission.getStudentID());
            pst.setString(3, submission.getSubmissionType());
            pst.setString(4, submission.getPdfPath());

            pst.executeUpdate();
            System.out.println("Submission saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all submissions of a student
    public List<Submission> getSubmissionsByStudent(int studentID) {
        List<Submission> submissions = new ArrayList<>();

        try {
            String sql = "SELECT * FROM submissions WHERE studentID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, studentID);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("appointmentID");
                String submissionType = rs.getString("submissionType");
                String pdfPath = rs.getString("pdfPath");

                submissions.add(
                    new Submission(appointmentID, studentID, submissionType, pdfPath)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return submissions;
    }

    // Get submissions for a specific appointment
    public List<Submission> getSubmissionsByAppointment(int appointmentID) {
        List<Submission> submissions = new ArrayList<>();

        try {
            String sql = "SELECT * FROM submissions WHERE appointmentID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, appointmentID);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int studentID = rs.getInt("studentID");
                String submissionType = rs.getString("submissionType");
                String pdfPath = rs.getString("pdfPath");

                submissions.add(
                    new Submission(appointmentID, studentID, submissionType, pdfPath)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return submissions;
    }
}
