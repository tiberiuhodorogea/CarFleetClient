package com.example.tiber.carfleetproject.Listeners;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.tiber.carfleetproject.Clase.MyAsyncTask;
import com.example.tiber.carfleetproject.Clase.ServerConnection;
import com.example.tiber.carfleetproject.R;
import com.example.tiber.carfleetproject.SharedClasses.Communication.Exceptions.KeyNotMappedException;
import com.example.tiber.carfleetproject.SharedClasses.Communication.RequestedAction;
import com.example.tiber.carfleetproject.SharedClasses.Communication.ResponseEnum;
import com.example.tiber.carfleetproject.SharedClasses.Objects.Car;
import com.example.tiber.carfleetproject.SharedClasses.Objects.User;
import com.example.tiber.carfleetproject.SharedClasses.Utils.CarBuilder;
import com.example.tiber.carfleetproject.SharedClasses.Utils.MyDate;
import com.example.tiber.carfleetproject.SharedClasses.Utils.MyDateBuilder;
import com.example.tiber.carfleetproject.SharedClasses.Utils.ObjectUserIdContainer;

/**
 * Created by tiber on 4/18/2016.
 */
public class OnAddVehicleButtonClickListener implements View.OnClickListener {
    String licenceNo;
    long mileage;
    MyDate lastRevisionDate;
    User user;
    public static Car carToAdd;
    private Context context;



    public OnAddVehicleButtonClickListener (Context context){
        this.context= context;
    }
    @Override
    public void onClick(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        if(((EditText)((AppCompatActivity)context).findViewById(R.id.editTextMileage)).getText().toString().trim().matches("") &&
                ((EditText)((AppCompatActivity)context).findViewById(R.id.editTextLicenceNumber)).getText().toString().trim().matches(""))
        {
            Toast.makeText(context,"All fields are required",Toast.LENGTH_SHORT).show();
        }
        else {
            user = ((AppCompatActivity)context).getIntent().getParcelableExtra("user");
            String licenceNumber =((EditText)((AppCompatActivity)context).findViewById(R.id.editTextLicenceNumber)).getText().toString();
            licenceNumber = removeLeadingAndTrailingSpaces(licenceNumber);
            licenceNo = licenceNumber;
            MyDateBuilder dateBuilder = new MyDateBuilder();
            lastRevisionDate = dateBuilder.setDay(((NumberPicker) ((AppCompatActivity) context).findViewById(R.id.numberPickerDay)).getValue())
                    .setMonth(((NumberPicker) ((AppCompatActivity) context).findViewById(R.id.numberPickerMonth)).getValue())
                    .setYear(((NumberPicker) ((AppCompatActivity) context).findViewById(R.id.numberPickerYear)).getValue())
                    .build();
            mileage = Long.parseLong(((EditText) ((AppCompatActivity) context).findViewById(R.id.editTextMileage)).getText().toString());
            carToAdd=new CarBuilder().setLastRevisionDate(lastRevisionDate)
                    .setLicenceNo(licenceNo)
                    .setMileage(mileage)
                    .setManagerId(user.getManagerId())
                    .build();
        alertDialogBuilder.setTitle("Adding new vehicle");

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Licence number - ");
        messageBuilder.append(licenceNo+"\n");
        messageBuilder.append("Mileage - ");
        messageBuilder.append(mileage+"km \n");
        messageBuilder.append("Last revision made on \n");
        messageBuilder.append(((NumberPicker) ((AppCompatActivity) context).findViewById(R.id.numberPickerDay)).getValue() + " " +
                       theMonth(((NumberPicker) ((AppCompatActivity) context).findViewById(R.id.numberPickerMonth)).getValue() - 1) +
                        " " + ((NumberPicker) ((AppCompatActivity) context).findViewById(R.id.numberPickerYear)).getValue());
                // set dialog message
                alertDialogBuilder
                        .setMessage(messageBuilder.toString())
                        .setCancelable(false)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new sendCarToServer(context).execute();

                            }
                        })
                        .setNegativeButton("back", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    }

    private String removeLeadingAndTrailingSpaces(String licenceNumber) {
        //leading spaces
        while(licenceNumber.charAt(0) == ' '){
            licenceNumber = licenceNumber.substring(1);
        }
        //trailing spaces
        while(licenceNumber.charAt(licenceNumber.length() -1) == ' '){
            licenceNumber = licenceNumber.substring(0,licenceNumber.length()-1);
        }
        return licenceNumber;
    }

    public static String theMonth(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

    class sendCarToServer extends MyAsyncTask{

        private ResponseEnum responseEnum = null;
        public sendCarToServer(Context context) {
            super(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServerConnection<Car,ResponseEnum> connection = new ServerConnection<>(context);
            try {
                responseEnum = connection.execute(RequestedAction.ADD_CAR, carToAdd);
            } catch (KeyNotMappedException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (responseEnum == null){
               Toast.makeText(context,context.getResources().getText(R.string.connection_problem),Toast.LENGTH_LONG);
            }
            else{ // got a response
                switch (responseEnum){
                    case CONFIRM:
                        Toast.makeText(context,"Vehicle added to database",Toast.LENGTH_SHORT).show();
                        ((AppCompatActivity)context).finish();
                        break;
                    case SQL_ERROR:
                        Toast.makeText(context,"SQL ERROR ON SERVER",Toast.LENGTH_SHORT).show();
                        break;
                    case DUPLICATE_LICENCE_NUMBER:
                        Toast.makeText(context,"DUPLICATE LICENCE NUMBER IN DATABASE!",Toast.LENGTH_SHORT).show();
                        break;
                    case LAST_REVISION_IS_IN_FUTURE:
                        Toast.makeText(context,"Are you a time traveller?",Toast.LENGTH_LONG).show();
                        break;
                    case ERROR:
                        Toast.makeText(context,"some error...",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(context,"unknown",Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        }
    }

}
