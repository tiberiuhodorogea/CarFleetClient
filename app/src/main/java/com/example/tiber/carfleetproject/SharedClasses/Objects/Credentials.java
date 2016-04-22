package com.example.tiber.carfleetproject.SharedClasses.Objects;

/**
 * Created by tiber on 4/10/2016.
 */
public class Credentials {
    String userMail;
    String password;

    public Credentials(String userMail, String password) {
        this.userMail = userMail;
        this.password = password;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
