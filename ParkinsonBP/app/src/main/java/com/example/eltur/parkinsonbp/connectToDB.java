package com.example.eltur.parkinsonbp;

import com.example.eltur.parkinsonbp.HttpClient.HttpClient;
import com.example.eltur.parkinsonbp.ServerClass.Activity;
import com.example.eltur.parkinsonbp.ServerClass.PatientRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Eltur on 28/05/2017.
 */

public class connectToDB {

    public static void AddActivities(String patientID){

//        //add list of activities of patient to Activity class list
//        PatientRecord content = new PatientRecord();
//        content.setPatientID(patientID);
//       // moodAndAction activities = new moodAndAction();
//       Activity addNewActivity = new Activity("test12","test","test");
//        //addNewActivity.AddActivitiesToList(activities.getActivityList());
//        content.getListOfActivitiy().add(addNewActivity);
//        content.setPatientLastUpdate(new Date());
        PatientRecord content = new PatientRecord();
        content.setPatientID(patientID);
        // moodAndAction activities = new moodAndAction();
        Activity addNewActivity = new Activity("test14","test","test");
        //addNewActivity.AddActivitiesToList(activities.getActivityList());
        content.getListOfActivitiy().add(addNewActivity);
        content.setPatientLastUpdate(new Date());
        HttpClient httpClient = HttpClient.getClient();
        try{
            httpClient.SendPatientRecordToServer("http://localhost:8080/BEAT-PD/User//Update/PatientRecord/ActivitiesAndMedicines",content);
        }catch (MalformedURLException | JsonProcessingException ex){
            System.out.println(String.format("Error:%s",ex.getMessage()));
        }

      /*  try{


            /*
            URL url = new URL("http://localhost:8080/BEAT-PD/User/Update/PatientRecord/ActivitiesAndMedicines");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
            ObjectMapper mapper = new ObjectMapper();
            osw.write(mapper.writeValueAsString(content));
            osw.flush();
            osw.close();*/
          /*
            // 1. URL
            URL url = new URL("http://localhost:8080/BEAT-PD/User/Update/PatientRecord/ActivitiesAndMedicines");
            // 2. Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 3. Specify POST method
            conn.setRequestMethod("PUT");
            // 4. Set the headers
            conn.setRequestProperty("Content-Type", "application/json");
          //  conn.setRequestProperty("Authorization", "key="+patientID);
            conn.setDoOutput(true);
            // 5. Add JSON data into POST request body
            //`5.1 Use Jackson object mapper to convert Contnet object into JSON
            ObjectMapper mapper = new ObjectMapper();
            // 5.2 Get connection output stream
            OutputStreamWriter wr = new OutputStreamWriter(
                    conn.getOutputStream());
            wr.write( mapper.writeValueAsString(content));
            wr.flush();
            wr.close();


           // DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            // 5.3 Copy Content "JSON" into
           // mapper.writeValue((OutputStream) wr, content);
            //System.out.print(content);
            //mapper.writeValueAsString(content);

            // 5.4 Send the request
           // wr.flush();
            // 5.5 close
           // wr.close();
           */

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }




    public static void main(String args[]) {

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
        try{
            //httpClient.SendPatientRecordToServer("http://localhost:8080/BEAT-PD/User//Update/PatientRecord/ActivitiesAndMedicines",content);
            HttpClient.GetAllActivitiesFromServer("http://localhost:8080/BEAT-PD/User/GET/AllActivities/");
        }catch (MalformedURLException /*| JsonProcessingException*/ ex){
            System.out.println(String.format("Error:%s",ex.getMessage()));
       }
    }
}


