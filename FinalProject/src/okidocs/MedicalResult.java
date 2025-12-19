package okidocs;

import java.sql.Date;

public class MedicalResult {

    private final String testName;
    private final String category;
    private final String resultValue;
    private final String normalRange;
    private final String remarks;
    private final Date dateReleased;
    private final String doctorRemark;

    public MedicalResult(
            String testName,
            String category,
            String resultValue,
            String normalRange,
            String remarks,
            Date dateReleased,
            String doctorRemark) {

        this.testName = testName;
        this.category = category;
        this.resultValue = resultValue;
        this.normalRange = normalRange;
        this.remarks = remarks;
        this.dateReleased = dateReleased;
        this.doctorRemark = doctorRemark;
    }

    public String getTestName() {
        return testName;
    }

    public String getCategory() {
        return category;
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

    public Date getDateReleased() {
        return dateReleased;
    }

    public String getDoctorRemark() {
        return doctorRemark;
    }
}


