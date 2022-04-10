package dto;

public class dtoRechnung {
    private int rechnung_id;
    private int no_of_days;
    private int lab_id;
    private int room_id;
    private int doctor_id;
    private int patient_id;

    public dtoRechnung(int rechnung_id, int no_of_days, int lab_id, int room_id, int doctor_id, int patient_id) {
        this.rechnung_id = rechnung_id;
        this.no_of_days = no_of_days;
        this.lab_id = lab_id;
        this.room_id = room_id;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
    }

    public int getRechnung_id() {
        return rechnung_id;
    }

    public int getNo_of_days() {
        return no_of_days;
    }

    public int getLab_id() {
        return lab_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }
}
