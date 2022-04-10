package dto;

import java.sql.Date;

public class dtoLabor {
    private int lab_id;
    private java.sql.Date datum;
    private String menge;
    private int doctor_id;

    public dtoLabor(int lab_id, Date datum, String menge, int doctor_id) {
        this.lab_id = lab_id;
        this.datum = datum;
        this.menge = menge;
        this.doctor_id = doctor_id;
    }

    public int getLab_id() {
        return lab_id;
    }

    public Date getDatum() {
        return datum;
    }

    public String getMenge() {
        return menge;
    }

    public int getDoctor_id() {
        return doctor_id;
    }
}
