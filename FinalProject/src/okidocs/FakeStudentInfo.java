package okidocs;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Fake student info for demo purposes.
 */
public class FakeStudentInfo {

    public static StudentInfo get(int studentId) {

        return new StudentInfo(
                "Ryona Cassandra Honrado",
                studentId,
                Date.valueOf(LocalDate.now().minusDays(7)),
                Date.valueOf(LocalDate.now())
        );
    }
}
