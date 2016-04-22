package com.example.tiber.carfleetproject.Listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiber.carfleetproject.Activities.ActivityAddVehicle;
import com.example.tiber.carfleetproject.Activities.ActivityFleetList;
import com.example.tiber.carfleetproject.R;
import com.example.tiber.carfleetproject.SharedClasses.Objects.User;

/**
 * Created by tiber on 4/9/2016.
 */
public class OnGridMenuItemClickListener implements AdapterView.OnItemClickListener {

    Context context;
    private User user;

    public OnGridMenuItemClickListener(Context context){
        this.context =context;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        user = ((AppCompatActivity)context).getIntent().getParcelableExtra("user");

        Intent startSelectedActivity = new Intent();

        startSelectedActivity.putExtra("user",user);

        String option = ((TextView) view.findViewById(R.id.grid_item_label))
                .getText().toString();
        switch (option){
            case "Fleet list":
                startSelectedActivity.setClass(context, ActivityFleetList.class);
                break;
            case "Add vehicle":
                startSelectedActivity.setClass(context, ActivityAddVehicle.class);
                break;
            case "Exit":
                //exit
                Toast.makeText(context,option, Toast.LENGTH_SHORT).show();
                break;
                default:
                    Toast.makeText(context,option, Toast.LENGTH_SHORT).show();
        }

        context.startActivity(startSelectedActivity);

    }
}
