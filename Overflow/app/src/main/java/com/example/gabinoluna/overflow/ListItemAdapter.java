package com.example.gabinoluna.overflow;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gabinoluna on 11/19/16.
 */


/**
 * Custom array adapter class to be used for our list that is used to present the invitations on the feed.
 */

public class ListItemAdapter extends ArrayAdapter<Sensor> {
    private Context context;
    private ArrayList<Sensor> sensors;

    public ListItemAdapter(Context context, int resource, ArrayList<Sensor> sensors) {

        super(context, resource, sensors);
        this.context = context;
        this.sensors = sensors;
    }

    /*
     *  start getView(int position, View convertView, ViewGroup parent)
     * Generates the view for each row in the list
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        final Sensor sensor = sensors.get(position); // get the sensor to display

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.sensorlistitem, null); // ***********************************

        /*
         * TextViews
         */
        final TextView tvSensorInfo = (TextView) view.findViewById(R.id.tvSensorInfo);

        /*
         * Buttons
         */


        /*
         * setting the text
         */

        tvSensorInfo.setText(sensor.toString());

//            tvInvitation.setText(sensor.toString());
//
//            btnJoin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String currentCount = (String)tvJoinCtr.getText();
//                    Integer count = Integer.valueOf(currentCount);
//                    if (count == 0)
//                        count++;
//                    else
//                        count = 0;
//
//                    tvJoinCtr.setText(count.toString());
//                    int invitationID = invitation.getInvitationID();
//                }
//
//            });
//
//            btnMaybe.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String currentCount = (String)tvMaybeCtr.getText();
//                    Integer count = Integer.valueOf(currentCount);
//
//                    if (count == 0)
//                        count++;
//                    else
//                        count = 0;
//
//                    tvMaybeCtr.setText(count.toString());
//                    int invitationID = invitation.getInvitationID();
//                }
//
//            });

        return view;
    }
    /*
     *  end getView(int position, View convertView, ViewGroup parent)
     */

}

