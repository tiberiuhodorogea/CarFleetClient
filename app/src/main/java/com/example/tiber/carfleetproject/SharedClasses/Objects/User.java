package com.example.tiber.carfleetproject.SharedClasses.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.tiber.carfleetproject.SharedClasses.Utils.UserRole;

import java.io.Serializable;

/**
 * Created by tiber on 4/17/2016.
 */
public class User implements Parcelable{
    int id;
    String mail;
    String password;
    UserRole role;
    int managerId;

    public User(){}

    public int getManagerId(){
        return managerId;
    }
    public void setManagerId(int managerId){
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public User(int id, String mail, String password, UserRole role, int managerId) {

        this.id = id;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.managerId = managerId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getId());
        dest.writeString(this.getMail());
        dest.writeString(this.getPassword());
        int role = -1;
        switch (this.getRole()){
            case MANAGER:role =0;
                break;
            case ADMINISTATOR:role = 1;
                break;
            default:role=2;
                break;
        }
        dest.writeInt(role);
        dest.writeInt(managerId);
    }

    public User(Parcel in){
        this.id = in.readInt();
        this.mail = in.readString();
        this.password = in.readString();
        int role = in.readInt();
        switch(role){
            case 0:this.role = UserRole.MANAGER;
                break;
            case 1:this.role = UserRole.ADMINISTATOR;
                default:
                    this.role = UserRole.NO_USER_ROLE;
             }
        this.managerId = in.readInt();
        }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
