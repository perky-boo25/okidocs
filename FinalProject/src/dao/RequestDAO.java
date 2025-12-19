package okidocs.dao;

import model.Request;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO extends DBConnection {

    public RequestDAO() {
        super(); // calls DatabaseConnection constructor
    }

    // Add a new request
    public void addRequest(Request req) {
        try {
            String sql = "INSERT INTO requests(studentID, dateRequested, status) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, req.getStudentID());
            pst.setDate(2, new java.sql.Date(req.getDateRequested().getTime()));
            pst.setString(3, req.getStatus());
            pst.executeUpdate();
            System.out.println("Request added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update request status
    public void updateStatus(int requestID, String newStatus) {
        try {
            String sql = "UPDATE requests SET status = ? WHERE requestID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newStatus);
            pst.setInt(2, requestID);
            pst.executeUpdate();
            System.out.println("Request status updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fetch requests for a specific student
    public List<Request> getRequestsByStudent(int studentID) {
        List<Request> requests = new ArrayList<>();
        try {
            String sql = "SELECT * FROM requests WHERE studentID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, studentID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int requestID = rs.getInt("requestID");
                java.sql.Date dateRequested = rs.getDate("dateRequested");
                String status = rs.getString("status");

                requests.add(new Request(requestID, studentID, new java.util.Date(dateRequested.getTime()), status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    // Fetch all requests (for staff)
    public List<Request> getAllRequests() {
        List<Request> requests = new ArrayList<>();
        try {
            String sql = "SELECT * FROM requests";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int requestID = rs.getInt("requestID");
                int studentID = rs.getInt("studentID");
                java.sql.Date dateRequested = rs.getDate("dateRequested");
                String status = rs.getString("status");

                requests.add(new Request(requestID, studentID, new java.util.Date(dateRequested.getTime()), status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }
}
