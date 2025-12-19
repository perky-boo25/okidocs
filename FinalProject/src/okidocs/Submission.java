package okidocs;

public class Submission {

    private int submissionId;
    private int studentId;
    private String submissionType;
    private String pdfPath;

    public Submission(int submissionId, int studentId,
                      String submissionType, String pdfPath) {
        this.submissionId = submissionId;
        this.studentId = studentId;
        this.submissionType = submissionType;
        this.pdfPath = pdfPath;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getSubmissionType() {
        return submissionType;
    }

    public String getPdfPath() {
        return pdfPath;
    }
}
