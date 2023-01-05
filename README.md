# Logs Grouping - Exercise
This repository contains a program which is implemented with Java and designed to group logs from a file and create a new file of it. 
Each group contains all the logs that are identical to each other except for one word. 
For example, `"01-01-2012 20:12:00 Neomi is eating at a restaurant"` and `"03-01-2012 10:23:00 George is eating at a restaurant that receives"` can be grouped together.

 This program receives an input of the absolute path for a log file which the user provides when prompted by the program. 
 After this alogrithm has ran, the output file will be extracted to the same directory. The output file's name is identical to the input file's name with the suffix: `"-grouped"`. 

### Input File Format Example
```
01-01-2012 10:14:00 George hates school
01-01-2012 20:12:00 George loves animals
02-01-2012 10:14:00 Nadav loves animals
02-01-2012 11:11:00 Nadav loves food
03-01-2012 13:55:00 Neomi loves animals
04-01-2012 18:32:00 Neomi loves food
``` 

### Output File Format Example
```
01-01-2012 19:45:00 Neomi is getting into the car
02-01-2012 09:13:15 George is getting into the car 
The changing words: Neomi, George

02-01-2012 10:14:15 George is eating at a diner
02-01-2012 10:14:15 Neomi is eating at a diner 
The changing words: George, Neomi
```

examples of input and output files with their corresponding logs that were  grouped according to their patterns are provided in the [testFiles](testFiles) directory.

- [test_grouping_logic_format1.txt](testFiles/test_grouping_logic_format1.txt): happy flow with multiple patterns and empty lines. 


- [test_grouping_logic_format2.txt](testFiles/test_grouping_logic_format2.txt): happy flow with many patterns.
  

- [test_empty_file.txt](testFiles/test_empty_file.txt): demonstrates empty file handling - the user gets a notification about an empty file.


- [test_ungrouped_logs.txt](testFiles/test_ungrouped_logs.txt): demonstrates logs without repeating pattern handling - the user gets a notification about a lack of similar logs. 



#### Expectations:
- The log file is valid. 
  
- The log sentences can be of any length.
  
- There is a single space between words.
  - Identical patterns will have the same amount of spaces.

### Running The Program
1. Clone this repo.
2. Install Java 17+ if needed.
3. Open the project folder with your favorite Java IDE and run the Main.java in the src folder.  
4. Provide a valid log file path, you can use one of the logs files in the [testFiles](testFiles) directory.

### Implementation References
- The program is implemented with Java 17.
- Java 8+ features used such as:
  - Streams
  - try-with-resources statements
  - lambda expressions
  - var keyword

## Contributions
This repo doesn't accept contributions.

