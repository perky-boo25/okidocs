package okidocs;

import java.util.ArrayList;
import java.util.List;

/**
 * Fake medical results (demo data only)
 */
public class FakeMedicalResults {

    public static List<MedicalResult> getResults(String category) {

        List<MedicalResult> list = new ArrayList<>();

        switch (category) {

            case "VITALS" -> {
                list.add(new MedicalResult("Blood Pressure", "120/80 mmHg", "90/60–120/80", "Normal"));
                list.add(new MedicalResult("Heart Rate", "72 bpm", "60–100 bpm", "Normal"));
                list.add(new MedicalResult("Temperature", "36.6 °C", "36.5–37.5 °C", "Normal"));
                list.add(new MedicalResult("BMI", "21.5", "18.5–24.9", "Normal"));
            }

            case "CBC" -> {
                list.add(new MedicalResult("Hemoglobin", "13.8 g/dL", "12–16", "Normal"));
                list.add(new MedicalResult("WBC", "6.5 x10⁹/L", "4–11", "Normal"));
                list.add(new MedicalResult("Platelets", "250 x10⁹/L", "150–450", "Normal"));
            }

            case "URINALYSIS" -> {
                list.add(new MedicalResult("Protein", "Negative", "Negative", "Normal"));
                list.add(new MedicalResult("Glucose", "Negative", "Negative", "Normal"));
                list.add(new MedicalResult("RBC", "0–1", "0–2", "Normal"));
            }

            case "XRAY" -> {
                list.add(new MedicalResult("Chest X-Ray", "Normal", "-", "No findings"));
            }
        }

        return list;
    }
}
