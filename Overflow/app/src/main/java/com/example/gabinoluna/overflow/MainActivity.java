package com.example.gabinoluna.overflow;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity implements AsyncResponse {

    private static Activity MainActivity; // list activity to set adapter to
    private ArrayList<Sensor> sensorList; // list of type Invitation which holds last 20 invitations
    private Button btnReset; // button to reset sensors
    private Button btnRefresh; // button to reset sensors
    private String request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity = this;
        sensorList = new ArrayList<>();
        btnReset = (Button) findViewById(R.id.btnReset);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        request = "";


        showFeed(); // show the feed jaherd
        setListListener();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < sensorList.size(); i++) {
                    HashMap postData = new HashMap(); // hashmap of the post data to be sent
                    request = "reset";

                    // object to execute task
                    PostResponseAsyncTask loginTask = new PostResponseAsyncTask(MainActivity.this, postData);
                    String getURL = "http://52.38.151.153/overflow/trigger.php?sensorID=" + sensorList.get(i).getIdString() + "&position=0";
                    System.out.println(getURL);
                    loginTask.execute(getURL);
                }
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

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
        HashMap postData = new HashMap(); // hashmap of the post data to be sent
        request = "info";
        // object to execute task
        PostResponseAsyncTask loginTask = new PostResponseAsyncTask(MainActivity.this, postData);
        loginTask.execute("http://52.38.151.153/overflow/sensors.php?request=info");
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

    /*
    * start method processFinish() which is called upon the completion of the async task
    */
    @Override
    public void processFinish(String output) {
        // if refreshing the feed
        if (request.equals("info")) {
            parseSensors(output);
        } else if (request.equals("reset")) {

        }

    }
    /*
     * end method processFinish()
     */

    /*
     * start parseSensors(String outputPOST)
     * which will return an arrayList of the current invitations
     */
    private void parseSensors(String outputPOST) {
        String[] invitationsInfo = outputPOST.split(",");
        for (int i = 0; i < invitationsInfo.length ; i++) {
            String[] temp = invitationsInfo[i].split(" ");
            int sensorID = Integer.valueOf(temp[0]);
            boolean position;
            if (temp[1].equals("1")) {
                position = true;
            } else {
                position = false;
            }
            String location = temp[2];

            Sensor tempSensor = new Sensor(sensorID, position, location);
            sensorList.add(tempSensor);
        }

        System.out.println(sensorList.size());

    }
}
