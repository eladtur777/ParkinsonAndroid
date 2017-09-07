package com.example.eltur.parkinsonbp.ServerClass;

import java.util.ArrayList;
import java.util.Collection;


public class PatientRecord {

    private String patientID;
    private String patientLastUpdate;//format yyyy-mm-dd

    public Collection<ActivityUpdate> getListOfActivityUpdate() {
        return listOfActivityUpdate;
    }

    public void setListOfActivityUpdate(Collection<ActivityUpdate> listOfActivityUpdate) {
        this.listOfActivityUpdate = listOfActivityUpdate;
    }

    private Collection<ActivityUpdate> listOfActivityUpdate = new ArrayList();

    public Collection<Medicine> getListOfMedicineUpdate() {
        return listOfMedicineUpdate;
    }

    public void setListOfMedicineUpdate(Collection<Medicine> listOfMedicineUpdate) {
        this.listOfMedicineUpdate = listOfMedicineUpdate;
    }

    private Collection<Medicine> listOfMedicineUpdate = new ArrayList();

    public Collection<HabitUpdate> getListOfHabitUpdate() {
        return listOfHabitUpdate;
    }

    public void setListOfHabitUpdate(Collection<HabitUpdate> listOfHabitUpdate) {
        this.listOfHabitUpdate = listOfHabitUpdate;
    }

    private Collection<HabitUpdate> listOfHabitUpdate = new ArrayList<>();
    private Collection<MoodCondition> listOfMoodCondition = new ArrayList<>();
    private Collection<SleepDisorder> listOfSleepDisorders = new ArrayList<>();
    private SleepCondition sleepCondition ;

    public SleepCondition getSleepCondition() {
        return sleepCondition;
    }

    public void setSleepCondition(SleepCondition sleepCondition) {
        this.sleepCondition = sleepCondition;

    }



    public Collection<SleepDisorder> getListOfSleepDisorders() {
        return listOfSleepDisorders;
    }

    public void setListOfSleepDisorders(Collection<SleepDisorder> listOfSleepDisorders) {
        this.listOfSleepDisorders = listOfSleepDisorders;
    }




    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    // private Long patientRecordID;
    //key + login ID

    public String getPatientLastUpdate() {
        return patientLastUpdate;
    }

    public void setPatientLastUpdate(String patientLastUpdate) {
        this.patientLastUpdate = patientLastUpdate;
    }









    public Collection<MoodCondition> getListOfMoodCondition() {
        return listOfMoodCondition;
    }

    public void setListOfMoodCondition(Collection<MoodCondition> listOfMoodCondition) {
        this.listOfMoodCondition = listOfMoodCondition;
    }



        @Override
    public String toString(){
        return String.format("{patientID:%s,patientLastUpdate:%s,listOfActivityUpdate:%s,listOfMedicineUpdate:%s,listOfHabitUpdate:%s,listOfMoodCondition:%s,sleepCondition:%s}",
                patientID,patientLastUpdate,listOfActivityUpdate,listOfMedicineUpdate, listOfHabitUpdate, listOfMoodCondition, sleepCondition);

    }



        public PatientRecord(){
           // patientLastUpdate = new Date();
        }

    public PatientRecord(String patientID, String patientLastUpdate, Collection<ActivityUpdate> listOfActivitiy, Collection<Medicine> listOfMedicine,Collection<HabitUpdate> listOfHabit,Collection<MoodCondition> listOfMoodCondition,Collection<SleepCondition> listOfSleepConditionCondition) {
        this.patientID = patientID;
        this.patientLastUpdate = patientLastUpdate;
        this.listOfActivityUpdate = listOfActivitiy;
        this.listOfMedicineUpdate = listOfMedicine;
        this.listOfHabitUpdate = listOfHabit;
        this.listOfMoodCondition = listOfMoodCondition;
       // this.sleepCondition = listOfSleepConditionCondition;

    }


}
