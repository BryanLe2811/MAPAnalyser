/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;
import java.util.ArrayList;                     //Import ArrayList class
/**
 * Student ID: 12105847
 * Student name: Van Manh Le
 */
public class MAPAnalyser {
    
    private Record[] data;                      //Set array Record type to keep data of patient
    private int nrecords;                       //Set variable for number of patient
    private final int MAP_LOWER_LIMIT = 70;     //Set constant variable of MAP Lower Limit for Medium type for classify method
    private final int MAP_HIGHER_LIMIT = 100;   //Set constant variable of MAP Higher Limit for Medium type for classify method
    
    public MAPAnalyser()                        // MAPAnalyser constructor
    {
        nrecords = 20;                          //Set the number of patient records
        data = new Record[nrecords];            // Create data array with number of value is number of patient
    }
    
    //-------------------Binary search for ID of patient-----------------------------
    public Record find(String id)           
    {
        loadFromTable();    //Run loadFromTable method to set value for "data" object
        SortById();         //Do selection sort by ID
        int low = 0;        //Set low limitation for binary search
        int high = data.length - 1;         //Set upper limitation for binary search
        int middle = (int)((low + high + 1)/2);     //Set middle point of binary search range
        int location = -1;  //Initiate the location of found record. If no record found, value will not change
        do 
        {
            if (id.equalsIgnoreCase(data[middle].getId()))      // ID inserted by User is equal to the ID at position [middle] of the binary search range
            {
                location = middle;  // Set location for the found ID as the middle position of binary search range
            }
            else if (id.compareToIgnoreCase(data[middle].getId())<0) // ID inserted by User is lower to the ID at position [middle] of the binary search range
            {
                high = middle-1;    // Set new upper limit for binary search if User's ID is lower than ID at position [middle] of the search range
            }
            else
            {
                low = middle + 1;   // Set new lower limit for binary search if User's ID is higher than ID at position [middle] of the search range
            }
            middle = (int)((low + high +1 )/2);     // Set new middle point of binary search range
        }
        while ((low <= high) && (location == -1));  // End loop when there is no value in search range or found the Record with ID inserted by user
        if (location == -1)         // Check if no record found
        {
            return null;            // Return no value for the method
        }
        else                        // if there is a record found
        {
            return data[location];  // Return location of found Record
        }
    }
    //------------------------End Binary Search--------------------------------
    
    
    //------------------Finding the lowest value of MAP------------------------
    public int lowest() 
    {
        loadFromTable();        //Run loadFromTable method to set value for "data" object
        int lowest = data[0].getMap();      //Initiate Lowest variable as MAP Value of record data[0]
        
        for (int i = 1; i < nrecords; i++)  //Check all value of the array data[]
        {
            if (lowest >= data[i].getMap()) //Compare Lowest variable with MAP value of record data[i]
            {
                lowest = data[i].getMap();  //Set new value of Lowest if it's higher than MAP value of record data[i]
            }
        }
        return lowest;          //Return lowest value
    }
    //------------------Ending find lowest value of MAP method------------------
    
    
    //------------------Finding the highest value of MAP------------------------
    public int highest()
    {
        loadFromTable();        //Run loadFromTable method to set value for "data" object
        int highest = data[0].getMap();     //Initiate Highest variable as MAP Value of record data[0]
        for (int i = 1; i < nrecords; i++)  //Check all value of the array data[]
        {
            if (highest <= data[i].getMap())    //Compare Highest variable with MAP value of record data[i]
            {
                highest = data[i].getMap();     //Set new value of Highest if it's lower than MAP value of record data[i]
            }
        }
        return highest;         //Return highest value
    }
    //------------------Ending find highest value of MAP method------------------
    
    
    //------------------Finding the median value of MAP------------------------
    public int median()     
    {
        loadFromTable();        //Run loadFromTable method to set value for "data" object
        SortByMap();            //Sort the record by MAP value

        if (nrecords%2 != 0)    //Check if number of record is odd or even number
        {
            return data[nrecords/2].getMap();   // Median value is the middle record if number of record is odd
        }
        else
        {
            return (data[nrecords/2].getMap()+data[nrecords/2-1].getMap())/2;   // Median value is the average value of records at position [nrecords/2] and [nrecords/2-1] if number of record is even
        }
        
    }
    //------------------Ending find median value of MAP method------------------
    
    
    
    //---------Find record with MAP value in the range of map1 and map2---------
    public Record[] find(int map1, int map2)
    {
        loadFromTable();        //Run loadFromTable method to set value for "data" object
        
        ArrayList<Record> r1 = new ArrayList(); //Create r1 array to get matched record with Map value in range of map1, map2
        int j = 0;      //Variable j will be used as index for r1 array 
        for (int i = 0; i<nrecords;i++) // Running for loop from index 0 to index nrecords-1
        {
            if (map1<=data[i].getMap() && data[i].getMap()<=map2)      //Compare Map value of record data[i] wit map1 and map2 value
            {
                r1.add(j,data[i]);      // Add new value to r1 array if it matched the condition of being in range of map1 and map2
                j++;                    // Increase value of j in order to create new value of r1 array
            }
        }
        Record[] r = new Record[r1.size()];     //Create r array to return matched record for user
        for (int i = 0; i<r1.size();i++)        //Running for loop the whole r1 array
        {
            r[i] = r1.get(i);           // Pass all value of r1 array to r array
        }
        
        return r;                       // Return r array to user with matched records with Map value in range of map1, map2
    }
    //---------End method of finding record with MAP value in the range of map1 and map2---------
    
    
    //---------------Sort array data[] by MAP value---------------------------
    private void SortByMap()
    {
        loadFromTable();        //Run loadFromTable method to set value for "data" object
        for (int i = 0; i <(nrecords-1);i++)    //Running for loop from index 0 to index nrecords-2
        {
            int smallest = i;   //Initiate position of smallest value as the first record in the loop
            for (int j = i+1; j<nrecords;j++)   //Running for loop from the next index to index nrecords-1
            {
                if (data[smallest].getMap() > data[j].getMap()) // Compare value of map in array to find the smallest value
                {
                    smallest = j;       //Found the smallest value and take its position for the smallest value
                }
            }
            Record temp = data[i];      //Create temp variable to keep value of data[i]
            data[i] = data[smallest];   //Take the record of patient with smallest MAP Value to replace the value of record with index i position
            data[smallest] = temp;      //Return the value of record previously at position i to the position that record with smallest MAP Value used to be at
        }
    }
    //----------------End method of sorting by MAP value-----------------------
    
    
    //---------------Sorting array data[] by ID value---------------------------
    private void SortById()
    {
        loadFromTable();        //Run loadFromTable method to set value for "data" object
        for (int i = 0; i <(nrecords-1);i++)        //Running for loop from index 0 to index nrecords-2
        {
            int smallest = i;   //Initiate position of smallest value as the first record in the loop
            for (int j = i+1; j<nrecords;j++)       //Running for loop from the next index to index nrecords-1
            {
                if (data[smallest].getId().compareTo(data[j].getId()) > 0)  // Compare value of ID in array to find the smallest value
                {
                    smallest = j;       //Found the smallest value and take its position for the smallest value
                }
            }
            Record temp = data[i];      //Create temp variable to keep value of data[i]
            data[i] = data[smallest];   //Take the record of patient with smallest ID Value to replace the value of record with index i position
            data[smallest] = temp;      //Return the value of record previously at position i to the position that record with smallest ID Value used to be at
        }
    }
    //----------End method of sorting by ID Value-------------------------------
    
    
    //-----------------Start loadFromTable{} method to initialize array and create Record object-------------------
    private void loadFromTable()
    {
        String[] idArray = {"S17","S05","S12","S04","S02","S06","S08","S14","S01","S03",
                            "S20","S16","S10","S07","S11","S09","S13","S25","S19","S23"};   //Initialize array for patient's ID
        int[] sbpArray = {80,90,120,110,95,73,130,70,140,88,
                          75,85,93,115,140,97,74,125,114,91};       //Initialize array for Systolic Blood Pressure value
        int[] dbpArray = {50,70,100,90,85,65,120,60,80,74,
                          72,68,83,95,110,87,64,103,102,71};        //Initialize array for Diastolic Blood Presure value
        int[] mapArray = new int[nrecords];                         //Create array to record Mean Arterial Pressure value
        String[] categoryArray = new String[nrecords];              //Create array to record Category of MAP Value
        
        for (int i = 0; i < nrecords; i++)          //Run for loop to calculate MAP value, Category and Record object
        {
            mapArray[i] = (int)(1.0/3.0*sbpArray[i] + 2.0/3.0*dbpArray[i]);     //Calculate MAP Value and pass the result into mapArray array
            categoryArray[i] = this.classify(mapArray[i]);                      //Run method classify(int map) to classify MAP value, and pass result into categoryArray array
            data[i] = new Record(idArray[i],sbpArray[i],dbpArray[i],mapArray[i],categoryArray[i]);  //Pass all record of a patient into data[] object of class Record
        }
    }
    //---------------End loadFromTable() method --------------------------------
    
    
    //---------------classify(int map) method to classify value of MAP in different categories: Medium, Low or High-----------
    private String classify(int map)
    {
        if (map >= MAP_LOWER_LIMIT && map <= MAP_HIGHER_LIMIT)  //Compare MAP Value with lower limit and higher limit
        {
            return "Medium";        //Return String value "Medium" if MAP Value is inside the range of lower limit and higher limit
        }
        else if ( map < MAP_LOWER_LIMIT)      //If MAP value is not inside range of lower limit and higher limit, compare it with lower limit
        {
            return "Low";           //Return String value "Low" if MAP Value is smaller than lower limit
        }
        else 
        {
            return "High";          //Return String value "High" if MAP Value is bigger than higher limit
        }
    }
    //---------------End classify(int map) method-------------------------------
    
}
