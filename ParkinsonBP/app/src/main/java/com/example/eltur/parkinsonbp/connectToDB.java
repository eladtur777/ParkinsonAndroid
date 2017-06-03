package com.example.eltur.parkinsonbp;

import com.example.eltur.parkinsonbp.HttpClient.HttpClient;
import com.example.eltur.parkinsonbp.ServerClass.PatientRecord;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.lang.Object;
import java.util.ListIterator;


/**
 * Created by Eltur on 28/05/2017.
 */

public class connectToDB {



    public static ArrayList<String> getActivitiesArray() {
        return ActivitiesArray;
    }

    public static void setActivitiesArray(ArrayList<String> activitiesArray) {
        ActivitiesArray = activitiesArray;
    }

    private static ArrayList<String> ActivitiesArray = new ArrayList<>();


    public static String AddActivities(String patientID, ArrayList<String> userActivity) {
        boolean serverResponde = false;
        PatientRecord content = new PatientRecord();
        content.setPatientLastUpdate(new Date());
        content.setPatientID(patientID);
        Activity[] Activitiesjson = HttpClient.getJson();
        int i =0;
        for ( i=0 ; i < userActivity.size(); i++) {
            if (!userActivity.get(i).toString().isEmpty()) {

                Activitiesjson[i].setActivityName(userActivity.get(i).toString());
                Activitiesjson[i].setActivityType("ספורט ופנאי");
                Activitiesjson[i].setActivityLemitation("ללא");
            }
        }

        Collection<Activity> collection;
        ArrayList json2 = new ArrayList<>();
        Collections.addAll(json2,Activitiesjson);
        collection = json2;
        Iterator<Activity> iterator = collection.iterator();
        for (int j =0 ; j < i; j++) {
            iterator.next(); // ignore the first x values
        }

        while(iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }

        content.setListOfActivitiy(collection);

        try {
            serverResponde =  HttpClient.getClient().SendPatientRecordToServer("http://10.0.2.2:8080/BEAT-PD/User/Update/PatientRecord/ActivitiesAndMedicines", content);
        } catch (MalformedURLException | JsonProcessingException ex) {
            System.out.println(String.format("Error:%s", ex.getMessage()));
        }
        json2.clear();
        for (int j =0 ; iterator.hasNext(); j++) {
            iterator.remove();
            iterator.next();
        }

        collection.clear();
        if (serverResponde) {
            return "Success";
        } else {
            return "Faild";
        }
    }






    public static ArrayList<String> getAllActivies() {
        try {
            ActivitiesArray.clear();
            ActivitiesArray = HttpClient.getClient().GetAllActiviesFromServer("http://10.0.2.2:8080/BEAT-PD/User/GET/AllActivities/");
        } catch (MalformedURLException ex) {
            System.out.println(String.format("Error:%s", ex.getMessage()));
        }

        return ActivitiesArray;
    }


  //  public static void main(String args[]) {

//        //add list of activities of patient to Activity class list
//        PatientRecord content = new PatientRecord();
//        content.setPatientID("1");
//        // moodAndAction activities = new moodAndAction();
//        Activity addNewActivity = new Activity("test13","test","test");
//        //addNewActivity.AddActivitiesToList(activities.getActivityList());
//        content.getListOfActivitiy().add(addNewActivity);
//        content.setPatientLastUpdate(new Date());
//        HttpClient httpClient = HttpClient.getClient();
//        try{
//            httpClient.SendPatientRecordToServer("http://localhost:8080/BEAT-PD/User//Update/PatientRecord/ActivitiesAndMedicines",content);
//        }catch (MalformedURLException | JsonProcessingException ex){
//            System.out.println(String.format("Error:%s",ex.getMessage()));
//        }
        // try{

        //    ActivitiesArray = HttpClient.getClient().GetAllActiviesFromServer("http://localhost:8080/BEAT-PD/User/GET/AllActivities/");
        //  }catch(MalformedURLException ex){
        //     System.out.println(String.format("Error:%s",ex.getMessage()));
        //  }

        // int i =5;
        // return ActivitiesArray;
        //}
      //  ArrayList<String> userAct = new ArrayList<>();
       // userAct.add("בדיקהבדיקהבדיקה");
      //  userAct.add("בדיקהבדיקה");
       // userAct.add("בדיקהבדיקה");
       // userAct.add("בדיקהבדיקה");
      //  userAct.add("בדיקהבדיקה");
       // userAct.add("elad test");
      //  userAct.add("eladtest");

     //   getAllActivies();
     //   AddActivities("1", userAct);

   // }
}




