package tk.mdt60.rentit.DB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @PrimaryKey @NonNull
    private int rid;
    private String time;

    public User(int rid,String time){
        this.rid = rid;
        this.time = time;
    }

    public int getRid(){return rid;}

    public String getTime(){return time;}

    public void setRid(int rid){this.rid = rid;}

    public void setTime(String time){this.time = time;}
}
