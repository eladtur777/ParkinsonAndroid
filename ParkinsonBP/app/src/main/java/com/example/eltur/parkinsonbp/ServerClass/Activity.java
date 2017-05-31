package com.example.eltur.parkinsonbp.ServerClass;

import java.util.ArrayList;

/**
 * Created by Eltur on 28/05/2017.
 */

public class Activity  {

    private String activityName;
    private String activityType;//TODO need to add enum
    private String activityLemitation;

    @Override
    public String toString(){
        return String.format("{activityName:%s,activityType:%s,activityLemitation:%s}", activityName, activityType, activityLemitation);
    }
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityLemitation() {
        return activityLemitation;
    }

    public void setActivityLemitation(String activityLemitation) {
        this.activityLemitation = activityLemitation;
    }


        public Activity(String activityName, String activityType, String activityLemitation) {
            this.activityName = activityName;//TODO insert Activity list from user choices
            this.activityType = activityType;
            this.activityLemitation = activityLemitation;
        }

    public Activity() {

    }

        public void AddActivitiesToList(ArrayList<String> activities)
        {
            for(int i=0;i<activities.size();i++)
            {
                if (!activities.get(i).isEmpty())
                this.activityName = activities.get(i).toString();
                this.activityType = "ספורט ופנאי";
                this.activityLemitation ="ללא" ;
            }

        }

    }
