/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;
import java.util.Scanner;                                       //Import Scanner class to receive user's command
/**
 * Student ID: 12105847
 * Student name: Van Manh Le
 */
public class View {
    
    
    private MAPAnalyser commandExecute = new MAPAnalyser();     // Create object commandExecute of class MAPAnalyser
    private Record data = null;                                 // Create variable data with type Record to pass value from Record value
    private final int LOWEST_MAP_VALUE = 0;                     // Set constant variable for the Lowest value of MAP
    private final int HIGHEST_MAP_VALUE = 200;                  // Set constant variable for the highest value of MAP
    
    
    public View(MAPAnalyser a)      // Constructor of View
    {
        
    }
    public void commandLoop()       // public method commandLoop to execute command from user
    {
        Scanner input = new Scanner(System.in);     // Scanner object to receive command from user
        Scanner keyboard = new Scanner(System.in);  // Scanner object to receive command from user
                
        int help = 1;     // variable "help" to ask user continue or stop the program at end of each command
        int command;      // variable "command" to receive command from user
        
        do
        {
            //-----------------Instruction for user to insert command-----------------------------------------
            System.out.println("The following commands are recognised");
            System.out.printf("\t%-70s%s%d\n","Display this message","> ",0);
            System.out.printf("\t%-70s%s%d%s\n","Display a specific subject record:","> ",1," id");
            System.out.printf("\t%-70s%s%d%s\n","Display records for all subject records within a range:","> ",2," map1 map2");
            System.out.printf("\t%-70s%s%d\n","Display statistics (minimum, maximum and median)","> ",3);
            System.out.printf("\t%-70s%s%d\n","Exit the application","> ",9);
            //---------------------------End instruction------------------------------------------------------
            System.out.println();
            
            System.out.print("User insert command: ");      //Prompt user to insert command
            do
            {
                while (!input.hasNextInt())                 //Set validation to make sure user insert Integer for the command
                {
                    System.out.print("Command must be integer. Please insert again: ");             //Instruction to user if input is not Integer
                    input.next();                           //Allow user to re-insert their command
                }
                command = input.nextInt();                  //Read command from user
                                                                                                    
                if (command !=0 && command != 1 && command != 2 && command != 3 && command != 9)    // Compare user's input with allowed command 
                {
                    System.out.print("Command must be 0, 1, 2, 3 or 9. Please insert again: ");     //Instruction to user if command input is not recognized
                }
            }
            while(command !=0 && command != 1 && command!= 2 && command != 3 && command != 9);      //While loop to receive valid command from user
            
            switch (command)            // For each command, program will execute accordingly
            {
                case 0:                 // User's command is 0
                    help = command;     // Allow the loop to run again and display instruction message
                    break;              // Stop Switch loop
                
                case 1:                 // User's command is 1
                    help = command;     // User will need to confirm again to continue using the program after finish this command
                    System.out.print("Please insert patient's ID for searching: "); //Instruct user to insert Patient ID for searching
                    String id = keyboard.nextLine();            // User insert patient ID
                    data = commandExecute.find(id);             // Run method find(String id) in class MAPAnalyser
                    if (data == null)                           // Check the found data. Null means there is no record
                    {
                        System.out.println("No matched record found");  // There is no record matched with inserted ID
                    }
                    else
                    {
                        System.out.println("Record found, and display as <ID, SBP, DBP, MAP, Category>: " +data.toString());  // Print out matched record with all details
                    }
                    break;              // Stop Switch loop
                
                case 2:
                    help = command;     //User will need to confirm again to continue using the program after finish this command
                    int map1;           // Lower limitation of the MAP Value range that user would like to check
                    int map2;           // Lower limitation of the MAP Value range that user would like to check

                    do
                    {
                        System.out.print("Insert lower MAP value: ");               //Instruct user to insert Lower limitation
                        do
                        {
                            while (!input.hasNextInt())                             // Validation to make sure user's input of MAP value is integer
                            {
                                System.out.print("Lower MAP value must be integer. Insert again: ");    // Instruct user to insert integer for MAP Value
                                input.next();                                       // Allow user to insert again
                            }
                            map1 = input.nextInt();                                 // User insert MAP value for the Lower limitation of the range to find
                            if (LOWEST_MAP_VALUE>map1 || HIGHEST_MAP_VALUE<map1)    // Check validation if the inserted integer in the valid range or not
                            {
                                System.out.print("MAP value must be in range of "+LOWEST_MAP_VALUE+" to "+HIGHEST_MAP_VALUE+". Please insert again: ");  
                                //Instruct user to insert valid value of Lower limitation for the searching range
                            }
                        }
                        while (LOWEST_MAP_VALUE>map1 || HIGHEST_MAP_VALUE<map1);    //Stop the loop after getting valid MAP value for the Lower limitation
                        
                        System.out.print("Insert higher MAP value: ");              //Instruct user to insert Higher limitation of the MAP range
                        do
                        {
                            while (!input.hasNextInt())                             //Validation of the inserted MAP Value must be integer
                            {
                                System.out.print("Higher MAP value must be integer. Insert again: ");   //Instruct user to insert again if their insert is not integer
                                input.next();                                       //Allow user to re-insert
                            }
                            map2 = input.nextInt();                                 //User inserts value of Higher limitation for the MAP range
                            if (LOWEST_MAP_VALUE>map2 || HIGHEST_MAP_VALUE<map2)    // Check validation of inserted MAP Value is within allowed range of value or not
                            {
                                System.out.print("MAP value must be in range of "+LOWEST_MAP_VALUE+" to "+HIGHEST_MAP_VALUE+". Please insert again: ");
                                // Instruct user to re-insert MAP value of Higher limitation for the searching range
                            }
                        }
                        while (LOWEST_MAP_VALUE>map2 || HIGHEST_MAP_VALUE<map2);    // Stop the loop after getting valid MAP value for the Higher limitation
                        
                        if (map1>map2)                                              //Compare value of Lower and Higher limitation. 
                        {
                            // Instruct user to insert again if the value of Lower limitation is higher than the Higher limiation
                            System.out.println("The second MAP value must be higher. Please insert again");
                            System.out.println();
                        }
                    }
                    while (map1>map2);                                              //Stop the loop until value of both Lower and Higher limitation are valid

                    Record[] find = commandExecute.find(map1, map2);                //Execute method find(int map1, int map2) of class MAPAnalyser
                    if (find.length == 0)                                           //Check the array of matched record. If length of the array is 0, it means there is no matched record
                    {
                        System.out.println("There is no matched record found");     //Print out message to inform that no record found
                    }
                    else                                                            //If length of the array is not 0, there are matched record
                    {
                        System.out.println(find.length+" matched records are below.\nDisplay as <ID, SBP, DBP, MAP, Category>:");      //Print out matched record
                        for (int i = 0; i<find.length;i++)                          //Run loop to print out all the matched records
                        {
                            System.out.println(find[i].toString());                 //Print out matched records with all details of patient
                        }
                    }
                    break;                                                          //Stop Switch loop
                
                case 3:
                    help = command;         //User will need to confirm again to continue using the program after finish this command
                    System.out.println ("Lowest MAP value is: "+commandExecute.lowest());                    //Print out lowest value of MAP
                    System.out.println ("Highest MAP value is: "+commandExecute.highest());                  //Print out highest value of MAP
                    System.out.println ("Median MAP value after sorting is: "+commandExecute.median());      //Print out medium value of MAP
                    break;
                    
                case 9:
                    help = command;         //Allow the loop to stop and terminate program
                    System.out.println("Thank you for using the program!");             //Thank you user for using the program
                    break;
            }
            
            if (help !=0 && help!= 9)       //If previous command is different from 0 and 9, user can confirm again if they want to use program again or not
            {
                System.out.println();
                System.out.print("Do you want to run again? Command <0> to continue or other number to end: "); //Instruct user to insert command
                while (!input.hasNextInt()) //Validation of the command. Run loop to request user to insert integer only.
                {
                    System.out.print("Your command must be integer. Insert again: ");   //Instruct user to insert command as integer
                    input.next();           //Allow user to re-insert command
                }
                help = input.nextInt();     //User insert their command
                if (help != 0)              //Check the command to say thank to user for using program
                {
                    System.out.print("Thank you for using the program!");               //Say thank to user for using program
                }
                System.out.println();
                
            }
        }
        while (help == 0 ); //Only repeat the loop if command is 0
    }
}
