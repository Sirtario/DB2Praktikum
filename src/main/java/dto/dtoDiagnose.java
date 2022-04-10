package dto;

public class dtoDiagnose {
    private int diagnose_id;
    private int patient_id;

    public dtoDiagnose(int diagnose_id, int patient_id) {
        this.diagnose_id = diagnose_id;
        this.patient_id = patient_id;
    }

    public int getDiagnose_id() {
        return diagnose_id;
    }

    public int getPatient_id() {
        return patient_id;
    }
}
