****************
* Project: 01 / Grid Monitor
* Class: CS 221
* Date: 1-22-2021
* Rich Boundji
**************** 

OVERVIEW:
 
 this program provides information about cells and the cells around them to determine if
 it is too high or too low to operate without exploding. it provide the sum of the surrounding
 cells and the average and more information.

 
INCLUDED FILES:

 GridMonitor.java - source file
 GridMonitorInterface.java - source file
 GridMonitorInterface.java - source file
 README - this file

COMPILING AND RUNNING:
 
 Each array have there own use and are all initialize in the constructor, 
 the amount of rows and columns are set after the file is scanner.
 the first two integers in the file are used as the row and columns number.
 to avoid repeating the same code I made it so that when a method needs information from 
 another method they can simply call upon it, doing so prevent me from having to 
 repeat the same code and having more areas that can create errors.
 

TESTING:

 GridMonitorTest.java was the primary way of testing for errors that I use, but I also 
 at some points made it so that the information that the GridMonitorTest.java would 
 be displayed using the toString method. GridMonitorTest.java tested all of the methods
 to make sure that they had the correct output. I am not definitely sure if the program
 would still work if the file that it was given was not formated correctly. aside from that 
 I do not believe that there is any other bug in the code.

DISCUSSION:
 something that I notice that I had done was that when I made the getSurroundingSumGrid()
 up and left were switched, the same thing happened with down and right.aside from that the 
 only other  thing was that when making the getDangerGrid() i did not call for the
 getDeltaGrid() and so the code did not have the info it needed and could not work.


EXTRA CREDIT:

N/A

----------------------------------------------------------------------------
