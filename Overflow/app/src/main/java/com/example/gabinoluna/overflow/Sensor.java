package com.example.gabinoluna.overflow;

/**
 * Created by gabinoluna on 11/19/16.
 */

public class Sensor {
    private String status;
    private int id;
    private boolean on;
    private String location;
    private String picName;

    public Sensor(int id) {
        this.status = "Sensor is on and clear";
        this.on = true;
        this.id = id;
        this.location = "location " + id;
        if (on) {
            picName = "status_good";
        }
        else {
            picName = "status_bad";
        }
    }

    public Sensor(int id, boolean on, String location) {
        this.id = id;
        this.on = on;
        this.location = location;
        this.status = "Sensor is on and clear";
        if (id == 3) {
            picName = "status_bad";
        }
        else {
            picName = "status_good";
        }


    }

    public Sensor(String status, int id, boolean on, String location) {
        this.status = status;
        this.id = id;
        this.on = on;
        this.location = location;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getIdString() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        String sensorStatus;
        if (on)
            sensorStatus = "Sensor: " + id + "\nLocation: " + location + "\nStatus: On";
        else {
            sensorStatus = "Sensor: " + id + "\nLocation: " + location + "\nStatus: Off";

        }

        return sensorStatus;
    }
}
