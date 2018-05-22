package tk.mdt60.rentit.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import tk.mdt60.rentit.Model.DataModel;
import tk.mdt60.rentit.R;

public class CcAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener {
    private ArrayList<DataModel> dataSet;
    Context mContext;

    private static class ViewHolder{
        TextView txId;
        TextView txTime;
    }
    public CcAdapter(ArrayList<DataModel> data,Context context){
        super(context, R.layout.roomitem, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;

        switch (v.getId())
        {
            case R.id.item_room_rent:
                Snackbar.make(v, "Release date " +dataModel.getId(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.roomitem, parent, false);
            viewHolder.txId = (TextView) convertView.findViewById(R.id.item_room_id);
            viewHolder.txTime = (TextView) convertView.findViewById(R.id.item_room_stats);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.txId.setText(dataModel.getId());
        viewHolder.txTime.setText(String.valueOf(dataModel.getTime()));
        // Return the completed view to render on screen
        return convertView;
    }
}
