package com.example.tiber.carfleetproject.SharedClasses.Communication;

import com.example.tiber.carfleetproject.SharedClasses.Communication.Exceptions.KeyNotMappedException;
import com.example.tiber.carfleetproject.SharedClasses.Objects.Car;
import com.example.tiber.carfleetproject.SharedClasses.Objects.Credentials;
import com.example.tiber.carfleetproject.SharedClasses.Objects.User;
import com.example.tiber.carfleetproject.SharedClasses.Utils.ObjectUserIdContainer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by tiber on 4/14/2016.
 */
public class ActionTypesHashMapper {

    private static Hashtable<RequestedAction,TypePairContainer> messageActionTypesMapper =
            new Hashtable<RequestedAction,TypePairContainer>();

    static {
        // maps the requested action
        // with the type of  the encapsulated data on the request
        // and with the type of encapsulated data on response
        messageActionTypesMapper.put(RequestedAction.CHECK_ACCESS,new TypePairContainer(Credentials.class, User.class));
        messageActionTypesMapper.put(RequestedAction.ADD_CAR, new TypePairContainer(Car.class, ResponseEnum.class));


        messageActionTypesMapper.put(RequestedAction.GET_FLEET_LIST,new TypePairContainer(User.class, new TypeToken<ArrayList<Car>>(){}.getType()));

        //////ADD more when implement new request - response
    }

    public static Type getRequestDataClass(RequestedAction key) throws KeyNotMappedException {
        TypePairContainer typePairContainer = messageActionTypesMapper.get(key);
        if(null == typePairContainer)
            throw new KeyNotMappedException("Key :" + key.toString() + "not mapped to any types combination.");
        Type ret =typePairContainer.getRequestDataType();
        return ret;
    }


    public static Type getResponseDataClass(RequestedAction key) throws KeyNotMappedException {
        TypePairContainer typePairContainer = messageActionTypesMapper.get(key);
        if(null == typePairContainer)
            throw new KeyNotMappedException("Key :" + key.toString() + "not mapped to any types combination.");
        Type ret =  typePairContainer.getResponseDataType();
        return ret;
    }


}
