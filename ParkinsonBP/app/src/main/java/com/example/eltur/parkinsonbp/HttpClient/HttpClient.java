package com.example.eltur.parkinsonbp.HttpClient;

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

import static android.R.attr.format;
import static com.example.eltur.parkinsonbp.Utils.UtilsMethod.convertStreamToString;

/**
 * Created by liran on 5/30/17.
 */

public class HttpClient {
    private HttpClient(){}
    private static HttpClient httpClient = new HttpClient();
    private URL url;
    private HttpURLConnection urlConnection;
    private static ObjectMapper mapper = new ObjectMapper();
    private InputStream inputStream;
    private  OutputStream outputStream;
    public static HttpClient getClient(){return httpClient;};

    public Boolean SendPatientRecordToServer(String i_URL, Object i_ObjectToSend)throws MalformedURLException,JsonProcessingException{
        String json = mapper.writeValueAsString(i_ObjectToSend);
        url = new URL(i_URL);

        try{
            initiateURLConnection("PUT");

            //write the body
            outputStream = urlConnection.getOutputStream();
            outputStream.write(json.getBytes("UTF-8"));
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
