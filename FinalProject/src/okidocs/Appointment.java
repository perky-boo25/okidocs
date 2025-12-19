package okidocs;

import java.sql.Date;

public class Appointment {
    private int appointmentId;
    private final int studentId;
    private final Date date;
    private final String timeSlot;
    private String status;

    public Appointment(int studentId, Date date, String timeSlot) {
        this.studentId = studentId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.status = "BOOKED";
    } 

    public Appointment(int appointmentId, int studentId, Date date, String timeSlot,String status) {
        this.studentId = studentId;
        this.date = date;
        this.timeSlot = timeSlot;
    } 

    public int getAppointmentId(){
        return this.appointmentId;
    }

    public int getstudentId(){
        return this.studentId;
    }

    public Date getDate(){
        return this.date;
    }

    public String gettimeSlot(){
        return this.timeSlot;
    }

    public String getStatus(){
        return this.status;
    }

    @Override
    public String toString() {
        return "Appointment #" + appointmentId + " (" + status + ")";
    }
}