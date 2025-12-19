package okidocs;

import java.util.ArrayList;
import java.util.List;

/**
 * Fake medical results for demo purposes only.
 * This simulates data normally retrieved from the database.
 */
public class FakeMedicalResults {

    public static List<MedicalResult> getResults(String category) {

        List<MedicalResult> results = new ArrayList<>();

        switch (category) {

            case "VITALS":
                results.add(new MedicalResult(
                        "Blood Pressure",
                        "120/80 mmHg",
                        "90/60 – 120/80",
                        "Normal"
                ));
                results.add(new MedicalResult(
                        "Heart Rate",
                        "72 bpm",
                        "60 – 100 bpm",
                        "Normal"
                ));
                results.add(new MedicalResult(
                        "Temperature",
                        "36.6 °C",
                        "36.5 – 37.5 °C",
                        "Normal"
                ));
                results.add(new MedicalResult(
                        "BMI",
                        "21.5",
                        "18.5 – 24.9",
                        "Normal"
                ));
                break;

            case "CBC":
                results.add(new MedicalResult(
                        "Hemoglobin",
                        "13.8 g/dL",
                        "12 – 16 g/dL",
                        "Normal"
                ));
                results.add(new MedicalResult(
                        "White Blood Cells",
                        "6.5 x10⁹/L",
                        "4 – 11 x10⁹/L",
                        "Normal"
                ));
                results.add(new MedicalResult(
                        "Platelets",
                        "250 x10⁹/L",
                        "150 – 450 x10⁹/L",
                        "Normal"
                ));
                break;

            case "URINALYSIS":
                results.add(new MedicalResult(
                        "Protein",
                        "Negative",
                        "Negative",
                        "Normal"
                ));
                results.add(new MedicalResult(
                        "Glucose",
                        "Negative",
                        "Negative",
                        "Normal"
                ));
                results.add(new MedicalResult(
                        "Red Blood Cells",
                        "0 – 1 /hpf",
                        "0 – 2 /hpf",
                        "Normal"
                ));
                break;

            case "XRAY":
                results.add(new MedicalResult(
                        "Chest X-Ray",
                        "Clear",
                        "N/A",
                        "No abnormal findings"
                ));
                break;

            default:
                // return empty list if category not recognized
                break;
        }

        return results;
    }
}
