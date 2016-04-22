package com.example.tiber.carfleetproject.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.tiber.carfleetproject.Listeners.OnAddVehicleButtonClickListener;
import com.example.tiber.carfleetproject.R;

import java.util.Calendar;

public class ActivityAddVehicle extends AppCompatActivity {

    private Context context;
    private EditText editTextLicenceNo;
    private EditText editTextMileage;
    private NumberPicker numberPickerDay;
    private NumberPicker numberPickerMonth;
    private NumberPicker numberPickerYear;
    private int maxFebruaryDays = 28;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        this.context =this;
        editTextLicenceNo = (EditText) ((AppCompatActivity)context).findViewById(R.id.editTextLicenceNumber);
        editTextMileage = (EditText) ((AppCompatActivity)context).findViewById(R.id.editTextMileage);
        numberPickerDay = (NumberPicker)((AppCompatActivity)context).findViewById(R.id.numberPickerDay);
        numberPickerMonth = (NumberPicker)((AppCompatActivity)context).findViewById(R.id.numberPickerMonth);
        numberPickerYear = (NumberPicker)((AppCompatActivity)context).findViewById(R.id.numberPickerYear);
        init();

        findViewById(R.id.buttonAddCar).setOnClickListener(new OnAddVehicleButtonClickListener(this));
        findViewById(R.id.buttonCancelAddCar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) context).finish();
            }
        });
    }
    private void init(){

        numberPickerDay.setMinValue(1);
        numberPickerDay.setMaxValue(31);
        numberPickerDay.setValue(1);
        numberPickerMonth.setMinValue(1);
        numberPickerMonth.setMaxValue(12);
        numberPickerMonth.setValue(1);
        numberPickerYear.setMinValue(2001);
        numberPickerYear.setMaxValue(Calendar.getInstance().get(Calendar.YEAR));
        numberPickerYear.setValue(2015);

        //TODO implement values logic
        numberPickerYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                if(newVal % 4 == 0){
                    if(numberPickerMonth.getValue() == 2)
                        numberPickerDay.setMaxValue(29);
                    maxFebruaryDays =29;
                }
                else{
                    maxFebruaryDays = 28;
                    if(numberPickerMonth.getValue() == 2)
                        numberPickerDay.setMaxValue(28);}

            }
        });
        numberPickerMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(newVal %2 == 0){
                    if(newVal == 2)//february
                        numberPickerDay.setMaxValue(maxFebruaryDays);
                    else
                        numberPickerDay.setMaxValue(30);

                }
                else
                    numberPickerDay.setMaxValue(31);
            }
        });
    } // implemented leap year, max days calendar logic...
}
