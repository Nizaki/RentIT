package tk.mdt60.rentit.Model;

import java.util.Date;

public class DataModel {
    String id;
    String status;
    int hour;
    int miniute;

    public DataModel(String id, String status, int hour, int miniute) {
        this.id = id;
        this.status = status;
        this.hour = hour;
        this.miniute = miniute;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getHour() {
        return hour;
    }

    public int getMiniute() {
        return miniute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMiniute(int miniute) {
        this.miniute = miniute;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
