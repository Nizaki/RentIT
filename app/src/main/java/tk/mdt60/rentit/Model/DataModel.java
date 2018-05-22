package tk.mdt60.rentit.Model;

public class DataModel {
    String id;
    String time;

    public DataModel(String id,String time){
        this.id = id;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}
