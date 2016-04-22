package com.example.tiber.carfleetproject.SharedClasses.Utils;

import com.example.tiber.carfleetproject.SharedClasses.Objects.Car;
import com.example.tiber.carfleetproject.SharedClasses.Objects.Driver;
import com.example.tiber.carfleetproject.SharedClasses.Objects.Location;
import com.example.tiber.carfleetproject.SharedClasses.Utils.CarStatus;

import java.util.Date;

/**
 * Created by tiber on 4/17/2016.
 */
public class CarBuilder {
    private Car car;

    public CarBuilder(){
        this.car = new Car();
    }
    public Car build(){
        return this.car;
    }
    public CarBuilder setLastKnownLocation(Location lastKnownLocation) {
        this.car.setLastKnownLocation(lastKnownLocation);
        return this;
    }
    public CarBuilder setMileage(long mileage) {
        this.car.setMileage(mileage);
        return this;
    }
    public CarBuilder setManagerId(int managerId) {
        this.car.setManagerId(managerId);
        return this;
    }

    public CarBuilder setId(int id) {
        this.car.setId(id);
        return this;
    }

    public CarBuilder setLicenceNo(String licenceNo) {
        this.car.setLicenceNo(licenceNo);
        return this;
    }

    public CarBuilder setDriver(Driver driver) {
        this.car.setDriver(driver);
        return this;
    }

    public CarBuilder setStatus(CarStatus status) {
        this.car.setStatus(status);
        return this;
    }

    public CarBuilder setSpeed(double speed) {
        this.car.setSpeed(speed);
        return this;
    }

    public CarBuilder setLocation(Location location) {
        this.car.setLocation(location);
        return this;
    }

    public CarBuilder setDestination(Location destination) {
        this.car.setDestination(destination);
        return this;
    }

    public CarBuilder setLastUpdateFromFieldDate(MyDate lastUpdateFromFieldDate) {
        this.car.setLastUpdateFromFieldDate(lastUpdateFromFieldDate);
        return this;
    }

    public CarBuilder setLastRevisionDate(MyDate lastRevisionDate) {
        this.car.setLastRevisionDate(lastRevisionDate);
        return this;
    }

    public void flush(){
        this.car = new Car();
    }
}
