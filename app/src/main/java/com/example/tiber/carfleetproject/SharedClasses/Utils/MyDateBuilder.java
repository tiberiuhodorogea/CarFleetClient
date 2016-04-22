package com.example.tiber.carfleetproject.SharedClasses.Utils;

/**
 * Created by tiber on 4/18/2016.
 */
public class MyDateBuilder {
    private MyDate myDate;

    public MyDateBuilder(int day, int month, int year){
        myDate = new MyDate(day,month,year);
    }
    public MyDateBuilder(){
        this.myDate = new MyDate();
    }
    public MyDateBuilder setDay(int day){
        this.myDate.setDay(day);
        return this;
    }
    public MyDateBuilder setMonth(int month){
        this.myDate.setMonth(month);
        return  this;
    }
    public MyDateBuilder setYear(int year){
        this.myDate.setYear(year);
        return this;
    }
    public MyDateBuilder setHour(int hour){
        this.myDate.setHour(hour);
        return this;
    }
    public  MyDateBuilder setMinute(int minute){
        this.myDate.setMinute(minute);
        return this;
    }
    public MyDate build(){
        return myDate;
    }
}
