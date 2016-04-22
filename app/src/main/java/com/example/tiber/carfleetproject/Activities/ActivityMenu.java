package com.example.tiber.carfleetproject.Activities;

/**
 * Created by tiber on 4/9/2016.
 */
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import com.example.tiber.carfleetproject.Adapters.GridViewAdapter;
import com.example.tiber.carfleetproject.Listeners.OnGridMenuItemClickListener;
import com.example.tiber.carfleetproject.Listeners.OnSettingsClickListener;
import com.example.tiber.carfleetproject.R;
import com.example.tiber.carfleetproject.SharedClasses.Objects.User;

import org.w3c.dom.Text;

public class ActivityMenu extends AppCompatActivity {

    private User user;
    GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        modifyActionBar(this, getSupportActionBar());


        gridView = (GridView) findViewById(R.id.gridView1);

        gridView.setAdapter(new GridViewAdapter(this, getResources().getStringArray(R.array.menu_options)));

        gridView.setOnItemClickListener(new OnGridMenuItemClickListener(this));


    }

    private void modifyActionBar(Context context, android.support.v7.app.ActionBar mActionBar) {

        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(context);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText(R.string.menu_title);

        ImageButton imageButton = (ImageButton) mCustomView
                .findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new OnSettingsClickListener(context));

        mActionBar.setCustomView(mCustomView);

        mActionBar.setDisplayShowCustomEnabled(true);

        user = getIntent().getParcelableExtra("user");
        ((TextView)findViewById(R.id.title_userMail)).setText(user.getMail());
        ((TextView)findViewById(R.id.title_userRole)).setText(user.getRole().toString());
    }

}

