package com.example.tiber.carfleetproject.SharedClasses.Objects;


import com.example.tiber.carfleetproject.SharedClasses.Utils.CarStatus;
import com.example.tiber.carfleetproject.SharedClasses.Utils.MyDate;

import java.util.Date;

/**
 * Created by tiber on 4/9/2016.
 */
public class Car {
    private int id;
    private int managerId;
    private String licenceNo;
    private long mileage;
    private Driver driver;//NON PERSISTENT
    private CarStatus status;//NON PERSISTENT
    private double speed;//NON PERSISTENT
    private Location location;//NON PERSISTENT
    private Location destination;//NON PERSISTENT
    private Location lastKnownLocation;
    private MyDate lastUpdateFromFieldDate;
    private MyDate lastRevisionDate;

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public Car(String licence,CarStatus status){
        this.licenceNo=licence;
        this.status = status;
    }

    public Car() {
    }

    public int getManagerId() {

        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public MyDate getLastUpdateFromFieldDate() {
        return lastUpdateFromFieldDate;
    }

    public void setLastUpdateFromFieldDate(MyDate lastUpdateFromFieldDate) {
        this.lastUpdateFromFieldDate = lastUpdateFromFieldDate;
    }

    public MyDate getLastRevisionDate() {
        return lastRevisionDate;
    }

    public void setLastRevisionDate(MyDate lastRevisionDate) {
        this.lastRevisionDate = lastRevisionDate;
    }
}

