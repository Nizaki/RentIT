package tk.mdt60.rentit.Activitys;

import android.content.SharedPreferences;
import android.database.SQLException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tk.mdt60.rentit.Module.RoomDetails;
import tk.mdt60.rentit.R;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private List<RoomDetails> rooms;
    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("tk.mdt60.rentit", MODE_PRIVATE);
        loadRoom();
        loadList();

    }
    private void loadRoom(){
        rooms = new ArrayList<RoomDetails>();
        RoomDetails Room = new RoomDetails();
        RoomDetails Room2 = new RoomDetails();
        RoomDetails Room3= new RoomDetails();
        Room.ID = 5567;
        rooms.add(Room);
        Room2.ID = 5568;
        rooms.add(Room2);
        Room3.ID = 5569;
        rooms.add(Room3);
    }
    private void loadList(){
        list = (ListView)findViewById(R.id.roomlist);
        ArrayAdapter<RoomDetails> adapter = new ArrayAdapter<RoomDetails>(this, R.layout.activity_main,rooms){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.roomitem,null);
                }

                TextView RoomID = (TextView)convertView.findViewById(R.id.item_room_id);
                RoomID.setText(String.valueOf(rooms.get(position).ID));

                return convertView;
            }
        };
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            //prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

}
