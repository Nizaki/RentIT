package tk.mdt60.rentit.utils;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

import tk.mdt60.rentit.Model.DataModel;
import tk.mdt60.rentit.R;

import static android.content.ContentValues.TAG;

public class CcAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener {
     ArrayList<DataModel> dataSet;
     Context mContext;
     int mH, mM;


    private static class ViewHolder {
        TextView txId;
        TextView txTime;
        Button btnRent;
    }

    public CcAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.roomitem, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        final DataModel dataModel = (DataModel) object;

        switch (v.getId()) {
            case R.id.item_room_rent:
                TimePickerDialog timePicker = new TimePickerDialog(mContext,R.style.TimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dataModel.setHour(hourOfDay);
                        dataModel.setMiniute(minute);
                        mM = minute;
                        Log.d(TAG, "onTimeSet: " + mH + ":" + mM);
                    }
                }, mH, mM, true);
                timePicker.setTitle("ขอใช้จนถึง");
                timePicker.show();

                break;
        }
    }

    private int lastPosition = -1;
    private String addZero(int num){
        if (num >= 10)
            return String.valueOf(num);
        else
            return String.format("%02d", num);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Calendar c = Calendar.getInstance();
        int curH = c.get(Calendar.HOUR_OF_DAY);
        int curM = c.get(Calendar.MINUTE);
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.roomitem, parent, false);

            viewHolder.txId = convertView.findViewById(R.id.item_room_id);
            viewHolder.txTime = convertView.findViewById(R.id.item_room_stats);
            viewHolder.btnRent = (Button) convertView.findViewById(R.id.item_room_rent);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        viewHolder.txId.setText(dataModel.getId());

        if (curH<dataModel.getHour() || curH==dataModel.getHour()&&curM<dataModel.getMiniute()) {
            viewHolder.txTime.setTextColor(ContextCompat.getColor(mContext,R.color.colorInuse));
            viewHolder.txTime.setText("มีคนใช้อยู่ จนถึง " + addZero(dataModel.getHour()) + ":" + addZero(dataModel.getMiniute()));
        }
        else {
            viewHolder.txTime.setTextColor(ContextCompat.getColor(mContext,R.color.colorEmpty));
            viewHolder.txTime.setText("ห้องว่าง");
        }

        viewHolder.btnRent.setOnClickListener(this);
        viewHolder.btnRent.setTag(position);
        // Return the completed view to render on screen
        return convertView;

    }

}
