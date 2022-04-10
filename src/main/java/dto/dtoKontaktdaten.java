package dto;

public class dtoKontaktdaten {
    private int kontakt_id;
    private int telephon;
    private String email;
    private String straße;
    private String plz;
    private String stadt;

    public dtoKontaktdaten(int kontakt_id, int telephon, String email, String straße, String plz, String stadt) {
        this.kontakt_id = kontakt_id;
        this.telephon = telephon;
        this.email = email;
        this.straße = straße;
        this.plz = plz;
        this.stadt = stadt;
    }

    public int getKontakt_id() {
        return kontakt_id;
    }

    public int getTelephon() {
        return telephon;
    }

    public String getEmail() {
        return email;
    }

    public String getStraße() {
        return straße;
    }

    public String getPlz() {
        return plz;
    }

    public String getStadt() {
        return stadt;
    }
}
