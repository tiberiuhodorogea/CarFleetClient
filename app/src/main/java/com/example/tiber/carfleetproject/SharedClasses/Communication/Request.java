package com.example.tiber.carfleetproject.SharedClasses.Communication;

import com.example.tiber.carfleetproject.SharedClasses.Communication.Exceptions.KeyNotMappedException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;

import java.lang.reflect.Modifier;

/**
 * Created by tiber on 4/14/2016.
 */
public class Request extends AbstractMessage {

    protected Request(RequestedAction requestedAction, Object data) throws KeyNotMappedException {
            this.requestedAction = requestedAction;
            Type typeOfData = ActionTypesHashMapper.getRequestDataClass(requestedAction);//get REQUEST data class
            this.jsonData = new Gson().toJson(data,data.getClass());
    }

    @Override
    public Object deserializeData() throws KeyNotMappedException {
        Type typeOfData = ActionTypesHashMapper.getRequestDataClass(requestedAction);//get REQUEST data class
        return new Gson().fromJson(this.jsonData,typeOfData);
    }

    @Override
    public String toJson() {
            return new Gson().toJson(this,this.getClass());
    }
}
