
The program assumes that there are 4 empty files namely 
(1) data.db 
(2) id.ndx
(3) lastname.ndx
(4) state.ndx

The program writes data into these files during the time of execution.
 
The us-500.csv file used to load to values initially into .db and .ndx files has been modified with delimiter as "|" instead of ",".

run() method has the commands for all the actions that can be perfomed on the data. The inputs are given as arguments inside this run() method.

The MyDatabase.java file has a class named MyDatabase which has the main() method defined inside it.
From the main() method a object of class MyDatabase is created and method run() is called using this object.

The modified us-500.csv file is also attached along with this folder. 