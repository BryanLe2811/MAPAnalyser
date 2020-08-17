/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 * Student ID: 12105847
 * Student name: Van Manh Le
 */
public class Assignment2 {

    public static void main(String[] args) {
        // TODO code application logic here
        MAPAnalyser a = new MAPAnalyser();      // Create object a of class MAPAnalyser
        View v = new View(a);                   // Create object v of class View
        v.commandLoop();                        // Execute Command Loop and prompt command from user
   
    }
    
}
