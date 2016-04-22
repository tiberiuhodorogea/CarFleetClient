package com.example.tiber.carfleetproject.SharedClasses.Communication;


import com.example.tiber.carfleetproject.SharedClasses.Communication.Exceptions.KeyNotMappedException;
import com.google.gson.Gson;

/**
 * Created by tiber on 4/14/2016.
 */
public abstract class MessageFactory  {
    abstract AbstractMessage create(RequestedAction requestedAction, Object data) throws KeyNotMappedException;
    abstract AbstractMessage create(String jsonMessage);

}

