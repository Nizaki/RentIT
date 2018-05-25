package tk.mdt60.rentit.Activitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import tk.mdt60.rentit.Model.DataModel;
import tk.mdt60.rentit.R;
import tk.mdt60.rentit.utils.CcAdapter;

public class MainActivity extends AppCompatActivity {
    public ArrayList<DataModel> dataModels;
    ArrayList<DataModel> sdataModels;
    ListView listView;
    SwipeRefreshLayout sRefresh;
    String pattern = "HH:mm";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    Date def;
    Date curTime;
    private static CcAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar c = Calendar.getInstance();
        listView = (ListView) findViewById(R.id.roomlist);
        sRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        dataModels = new ArrayList<>();
        fRun();
        adapter = new CcAdapter(dataModels, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.wtf("Debug", "Why not appear wtf");
                DataModel dataModel = dataModels.get(position);
            }
        });

        sRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateListview();
            }
        });
    }
    private  void updateListview(){
        adapter.notifyDataSetChanged();
        sRefresh.setRefreshing(false);
        saveData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    private void fRun() {
        SharedPreferences prefs = null;
        prefs = getSharedPreferences("tk.mdt60.rentit", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).apply();
            Log.d("Debug", "fRun: Detect");
            addRoom("LAB1");
            addRoom("201");
            addRoom("202");
            addRoom("203");
            addRoom("204");
            addRoom("205");
            addRoom("301");
            addRoom("302");
            saveData();
            loadData();
        }
        else {
            Log.d("Debug", "fRun: Load Data");
            loadData();
        }
    }
    public void addRoom(String id){
        dataModels.add(new DataModel(id,"use",0,0));
    }
    /**
     * Save and get ArrayList in SharedPreference
     **/

    private void saveData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dataModels);
        editor.putString("example", json);
        editor.apply();

    }

    private void loadData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sp.getString("example", null);
        Type type = new TypeToken<ArrayList<DataModel>>() {
        }.getType();
        dataModels = gson.fromJson(json, type);
    }
}
