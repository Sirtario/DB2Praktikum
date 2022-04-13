package Kiss.dto;

public class dtoRoom {
    private int room_id;
    private String room_typ;
    private boolean status;
    private int abteilunsnummer;

    public dtoRoom(int room_id, String room_typ, boolean status, int abteilunsnummer) {
        this.room_id = room_id;
        this.room_typ = room_typ;
        this.status = status;
        this.abteilunsnummer = abteilunsnummer;
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getRoom_typ() {
        return room_typ;
    }

    public boolean isStatus() {
        return status;
    }

    public int getAbteilunsnummer() {
        return abteilunsnummer;
    }
}
