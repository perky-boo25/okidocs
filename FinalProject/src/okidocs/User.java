package okidocs;

public class User {
    private final int studentID;
    private final String name;
    private final String password;

    public User(int studentID, String name, String password) {
        this.studentID = studentID;
        this.name = name;
        this.password = password;
    }

    public int getStudentID() {
        return this.studentID;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
}