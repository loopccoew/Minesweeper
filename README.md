# Minesweeper
1. Random Class:

Java's random class (part of util package) has been used, more specifically the nextInt() function of this class has been used to generate random numbers for the number and location of mines in the makeBoard function (line 34).

For more information: https://www.geeksforgeeks.org/java-util-random-class-java/ 

2. Calendar Class:

Java's calendar class has been used to calculate the time duration of each user's play. 
Line 17: "Calendar c= Calendar.getInstance();", creates an instance of the calendar class based on the current time in the default time zone with the default locale. 
On the basis of that instance the time is accessed using (Line 25):

start[0]=c.get(Calendar.HOUR_OF_DAY);//start[0] stores the hour at which the instance was created
start[1]=c.get(Calendar.MINUTE);//start[1] stores the minute at which the instance was created
start[2]=c.get(Calendar.SECOND);//start[2] stores the second at which the instance was created

Likewise, the end time is calculated either when a mine is selected or when the user completes the game without clicking on a "mine". (Calendar instance c1)

For more information: https://www.geeksforgeeks.org/calendar-class-in-java-with-examples/ 

*(Content credits on respective sites)*
