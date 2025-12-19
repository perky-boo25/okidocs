public class Submission {

    private int appointmentID;
    private int studentID;
    private String submissionType;
    private String pdfPath;

    public Submission(int appointmentID, int studentID, String submissionType, String pdfPath) {
        this.appointmentID = appointmentID;
        this.studentID = studentID;
        this.submissionType = submissionType;
        this.pdfPath = pdfPath;
    }

    public int getAppointmentID() {
        return this.appointmentID;
    }

    public int getStudentID() {
        return this.studentID;
    }

    public String getSubmissionType() {
        return this.submissionType;
    }

     public String getPdfPath() {
        return this.pdfPath;
    }
}