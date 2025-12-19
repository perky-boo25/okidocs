package okidocs;

public class Session {

    private static int currentStudentId = -1;

    public static void setStudentId(int studentId) {
        currentStudentId = studentId;
    }

    public static int getStudentId() {
        return currentStudentId;
    }

    public static boolean isLoggedIn() {
        return currentStudentId != -1;
    }

    public static void logout() {
        currentStudentId = -1;
    }
}
