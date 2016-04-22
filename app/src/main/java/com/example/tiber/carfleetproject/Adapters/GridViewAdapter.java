package com.example.tiber.carfleetproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiber.carfleetproject.R;


public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private final String[] mobileValues;

    public GridViewAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.single_cell, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(mobileValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = mobileValues[position];

            switch(mobile){
                case "Fleet list":
                    imageView.setImageResource(R.drawable.fleet_list);
                    break;
                case "Fleet map":
                    imageView.setImageResource(R.drawable.fleet_map);
                    break;
                case "Add driver":
                    imageView.setImageResource(R.drawable.add_driver);
                    break;
                case "Add vehicle":
                    imageView.setImageResource(R.drawable.add_vehicle);
                    break;
                case "Reports":
                    imageView.setImageResource(R.drawable.reports);
                    break;
                case "Alerts":
                    imageView.setImageResource(R.drawable.alerts);
                    break;
                case "Car inspections":
                    imageView.setImageResource(R.drawable.inspections);
                    break;
                case "Exit":
                    imageView.setImageResource(R.drawable.exit);
                    break;
                default:
                    imageView.setImageResource(R.drawable.none);

            }
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}

