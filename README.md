1.	Introduction

In this assignment, you are to implement a console application that supports simple MAP (Mean Arterial Pressure) analysis functionality. A phased implementation approach is recommended and an outline for such an approach is provided in Appendix 1. The application is to be conformant with the class diagram provided in Figure 1.

2.	The Application

The raw data to be analysed is to be stored in 3 arrays. Use array initialisers to specify initial values of your choosing. The 3 arrays are to contain person identifiers (string values), systolic blood pressure (SBP) and diastolic blood pressure (DSP). Both SBP and DSP are to be specified as int values. MAP is determined from an individual’s blood pressure using the following formula:
MAP = 1.0/3.0 * SBP + 2.0/3.0 * DBP
The raw data is to be used to create objects of type Record, which are to be stored in an array of type Record. The Record class is to conform to the class shown in the UML Diagram below:
 
While map is calculated as a floating point value, it is to be stored as an int value. In addition to the map attribute, Record also has a category attribute, which has values of “high”, “medium” and “low”. A normal mean arterial pressure is a value in the range 70 and 100 mmHg inclusive.
Category determination is to be provided as a separate (private) method called classify(). Creation of the Record array is to occur when the MAPAnalyser object is created, using a private method called loadFromTables(). This method will iterate through three separate arrays containing ids, systolic blood pressures and diastolic blood pressures, construct Record objects and add them to the data array. Populate these arrays using array initializers. In order to facilitate search, records are to be maintained in ascending order of person id. Sorting is to be done after record creation, using a private method called sortById(). This method must implement the selection sort algorithm. Both load FromTables() and sortById() are to be invoked from the constructor for the MAPAnalyser class.

The application’s View class is to execute (using a switch statement) the following command options:
1.	Display the record for a specified person
2.	Display records for all persons with MAP values within a specified range.  
3.	Display the minimum, maximum and median MAP values
4.	Exit the application
As it is a console application, the user will need to be prompted to enter a command and its arguments (if any). My personal preference is for a minimal interaction scheme, as shown below:
run:

The following commands are recognised
	Display this message                                   > 0
	Display a specific subject record:                     > 1 id
	Display records for all subject records within a range > 2 map1 map2
	Display statistics (minimum, maximum and median)       > 3
	Exit the application                                   > 9
	
> 1 S01
<S01,120,81,94.0, normal>
>
Feel free to adopt the above scheme or if you prefer, implement a more verbose interaction scheme.

Note that 
1.	Each command is designated a number
2.	Command 1 requires a single argument – the subject id
3.	Command 2 requires 2 arguments – the lower and upper bounds of a range of MAP values. 
4.	I have added a “help” command (command 0)
5.	The command options are displayed at the start of the application and whenever a “help” command is entered, rather than after each command.
6.	Records are displayed (with no explanation of the fields) using Record.toString()
7.	For a sorted list of values, if the number of values is odd, the median is the middle value. If the number of values is even, the median is the average of the two middle values. 

For the commands that require arguments note that
*	For commands 1 and 2, basic error checking is expected.  For command 1, an error message is to be displayed if an id does not exist. For command 2, an error message is to be displayed if a member of the range is < 0 or > 200 or if the second member of a range is less than the first member. 
*	For command 2, the range is inclusive of the values specified.  Consequently, having both members of a range equal is valid. 
*	For command 1, binary search is to be used.
*	For command 2, the results are to be stored in an array for display. The length of the array will correspond to the number of records found. 
