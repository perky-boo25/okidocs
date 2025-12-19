package okidocs;
import java.util.Date;

public class TestResults {
    private int requestID;
    private int studentID;
    private String testName;
    private String resultName;
    private String normalRange;
    private String remarks;
    private Date dateReleased;
    private String drRemarks;

    public TestResults(int requestID, int studentID, String testName, String resultName,
    String normalRange, String remarks, Date dateReleased, String drRemarks){
    this.requestID = requestID;
    this.studentID = studentID;
    this.testName = testName;
    this.resultName = resultName;
    this.normalRange = normalRange;
    this.remarks = remarks;
    this.dateReleased = dateReleased;
    this.drRemarks = drRemarks;
    }

     public int getRequestID() {
        return this.requestID;
    }

    public int getStudentID() {
        return this.studentID;
    }

    public String getTestName() {
        return this.testName;
    }

    public String getResultName() {
        return this.resultName;
    }

    public String getNormalRange() {
        return this.normalRange;
    } 

    public String getRemarks() {
        return this.remarks;
    }

    public Date getDateReleased() {
        return this.dateReleased;
    } 

    public String getDrRemarks() {
        return this.drRemarks;
    }
}