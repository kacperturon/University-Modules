/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Models.IModel;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class GaugeDatabaseModel {
    //Singleton class
    private static GaugeDatabaseModel instance = null;
    private static Map<ConstantGauges, IModel> database;

    private GaugeDatabaseModel(){
        database = new HashMap();//Database
    }
    
    public static synchronized GaugeDatabaseModel getInstance(){
        if(instance == null) instance = new GaugeDatabaseModel();
        return instance;
    }
    
    public static synchronized void addModel(ConstantGauges gauge,IModel model){
        database.put(gauge, model);
    }
    
    public static synchronized void updateModel(ConstantGauges gauge, double value){
        database.get(gauge).setValue(value);//Sets the value of the map
    }
            
    public static synchronized Map getDatabase(){
        return database;//Returns map
    }

}
