package okidocs.dao;

import model.TestResults;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestResultsDAO extends DBConnection {

    public TestResultsDAO() {
        super(); // calls DatabaseConnection constructor
    }

    // Add a new test result
    public void addTestResult(TestResults tr) {
        try {
            String sql = "INSERT INTO test_results(requestID, studentID, testName, resultName, normalRange, remarks, dateReleased, drRemarks) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, tr.getRequestID());
            pst.setInt(2, tr.getStudentID());
            pst.setString(3, tr.getTestName());
            pst.setString(4, tr.getResultName());
            pst.setString(5, tr.getNormalRange());
            pst.setString(6, tr.getRemarks());
            pst.setDate(7, new java.sql.Date(tr.getDateReleased().getTime()));
            pst.setString(8, tr.getDrRemarks());
            pst.executeUpdate();
            System.out.println("Test result added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fetch test results for a specific student
    public List<TestResults> getResultsByStudent(int studentID) {
        List<TestResults> results = new ArrayList<>();
        try {
            String sql = "SELECT * FROM test_results WHERE studentID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, studentID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int requestID = rs.getInt("requestID");
                String testName = rs.getString("testName");
                String resultName = rs.getString("resultName");
                String normalRange = rs.getString("normalRange");
                String remarks = rs.getString("remarks");
                java.sql.Date dateReleased = rs.getDate("dateReleased");
                String drRemarks = rs.getString("drRemarks");

                results.add(new TestResults(
                    requestID, studentID, testName, resultName,
                    normalRange, remarks, new java.util.Date(dateReleased.getTime()), drRemarks
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    // Fetch all test results (for staff)
    public List<TestResults> getAllResults() {
        List<TestResults> results = new ArrayList<>();
        try {
            String sql = "SELECT * FROM test_results";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int requestID = rs.getInt("requestID");
                int studentID = rs.getInt("studentID");
                String testName = rs.getString("testName");
                String resultName = rs.getString("resultName");
                String normalRange = rs.getString("normalRange");
                String remarks = rs.getString("remarks");
                java.sql.Date dateReleased = rs.getDate("dateReleased");
                String drRemarks = rs.getString("drRemarks");

                results.add(new TestResults(
                    requestID, studentID, testName, resultName,
                    normalRange, remarks, new java.util.Date(dateReleased.getTime()), drRemarks
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
