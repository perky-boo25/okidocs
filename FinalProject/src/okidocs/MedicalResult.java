package okidocs;

/**
 * Represents a single medical test result.
 * Used by ViewResults, TestResultDAO, and FakeMedicalResults.
 */
public class MedicalResult {

    private final String testName;
    private final String resultValue;
    private final String normalRange;
    private final String remarks;

    public MedicalResult(String testName,
                         String resultValue,
                         String normalRange,
                         String remarks) {
        this.testName = testName;
        this.resultValue = resultValue;
        this.normalRange = normalRange;
        this.remarks = remarks;
    }

    public String getTestName() {
        return testName;
    }

    public String getResultValue() {
        return resultValue;
    }

    public String getNormalRange() {
        return normalRange;
    }

    public String getRemarks() {
        return remarks;
    }

    @Override
    public String toString() {
        return testName + ": " + resultValue +
               " (Normal: " + normalRange + ") - " + remarks;
    }
}
