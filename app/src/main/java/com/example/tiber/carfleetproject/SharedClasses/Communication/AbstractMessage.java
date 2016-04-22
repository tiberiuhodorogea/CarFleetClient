package com.example.tiber.carfleetproject.SharedClasses.Communication;


import com.example.tiber.carfleetproject.SharedClasses.Communication.Exceptions.KeyNotMappedException;

import java.lang.reflect.Type;

/**
 * Created by tiber on 4/14/2016.
 */
public abstract class AbstractMessage {
    protected RequestedAction requestedAction = null;
    protected String jsonData = null;

    public abstract Object deserializeData() throws KeyNotMappedException;
    public abstract String toJson();

    public  RequestedAction getRequestedAction(){
       return this.requestedAction;
    }
}
