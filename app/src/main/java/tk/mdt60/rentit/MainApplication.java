package tk.mdt60.rentit;

import android.app.Application;
import android.database.SQLException;
import android.widget.Toast;

import java.io.IOException;

import tk.mdt60.rentit.DB.DataBaseHelper;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"GGEZ",Toast.LENGTH_LONG).show();
        LoadDB();
    }

    private void LoadDB() {
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
