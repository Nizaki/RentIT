package tk.mdt60.rentit;

import android.app.Application;
import android.content.SharedPreferences;
import android.widget.Toast;

public class MainApplication extends Application {
    private static MainApplication mContext;
    SharedPreferences prefs = null;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"GGEZ",Toast.LENGTH_LONG).show();
    }

    public  static MainApplication getmContext(){
        return mContext;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
