package dto;

public class dtoBeds {
    private int bed_id;
    private int room_id;

    public dtoBeds(int bed_id, int room_id) {
        this.bed_id = bed_id;
        this.room_id = room_id;
    }

    public int getBed_id() {
        return bed_id;
    }

    public int getRoom_id() {
        return room_id;
    }
}
