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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity = this;
        sensorList = new ArrayList<>();

        showFeed(); // show the feed jaherd
        setListListener();


    }


    /*
     * showFeed()
     * this function will fill the sensor list and display the list
     */
    public void showFeed () {
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
