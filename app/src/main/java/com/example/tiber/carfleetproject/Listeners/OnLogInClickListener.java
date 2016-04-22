package com.example.tiber.carfleetproject.Listeners;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiber.carfleetproject.Clase.ServerConnection;
import com.example.tiber.carfleetproject.Activities.ActivityLogin;
import com.example.tiber.carfleetproject.Activities.ActivityMenu;
import com.example.tiber.carfleetproject.R;
import com.example.tiber.carfleetproject.SharedClasses.Communication.Exceptions.KeyNotMappedException;
import com.example.tiber.carfleetproject.SharedClasses.Communication.RequestedAction;
import com.example.tiber.carfleetproject.SharedClasses.Objects.Credentials;


import com.example.tiber.carfleetproject.SharedClasses.Objects.User;

/**
 * Created by tiber on 4/9/2016.
 */
public class OnLogInClickListener implements View.OnClickListener{
    Context context;


    public OnLogInClickListener(Context context) {
        this.context=context;
    }

    private static String mail,password;

    @Override
    public void onClick(View v) {

        final EditText editTextMail = (EditText) ((ActivityLogin)context).findViewById(R.id.editTextEmail);
        final EditText editTextPassword = (EditText) ((ActivityLogin)context).findViewById(R.id.editTextPassword);

        mail = editTextMail.getText().toString();
        password = editTextPassword.getText().toString();


        new AsyncTask<Void,Void,Void>(){
            User user;
            ProgressDialog mDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mDialog  = new ProgressDialog(context);
                mDialog.setMessage("Please wait...");
                mDialog.setCancelable(false);
                mDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                ServerConnection<Credentials,User> conn = new ServerConnection<Credentials,User>(context);
                try {
                    user = conn.execute(RequestedAction.CHECK_ACCESS,new Credentials(mail,password));
                } catch (KeyNotMappedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
        protected void onPostExecute(Void aVoid){
                mDialog.dismiss();
                if( user != null) // treating null (connection problem)
                {
                    switch (user.getRole()) {
                        case NO_USER_ROLE:
                            editTextPassword.setText("");
                            Toast.makeText(context, "Invalid credentials, try again or register as new user", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Intent startMenuActivity = new Intent(context, ActivityMenu.class);
                            startMenuActivity.putExtra("userMail", user.getMail());
                            startMenuActivity.putExtra("userRole",user.getRole().toString());
                            startMenuActivity.putExtra("userId",String.valueOf(user.getId()));
                            startMenuActivity.putExtra("managerId",String.valueOf(user.getManagerId()));
                            startMenuActivity.putExtra("user",user);
                            ((AppCompatActivity) context).startActivity(startMenuActivity);
                            break;
                    }
                }
                else
                    Toast.makeText(context,R.string.connection_problem,Toast.LENGTH_LONG).show();
            }
        }.execute();

    }


}
