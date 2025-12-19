package okidocs;

import java.sql.Date;

public class StudentInfoDAO {
    public static StudentInfo get(int id) {
        return new StudentInfo(
            "John Doe",
            id,
            Date.valueOf("2025-09-01"),
            Date.valueOf("2025-09-10")
        );
    } 
}
