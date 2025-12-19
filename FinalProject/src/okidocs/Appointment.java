import java.sql.Date;

public class Appointment {
    private int appointmentID;
    private int studentID;
    private Date date;
    private String timeslot;
    private String status;

    public Appointment(int studentID, Date date, String timeslot) {
        this.studentID = studentID;
        this.date = date;
        this.timeslot = timeslot;
        this.status = "Booked";
    } 

    public Appointment(int appointmentID, int studentID, Date date, String timeslot,String status) {
        this.studentID = studentID;
        this.date = date;
        this.timeslot = timeslot;
    } 

    public int getAppointmentID(){
        return this.appointmentID;
    }

    public int getstudentID(){
        return this.studentID;
    }

    public Date getDate(){
        return this.date;
    }

    public String getTimeslot(){
        return this.timeslot;
    }

    public String getStatus(){
        return this.status;
    }
}