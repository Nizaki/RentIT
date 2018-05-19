package tk.mdt60.rentit.Activitys;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import tk.mdt60.rentit.Module.RoomDetails;
import tk.mdt60.rentit.R;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private List<RoomDetails> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadRoom();
        loadList();
    }
    private void loadRoom(){
        rooms = new ArrayList<RoomDetails>();
        RoomDetails Room = new RoomDetails();
        Room.ID = 5567;
        rooms.add(Room);
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
}
