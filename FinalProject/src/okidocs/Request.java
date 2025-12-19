
import java.util.Date;

public class Request {
    private int requestID;
    private int studentID; 
    private Date dateRequested;
    private String status;

    public Request(int requestID, int studentID, Date dateRequested, String status) {
        this.requestID = requestID;
        this.studentID = studentID;
        this.dateRequested = dateRequested;
        this.status = status; 
    }

    public int getRequestID(){
        return this.requestID;
    }

    public int getStudentID(){
        return this.studentID;
    }

    public Date getDateRequested(){
        return this.dateRequested;
    }

    public String getStatus() {
        return this.status;
    }
}