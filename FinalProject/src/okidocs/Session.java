package okidocs;

public class Session {

    //Stores the currently logged-in student's ID
    private static int currentStudentId = -1;

    //sets the student ID after successful login
    public static void setStudentId(int studentId) {
        currentStudentId = studentId;
    }

    //Returns the current logged-in student ID
    public static int getStudentId() {
        return currentStudentId;
    }

    //Checks if a user is logged in
    public static boolean isLoggedIn() {
        return currentStudentId != -1;
    }

    //Clears session on logout
    public static void logout() {
        currentStudentId = -1;
    }
}
