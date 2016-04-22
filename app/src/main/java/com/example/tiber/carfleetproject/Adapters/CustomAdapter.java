package com.example.tiber.carfleetproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiber.carfleetproject.SharedClasses.Objects.Car;
import com.example.tiber.carfleetproject.R;
import com.example.tiber.carfleetproject.SharedClasses.Utils.CarStatus;

import java.util.ArrayList;

/**
 * Created by tiber on 4/10/2016.
 */
public class CustomAdapter extends ArrayAdapter {

    public static final int TYPE_STATUS_OFFLINE = 0;
    public static final int TYPE_STATUS_ONLINE = 1;

    Context context;
    protected ArrayList<Car> cars;

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(cars.get(position).getStatus() == CarStatus.OFFLINE)
            return 0;
        else
            return 1;
    }

    public CustomAdapter(Context context, int resource , ArrayList<Car> cars) {
        super(context, resource, cars);
        this.cars = cars;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Car car = cars.get(position);
        int viewType = getItemViewType(position);
        ImageView image;

            switch(viewType){
                case TYPE_STATUS_OFFLINE:
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_offline_row, null);
                    ((TextView)(convertView.findViewById(R.id.licencePlateTagOffline))).setText(car.getLicenceNo());
                    image = (ImageView) convertView.findViewById(R.id.statusImageOffline);
                    image.setImageResource(R.drawable.car_offline);
                    break;
                case TYPE_STATUS_ONLINE:
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_online_row, null);
                    ((TextView)(convertView.findViewById(R.id.licencePlateTag))).setText(car.getLicenceNo());
                    image = (ImageView) convertView.findViewById(R.id.statusImage);
                    image.setImageResource(R.drawable.car_online);
                    break;
                default:
                    ;

            }

        return convertView;
    }

}
