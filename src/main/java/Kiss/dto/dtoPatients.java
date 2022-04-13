package Kiss.dto;

import java.sql.Date;

public class dtoPatients {
    private int patient_id;
    private String vorname;
    private String nachname;
    private String geschlecht;
    private java.sql.Date geburstag;
    private boolean stationaer;
    private int versicherungs_id;
    private int behandlungs_id;
    private int lab_id;
    private int kontakt_id;
    private int doctor_id;

    public dtoPatients(int patient_id, String vorname, String nachname, String geschlecht, Date geburstag, boolean stationaer, int versicherungs_id, int behandlungs_id, int lab_id, int kontakt_id, int doctor_id) {
        this.patient_id = patient_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.geburstag = geburstag;
        this.stationaer = stationaer;
        this.versicherungs_id = versicherungs_id;
        this.behandlungs_id = behandlungs_id;
        this.lab_id = lab_id;
        this.kontakt_id = kontakt_id;
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public Date getGeburstag() {
        return geburstag;
    }

    public boolean isStationaer() {
        return stationaer;
    }

    public int getVersicherungs_id() {
        return versicherungs_id;
    }

    public int getBehandlungs_id() {
        return behandlungs_id;
    }

    public int getLab_id() {
        return lab_id;
    }

    public int getKontakt_id() {
        return kontakt_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }
}
