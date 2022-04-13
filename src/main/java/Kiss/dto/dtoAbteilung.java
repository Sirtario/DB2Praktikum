package Kiss.dto;

public class dtoAbteilung {
    private int abteilungsnummer;
    private String abteilungsdescrip;
    private String abteilungslocation;
    private String abteilungsname;

    public dtoAbteilung(int abteilungsnummer, String abteilungsdescrip, String abteilungslocation, String abteilungsname) {
        this.abteilungsnummer = abteilungsnummer;
        this.abteilungsdescrip = abteilungsdescrip;
        this.abteilungslocation = abteilungslocation;
        this.abteilungsname = abteilungsname;
    }

    public int getAbteilungsnummer() {
        return abteilungsnummer;
    }

    public String getAbteilungsdescrip() {
        return abteilungsdescrip;
    }

    public String getAbteilungslocation() {
        return abteilungslocation;
    }

    public String getAbteilungsname() {
        return abteilungsname;
    }
}
