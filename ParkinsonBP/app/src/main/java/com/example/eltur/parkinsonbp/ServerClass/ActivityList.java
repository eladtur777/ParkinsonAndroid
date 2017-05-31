package com.example.eltur.parkinsonbp.ServerClass;

import com.example.eltur.parkinsonbp.Activity;

import java.util.ArrayList;

/**
 * Created by liran on 5/31/17.
 */

public class ActivityList {
    private ArrayList<Activity> activities;

    public ActivityList(){}

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }
}
