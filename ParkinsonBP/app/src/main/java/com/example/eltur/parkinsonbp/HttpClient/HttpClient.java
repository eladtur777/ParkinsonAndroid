package com.example.eltur.parkinsonbp.HttpClient;

import com.example.eltur.parkinsonbp.Activity;
import com.example.eltur.parkinsonbp.ServerClass.PatientRecord;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.example.eltur.parkinsonbp.Utils.UtilsMethod.convertStreamToString;

/**
 * Created by liran on 5/30/17.
 */

public class HttpClient {
    private HttpClient(){
    }
    private static HttpClient httpClient = new HttpClient();
    private URL url;
    private HttpURLConnection urlConnection;
    private static ObjectMapper mapper = new ObjectMapper();
    private ArrayList<String> arractivities = new ArrayList<>();
    private InputStream inputStream;
    private  OutputStream outputStream;
    public static HttpClient getClient(){return httpClient;};
    private static ArrayList<String> activitiesList = new ArrayList<>();

    public static Activity[] getJson() {
        return json;
    }

    private static Activity[] json;

    public Collection<Activity> getCollection() {
        return collection;
    }

    private Collection<Activity> collection;

    public Boolean SendPatientRecordToServer(String i_URL, Object i_ObjectToSend)throws MalformedURLException,JsonProcessingException{

        String json1 = mapper.writeValueAsString(i_ObjectToSend);
        url = new URL(i_URL);

        try{
            initiateURLConnection("PUT");

            //write the body
            outputStream = urlConnection.getOutputStream();
            outputStream.write(json1.getBytes("UTF-8"));
            outputStream.close();

            //read the body response
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            String result = convertStreamToString(inputStream);
            System.out.print(result);
            inputStream.close();
            if(result.contains("success"))
                return true;
        }catch (ProtocolException pr){
            System.out.println(String.format("Error:%s",pr.getMessage()));
        }
        catch (IOException IO){
            System.out.println(String.format("Error:%s",IO.getMessage()));
        }
        return false;
    }

    public ArrayList<String> GetAllActiviesFromServer(String i_URL)throws MalformedURLException {
       // json=null;
       // activitiesList =null;
        activitiesList.clear();
        url = new URL(i_URL);
        List<Activity> getActivitiesFromServer ;
        String result2="";

        try {
            initiateURLConnection("GET");
            //read the body response
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            String result = convertStreamToString(inputStream);

            if(result.contains("success")) {
                result2 = result.substring(43, result.length() - 2);
                mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                json = mapper.readValue(result2.getBytes("UTF-8"), Activity[].class);

                for (int i = 0; i < json.length; i++) {
                    if (!json[i].getActivityName().isEmpty()) {
                        activitiesList.add(json[i].getActivityName());
                    }

                }
                System.out.println(activitiesList);
                inputStream.close();
            }
            } catch (ProtocolException pr) {
                System.out.println(String.format("Error:%s", pr.getMessage()));
            } catch (IOException IO) {
                System.out.println(String.format("Error:%s", IO.getMessage()));
            }

            return activitiesList;

    }

    private void initiateURLConnection(String httpMethod)throws IOException,ProtocolException {
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(5000);
        urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        if (!httpMethod.equals("GET")) {
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod(httpMethod);
        }

    }
}