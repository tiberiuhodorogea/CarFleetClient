package com.example.tiber.carfleetproject.Clase;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.example.tiber.carfleetproject.R;
import com.example.tiber.carfleetproject.SharedClasses.Communication.*;
import com.example.tiber.carfleetproject.SharedClasses.Communication.Exceptions.KeyNotMappedException;


/**
 * Created by tiber on 4/10/2016.
 */
    public class ServerConnection<RequestDataType,ResponseDataType>  {

    //SETTINGS ( .res/values/setting.xml )
    private static int PORT ;
    private static String SERVER_ADDRESS ;
    private static int CONNECT_TO_SERVER_TIMEOUT ;
    /////////

    private Socket client = null;
    private PrintWriter printwriter;
    private BufferedReader bufferedReader ;
    private String requestJson;
    private String responseJson = null;
    private boolean connectionSuccessful = true;
    private Request requestObject;

    public ServerConnection(Context context) {
        SERVER_ADDRESS = context.getResources().getString(R.string.SERVER_ADDRESS);
        PORT = context.getResources().getInteger(R.integer.PORT);
        CONNECT_TO_SERVER_TIMEOUT =  context.getResources().getInteger(R.integer.CONNECT_TO_SERVER_TIMEOUT);
    }


    public ResponseDataType execute(RequestedAction requestedAction, RequestDataType dataNeededOnServer) throws KeyNotMappedException {
        Response response = doRequest(requestedAction, dataNeededOnServer);     // do request

        if(!connectionSuccessful) {                  //check if connection was successful

           return null;
        }
        ResponseDataType ret =  (ResponseDataType)response.deserializeData();            //deserialize

        return ret;
    }

    private Response doRequest(RequestedAction requestedAction, RequestDataType dataNeededOnServer) throws KeyNotMappedException {
        requestObject = new RequestFactory().create(requestedAction, dataNeededOnServer); // set the requestObject
        requestJson = requestObject.toJson();          //set the requestJson String

        SendRequest();
        while(  responseJson == null &&
               connectionSuccessful) //if connection unsuccessfull stop loop and treat accordingly
        {/*loop*/}

        Response ret = null;
        if(connectionSuccessful){
           ret = new ResponseFactory().create(responseJson);
        }

        return ret;

    }

    private void preSendRequest(){
        responseJson = null; // reinit response makes while loop to wait for it
        connectionSuccessful = true;
    }

    private void SendRequest(){
        try{
            client = new Socket();
            client.setSoTimeout(CONNECT_TO_SERVER_TIMEOUT);

            client.connect(new InetSocketAddress(SERVER_ADDRESS, PORT),CONNECT_TO_SERVER_TIMEOUT);

            printwriter = new PrintWriter(client.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            printwriter.write(requestJson +"\n");//write REQUEST as json string
            printwriter.flush();

            responseJson = bufferedReader.readLine(); // get RESPONSE as json string

            client.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            connectionSuccessful = false;
        }
    }

}
