package okidocs;

import java.sql.Date;

public class StudentInfo {

    private final String name;
    private final String int studentId;
    private final Date dateRequested;
    private final Date dateReleased;

    public StudentInfo(String name, int studentId, Date dateRequested, Date dateReleased){
        this.name = name;
        this.studentID = studentId;
        this.dateRequested = dateRequested;
        this.dateReleased = dateReleased;
    }

    public String getName() {
        return name;
    }

    public int getStudentId() {
        return studentId;
    }

    public Date getDateRequested() {
        return dateRequested;
    }

    public Date getDateReleased() {
        return dateReleased;
    }

    
}
