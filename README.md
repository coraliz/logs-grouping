# Logs Grouping 
This repository contains a program which it's purpose is to group the logs inside a file log. Each group contains all the logs are identical to each other, except for one word. 
For example, "01-01-2012 20:12:00 Neomi is eating at a restaurant" and "03-01-2012 10:23:00 George is eating at a restaurant that receives" can be grouped together.

 This program recieves as input the absoulte path of a log file which is provided by the user on the CLI.

## input file format
```
01-01-2012 10:14:00 George hates school
01-01-2012 20:12:00 George loves animals
02-01-2012 10:14:00 Nadav loves animals
02-01-2012 11:11:00 Nadav loves food
03-01-2012 13:55:00 Neomi loves animals
04-01-2012 18:32:00 Neomi loves food
``` 

An example input file which it's logs are grouping according to their patterns is provided [here]("https://github.com/coraliz/logs-grouping/blob/ready_for_pr/testFiles/test_grouping_logic_format2-grouped.txt").

- If the file has logs that are not similar to each other, the user gets the notification message "There are no logs that have the same pattern" as you can see if you run [this]("https://github.com/coraliz/logs-grouping/blob/ready_for_pr/testFiles/test_without_logs.txt") test file.

- If the file is empty, the user gets the notification message " `filePath` doesn't contain logs" as you can see if you run [this]("https://github.com/coraliz/logs-grouping/blob/ready_for_pr/testFiles/test_without_logs.txt") test file.

 Assumptions: 
 - The log file is valid. 
  
## Running the program
1. Clone this repo.
2. Make sure you have a Java 17+ .
3. Run src/Main and provide the file path to the input file. To use the example input file provided with repo run in your terminal (cmd/bash/etc.) from repo root:
    
    `java src/Main test_grouping_logic_format1.txt`



## Implementation references
- This code was written in Java 17.

## Contributions
This repo doesn't accept contributions.

