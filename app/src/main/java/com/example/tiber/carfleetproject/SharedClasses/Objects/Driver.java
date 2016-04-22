package com.example.tiber.carfleetproject.SharedClasses.Objects;

/**
 * Created by tiber on 4/9/2016.
 */
public class Driver {
    private int id;
    private String firstName;
    private String lastName;

    public Driver(int id, String firstName, String lastName, int yearOfGettingLicence) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfGettingLicence = yearOfGettingLicence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfGettingLicence() {
        return yearOfGettingLicence;
    }

    public void setYearOfGettingLicence(int yearOfGettingLicence) {
        this.yearOfGettingLicence = yearOfGettingLicence;
    }

    private int yearOfGettingLicence;

}
