package okidocs;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Fake medical results used for demo and testing purposes.
 * This simulates data normally retrieved from the test_results table.
 */
public class FakeMedicalResults {

    public static List<MedicalResult> getResults() {

        List<MedicalResult> results = new ArrayList<>();
        Date today = Date.valueOf(LocalDate.now());

        // =========================
        // VITAL SIGNS
        // =========================
        results.add(new MedicalResult(
                "Blood Pressure",
                "Vital Signs",
                "120/80 mmHg",
                "90/60 – 120/80",
                "Normal",
                today,
                "Patient is within normal range."
        ));

        results.add(new MedicalResult(
                "Heart Rate",
                "Vital Signs",
                "72 bpm",
                "60 – 100 bpm",
                "Normal",
                today,
                "No abnormalities detected."
        ));

        results.add(new MedicalResult(
                "Body Temperature",
                "Vital Signs",
                "36.7 °C",
                "36.5 – 37.5 °C",
                "Normal",
                today,
                "Afebrile."
        ));

        // =========================
        // LABORATORY TESTS
        // =========================
        results.add(new MedicalResult(
                "Hemoglobin",
                "Laboratory",
                "13.9 g/dL",
                "12 – 16 g/dL",
                "Normal",
                today,
                "Hemoglobin level is normal."
        ));

        results.add(new MedicalResult(
                "White Blood Cell Count",
                "Laboratory",
                "6.2 x10⁹/L",
                "4 – 11 x10⁹/L",
                "Normal",
                today,
                "No signs of infection."
        ));

        // =========================
        // IMAGING
        // =========================
        results.add(new MedicalResult(
                "Chest X-Ray",
                "Imaging",
                "Clear",
                "N/A",
                "Normal",
                today,
                "No radiographic abnormalities."
        ));

        return results;
    }
}
