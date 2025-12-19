package okidocs;

public class User {
    private int studentID;
    private String name;
    private String email;

    public User(int studentID, String name, String email) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
    }

    public int getStudentID() {
        return this.studentID;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
}