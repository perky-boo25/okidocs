package okidocs.dao;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DBConnection {

    public UserDAO() {
        super(); // calls DatabaseConnection constructor
    }

    // Add a new user
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users(studentID, name, email) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, user.getStudentID());
            pst.setString(2, user.getName());
            pst.setString(3, user.getEmail());
            pst.executeUpdate();
            System.out.println("User added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fetch a user by studentID
    public User getUserByID(int studentID) {
        User user = null;
        try {
            String sql = "SELECT * FROM users WHERE studentID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, studentID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                user = new User(studentID, name, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Fetch all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int studentID = rs.getInt("studentID");
                String name = rs.getString("name");
                String email = rs.getString("email");
                users.add(new User(studentID, name, email));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // Update user email
    public void updateEmail(int studentID, String newEmail) {
        try {
            String sql = "UPDATE users SET email = ? WHERE studentID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newEmail);
            pst.setInt(2, studentID);
            pst.executeUpdate();
            System.out.println("Email updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
