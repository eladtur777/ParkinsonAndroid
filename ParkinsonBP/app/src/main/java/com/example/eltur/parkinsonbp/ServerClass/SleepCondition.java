package com.example.eltur.parkinsonbp.ServerClass;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.String.format;

/**
 * Created by Eltur on 13/06/2017.
 */

public class SleepCondition {



    private Long sleepConditionID;
    private String sleepHours;
    private String sleepQuality;

    private Collection<SleepDisorder> sleepDisorders = new ArrayList<>();

    public SleepCondition(){
    }

    public SleepCondition(String sleepHours, String sleepQuality, Collection<SleepDisorder> sleepDisorders){
        this.sleepHours = sleepHours;
        this.sleepQuality = sleepQuality;
        this.sleepDisorders.addAll(sleepDisorders);

    }

  //  public String getSleepConditionName() {
    //    return sleepConditionID;
    //}

    @Override
    public String toString(){
        return format("{sleepConditionID:\"%s\",sleepHours:\"%s\",sleepQuality:\"%s\",sleepDisorders:%s}",sleepConditionID,sleepHours,sleepQuality,
               sleepDisorders.toString());
    }



    public Long getSleepConditionID() {
        return sleepConditionID;
    }


    public String getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(String sleepHours) {
        this.sleepHours = sleepHours;
    }

    public String getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(String sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public Collection<SleepDisorder> getSleepDisorders() {
        return sleepDisorders;
    }

    public void setSleepDisorders(Collection<SleepDisorder> sleepDisorders) {
        this.sleepDisorders = sleepDisorders;
    }
}


