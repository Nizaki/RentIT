package tk.mdt60.rentit.Activitys;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import tk.mdt60.rentit.Model.DataModel;
import tk.mdt60.rentit.R;
import tk.mdt60.rentit.utils.CcAdapter;

public class MainActivity extends AppCompatActivity {
    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CcAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.roomlist);
        dataModels= new ArrayList<>();
        dataModels.add(new DataModel("201","Test1"));
        dataModels.add(new DataModel("202","Test2"));
        dataModels.add(new DataModel("203","Test3"));
        dataModels.add(new DataModel("Lab Bone","Test4"));
        dataModels.add(new DataModel("Lab XXX","Test5"));

        adapter= new CcAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataModel dataModel= dataModels.get(position);

                Snackbar.make(view, dataModel.getId()+"\n"+dataModel.getTime()+" API: ", Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

    /*
    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            //prefs.edit().putBoolean("firstrun", false).commit();
        }
    }
    */
    /**
     *     Save and get ArrayList in SharedPreference
     */
    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
