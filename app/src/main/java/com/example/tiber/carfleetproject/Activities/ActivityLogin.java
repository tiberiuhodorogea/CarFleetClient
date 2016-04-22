package com.example.tiber.carfleetproject.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiber.carfleetproject.Clase.ServerConnection;
import com.example.tiber.carfleetproject.Listeners.OnLogInClickListener;
import com.example.tiber.carfleetproject.Listeners.OnRegisterClickListener;
import com.example.tiber.carfleetproject.R;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        setListeners(this);

    }

    private void setListeners(Context context){

        findViewById(R.id.buttonLogIn).setOnClickListener(new OnLogInClickListener(context));
        findViewById(R.id.buttonRegister).setOnClickListener(new OnRegisterClickListener(context));

    }
}
