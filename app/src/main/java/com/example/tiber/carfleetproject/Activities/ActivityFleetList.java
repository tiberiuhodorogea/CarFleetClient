package com.example.tiber.carfleetproject.Activities;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiber.carfleetproject.Adapters.CustomAdapter;
import com.example.tiber.carfleetproject.Clase.MyAsyncTask;
import com.example.tiber.carfleetproject.Clase.ServerConnection;
import com.example.tiber.carfleetproject.R;
import com.example.tiber.carfleetproject.SharedClasses.Communication.Exceptions.KeyNotMappedException;
import com.example.tiber.carfleetproject.SharedClasses.Communication.RequestedAction;
import com.example.tiber.carfleetproject.SharedClasses.Objects.Car;
import com.example.tiber.carfleetproject.SharedClasses.Objects.User;
import com.example.tiber.carfleetproject.SharedClasses.Utils.CarBuilder;
import com.example.tiber.carfleetproject.SharedClasses.Utils.CarStatus;

import java.util.ArrayList;

public class ActivityFleetList extends AppCompatActivity {

    private User user;
    private ArrayList<Car> dataSource = new ArrayList<Car>();
    private ListView lv;
    private static ArrayList<Car> allCars;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet_list);
        //getActionBar().setTitle("Fleet list");
        user = getIntent().getParcelableExtra("user");
        dataSource.add(new Car("CT 19 TBI", CarStatus.ONLINE));
        dataSource.add(new Car("B 190 BBB", CarStatus.ONLINE));
        dataSource.add(new Car("AG 23 ALX",CarStatus.OFFLINE));
        dataSource.add(new Car("B 190 BBB",CarStatus.ONLINE));

        lv = (ListView) findViewById(R.id.fleetListListView);
        adapter = new CustomAdapter(this,0,dataSource);
        lv.setAdapter(adapter);


        new getCarsAsync(this).execute();
    }


    class getCarsAsync extends MyAsyncTask{

        public getCarsAsync(Context context) {
            super(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServerConnection<User,ArrayList<Car>> connection= new ServerConnection<User,ArrayList<Car>>(this.context);
            try {
                allCars = connection.execute(RequestedAction.GET_FLEET_LIST,user);
            } catch (KeyNotMappedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
          //  for(Car c:allCars);
             //adapter.addAll(allCars);
           // adapter.notifyDataSetChanged();
           for(int i =0;i<allCars.size();++i)
               adapter.addAll(convertArrayOfCars(allCars));
            adapter.notifyDataSetChanged();
        }
    }


    private ArrayList<Car> convertArrayOfCars(ArrayList<Car> localCars) {
        ArrayList<Car> ret = new ArrayList<Car>();
        CarBuilder carBuilder = new CarBuilder();
        for(int i=0;i<localCars.size();++i)
        {
        ret.add(
                carBuilder.setMileage(localCars.get(i).getMileage())
                        .setLicenceNo(localCars.get(i).getLicenceNo())
                        .setLastRevisionDate(localCars.get(i).getLastRevisionDate())
                        .setDestination(localCars.get(i).getDestination())
                        .setDriver(localCars.get(i).getDriver())
                        .setLastUpdateFromFieldDate(localCars.get(i).getLastUpdateFromFieldDate())
                        .setLastKnownLocation(localCars.get(i).getLastKnownLocation())
                        .setId(localCars.get(i).getId())
                        .setManagerId(localCars.get(i).getManagerId())
                        .setSpeed(localCars.get(i).getSpeed())
                        .setStatus(localCars.get(i).getStatus())
                        .setLocation(localCars.get(i).getLocation())
                        .build()
        );
        carBuilder.flush();
    }
        return ret;
    }
}
