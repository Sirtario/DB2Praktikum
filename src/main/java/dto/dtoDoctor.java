package dto;

import java.sql.Date;

public class dtoDoctor {
    private int doctor_id;
    private String vorname;
    private String nachname;
    private String geschlecht;
    private java.sql.Date geburstag;
    private String spezialist;
    private int kontakt_id;

    public dtoDoctor(int doctor_id, String vorname, String nachname, String geschlecht, Date geburstag, String spezialist, int kontakt_id) {
        this.doctor_id = doctor_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.geburstag = geburstag;
        this.spezialist = spezialist;
        this.kontakt_id = kontakt_id;
    }

    public int getDoctor_id() {
        return doctor_id;
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

    public String getSpezialist() {
        return spezialist;
    }

    public int getKontakt_id() {
        return kontakt_id;
    }
}
