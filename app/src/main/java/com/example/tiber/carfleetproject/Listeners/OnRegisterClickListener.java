package com.example.tiber.carfleetproject.Listeners;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by tiber on 4/9/2016.
 */
public class OnRegisterClickListener implements View.OnClickListener {
    Context context;
    public OnRegisterClickListener(Context context){
        this.context=context;
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(context,"Register",Toast.LENGTH_SHORT).show();
    }
}
