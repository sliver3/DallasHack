package com.example.gabinoluna.overflow;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    private static Activity MainActivity; // list activity to set adapter to
    private ArrayList<Sensor> sensorList; // list of type Invitation which holds last 20 invitations
//    private MFPPush push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        BMSClient.getInstance().initialize(this, BMSClient.REGION_US_SOUTH);
//
//        //Initialize client Push SDK for Java
//        MFPPush push = MFPPush.getInstance();
//        push.initialize(getApplicationContext(), "6782580a-addd-4914-8eb3-8cbce1083c41", "b8b768e5-fe15-4159-9c3b-0250642aa622");
//
//
//
//        //Register Android devices
//        push.registerDevice(new MFPPushResponseListener<String>() {
//            @Override
//            public void onSuccess(String deviceId) {
//                //handle success here
//            }
//
//            @Override
//            public void onFailure(MFPPushException ex) {
//                //handle failure here
//            }
//        });




        MainActivity = this;
        sensorList = new ArrayList<>();

        showFeed(); // show the feed jaherd
        setListListener();


    }

//
//    @Override
//    protected void onResume(){
//        super.onResume();
//
//        //Handles the notification when it arrives
//        MFPPushNotificationListener notificationListener = new MFPPushNotificationListener() {
//            @Override
//            public void onReceive(final MFPSimplePushNotification message) {
//                // Handle Push Notification
//            }
//        };
//        if(push != null) {
//            push.listen(notificationListener);
//        }
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (push != null) {
//            push.hold();
//        }
//    }

    /*
     * showFeed()
     * this function will fill the sensor list and display the list
     */
    public void showFeed() {
        sensorList.clear();
        fillSensorLists();
        this.setListAdapter(new ListItemAdapter(MainActivity, 0, sensorList)); // 2nd way of setting adapter
    }
    /*
     * end showFeed()
     */

    /*
     * fillSensorLists()
     * The following function will be used to fill the sensorList for testing purposes.
     */
    public void fillSensorLists() {
        for (Integer i = 1; i <= 20; i++) {
            int id = i;

            Sensor temp = new Sensor(id);
            sensorList.add(temp);
        }
    }
    /*
     * end of fillSensorLists()
     */

    /*
     * start of setListListener()
     */

    private void setListListener() {
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position = " + position);
            }
        });

    }

    /*
     * end of setListListener()
     */
}
