/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 * COIT20245 - Assignment 2
 * Van Manh Le - 12105847
 */
public class Record {
    //create all variables
    private String id;          //Patient ID 
    private int sbp;            //Patient Systolic Blood Pressure 
    private int dbp;            //Patient Diastolic Blood Pressure
    private int map;            //Patient Mean Arterial Pressure
    private String category;    //Category of MAP Value
    
    //Create constructor to set value of variables
    public Record(String id, int sbp, int dbp, int map, String category)
    {
    this.id = id;               //Set value of ID 
    this.sbp = sbp;             //Set value of SBP
    this.dbp = dbp;             //Set value of DBP
    this.map = map;             //Set value of MAP
    this.category = category;   //Set value of category
    }
    
    //toString() method to return record of patient
    public String toString()
    {
        return "<"+id+", "+sbp+", "+dbp+", "+map+", "+category+">";
    }
    
    //Getter for all variables
    public String getId()       //Getter for patient ID
    {
        return id;
    }
    
    public int getSbp()         //Getter for patient SBP value
    {
        return sbp;
    }
    
    public int getDbp()         //Getter for patient DBP value
    {
        return dbp;
    }
    
    public int getMap()         //Getter for patient MAP value
    {
        return map;
    }
    
    public String getCategory() //Getter for category of MAP value
    {
        return category;
    }
    
}
