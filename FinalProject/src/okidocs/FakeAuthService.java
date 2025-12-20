package okidocs;

import java.util.HashMap;
import java.util.Map;

public class FakeAuthService {

    // studentId -> password
    private static final Map<Integer, String> accounts = new HashMap<>();

    // studentId -> name
    private static final Map<Integer, String> names = new HashMap<>();

    static {
        // PRE-LOADED TEST ACCOUNT
        accounts.put(20250001, "password");
        names.put(20250001, "Test Student");
    }

    public static boolean register(int studentId, String name, String password) {
        if (accounts.containsKey(studentId)) {
            return false; // already exists
        }
        accounts.put(studentId, password);
        names.put(studentId, name);
        return true;
    }

    public static boolean login(int studentId, String password) {
        return accounts.containsKey(studentId)
                && accounts.get(studentId).equals(password);
    }

    public static String getName(int studentId) {
        return names.get(studentId);
    }
}
