package tk.mdt60.rentit.Activitys;

import android.content.SharedPreferences;
import android.os.Bundle;
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

        int Hr24=c.get(Calendar.HOUR_OF_DAY);
        int Min=c.get(Calendar.MINUTE);
        Toast.makeText(this,"Time: "+Hr24+Min,Toast.LENGTH_LONG).show();
        listView = (ListView) findViewById(R.id.roomlist);
        sRefresh =(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        dataModels = new ArrayList<>();
        SharedPreferences prefs = null;
        prefs = getSharedPreferences("tk.mdt60.rentit", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", false)) {
            fRun();
            prefs.edit().putBoolean("firstrun", true).commit();
            saveData();
        }


        loadData();
        adapter = new CcAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.wtf("Debug", "Why not appear wtf");
                DataModel dataModel = dataModels.get(position);

                Snackbar.make(view, dataModel.getId() + "\n Time : " + dataModel.getHour() + dataModel.getMiniute(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
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
    }
    private void fRun() {
        dataModels.clear();
        try {
            def = sdf.parse("00:00");
        } catch (ParseException e) {
        }
        sdataModels.add(new DataModel("201", "Test1", 0,0));
        sdataModels.add(new DataModel("202", "Test2", 0,0));
        sdataModels.add(new DataModel("203", "Test3", 0,0));
        sdataModels.add(new DataModel("Lab Bone", "Test4", 0,0));
        sdataModels.add(new DataModel("Lab XXX", "Test5", 0,0));
    }

    /**
     * Save and get ArrayList in SharedPreference
     **/

    private void saveData() {
        SharedPreferences sp = getSharedPreferences("test", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(sdataModels);
        editor.putString("example", json);
        editor.apply();

    }

    private void loadData() {
        SharedPreferences sp = getSharedPreferences("test", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("example", null);
        Type type = new TypeToken<ArrayList<DataModel>>() {
        }.getType();
        dataModels = gson.fromJson(json, type);
    }
}
