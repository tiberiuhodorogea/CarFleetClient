package com.example.tiber.carfleetproject.SharedClasses.Utils;

/**
 * Created by tiber on 4/18/2016.
 * String dates have this format : "dd-mm-yyyy-hh-mm"
 * example date 9 march 1990 19:32 we have 09-03-1990-19-32 ( yes, with leading zeros)
 */
public class MyDate {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    public MyDate() {
        day=month=year=hour=minute=0;
    }

    public MyDate(int day, int month, int year) {
        if(month<1 || month>12 || day<1 || day>21|| year > 9999 || minute<0 || hour<1 || minute>59 || hour>24)
            throw  new UnsupportedOperationException("Invalid day or month");
        this.day = day;
        this.month = month;
        this.year = year;
        hour=minute=0;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if(minute<0 || minute>59)
            throw new UnsupportedOperationException("Minute not in bounds");
        if(hour == 24 && minute != 0)
            throw new UnsupportedOperationException("min must be 0 for hour 24");
        this.minute = minute;
    }


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if(hour>24 || hour<0)
            throw  new UnsupportedOperationException("Hour not in bounds");
        if(hour == 24 && minute != 0)
            throw new UnsupportedOperationException("min must be 0 for hour 24");
        this.hour = hour;
    }
    public void setDay(int day) {
        if(day<1 || day>31)
            throw new UnsupportedOperationException("Invalid day");
        this.day = day;
    }

    public void setMonth(int month) {
        if(month<1 || month>12)
            throw new UnsupportedOperationException("Invalid month");
        this.month = month;
    }

    public void setYear(int year) {
        if (year>9999)
            throw new UnsupportedOperationException("Year too high");
        this.year = year;
    }

    public int getDay() {

        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {

        return year;
    }

    @Override
    public String toString(){
        String ret = "";
        if(day<10)
            ret+="0";
        ret+=day+"-";
        if(month<10)
            ret+="0";
        ret+=month+"-";
        ret+=year+"-";
        if(hour<10)
            ret+="0";
        ret+=hour+"-";
        if(minute<10)
            ret+="0";
        ret+=minute;

        return ret;
    }
    public static MyDate fromString(String date){//0 9 - 0 3 - 1 9 9 0 -   1   9   -   3   2
                                                 //0 1 2 3 4 5 6 7 8 9 10  11  12  13  14  15
        MyDate ret = new MyDate();
        ret.setDay(Integer.parseInt(date.substring(0, 2)));
        ret.setMonth(Integer.parseInt(date.substring(3, 5)));
        ret.setYear(Integer.parseInt(date.substring(6, 10)));
        ret.setHour(Integer.parseInt(date.substring(11, 13)));
        ret.setMinute(Integer.parseInt(date.substring(14,16)));
        return ret;
    }
}
