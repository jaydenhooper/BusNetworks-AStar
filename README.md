
# COMP261 Assignment 2 A*

Due: Wed 13 April 2022 (first week of mid-trimester break)

The focus of this assignment is to demonstrate an understanding of A* and connected component algorithms related to graphs. 
A* will be used to find the shortest paths in the Wellington Public Transport network. 
Then we will extend to consider other constraints on travel around the graph. 
This assignment builds on[ Assignment 1](https://gitlab.ecs.vuw.ac.nz/lms/comp261/2022/ass1) adding searching and interaction with the data.

Assignment 1 used stops.txt and stop_patterns.txt. 
There is a **new file** `stop_pattern_times.txt` which contains similar information but with a time in seconds from the start of the trip as the last column.  
For the complete task in the assignment you will need to use this additional data. 
For challenge exercises additional data can be downloaded from [https://static.opendata.metlink.org.nz/v1/gtfs/full.zip](https://static.opendata.metlink.org.nz/v1/gtfs/full.zip)

## Submission
The submission will be using the ECS submission systems and we would strongly prefer the use of the ECS git submission so that submission status of the files can be tracked. We will reuse the folder for assignment-1 https://gitlab.ecs.vuw.ac.nz/course-work/comp261/2022/assignment-1/<username> with your username.  Fork this repository into that area by selecting the `fork` in the top left of this page.  Select the repository area to fork to, to be the repository above. Once you have forked the assignment, you can clone the assignment using the blue `Clone` button to get a copy of your project on your local machine. When you have working code push it to the fork you cloned the project from, and then go to the submit page and select the Assignment 2 repository to submit from the gitlab option.  This will also put a tag in the repository to show when you submitted.  You can check the tags on the left bar `Repository->Tags`.  You can still submit a .zip of the code but if there are any issues or we want to give you partical credit for fixing last minute issues that cannot be done with the .zip file.


### Minimum: A* search [0-40]
A* to find the shortest path between two nodes. This will be done by using the Start bus stop and the Goal bus stop.  
The code template assigns the start and goal by clicking on a node to set it to start and then the next clicking on the goal node. 
This will call **your A* Algorithm**. The path is displayed in the tripText area for the sequence of stops and the stop_patterns used to link those stops. 
* [10] Find any path between two nodes
* [10] Find the shortest path between two nodes (Dijkstra from Tutorial 3)
* [20] Find the shortest path limiting the number of nodes visited by using a distance heuristic (Adding heuristic)
i.e. each outgoing edge on a stop should have a stop_pattern_id associated with that edge. The output should be something like:
```
Start: 
5915:4915	walking  	cost 61	    :to goal 05:02
4915:4914	22_1  	    cost 242	:to goal 04:02
Edge goal 05:02
```

### Core: Components and Connections [40-65]
1. [15] Show the components. Use depth-first search to show the connected components. Use a recursive depth-first search to build a list of the connected components in the graph. This will go through all stops and set the visited field while keeping count of components found and the root node where you started the depth-first search. Colour the stops in the different components to make them visible. 
2. [10] Connect the components with **walking edges**. Connect the separate graphs in the system with walking edges. This will also connect Bus stops that are on either side of a road. Make sure A* is using the walking edges. After clicking on the check box include walking edges - update and show the component colours on the graph.

### Complete[75-85]: Time taken
[10] Walking, buses, trains, cable car and the ferry, all travel at different speeds. The stop_pattern_times.txt includes the time in seconds from the start of the trip. Assume that a bus is waiting for you at any stop.  Work out the time take for each journey, and update A* to use time as the cost metric rather than distance.


### Challenge[85-100]: Fun stuff do something interesting
* [5] Allow time or distance in A* search
* [5] Limit travel by transportation type
* [5] Flexible walking distance calculated within A*
* [5] Implement pin dropping for start and goal points
* [10] Implement an on route stop (ie start to mid then mid to goal)
* [15] Download the Metlink data and implement the Trips and Stoptime to actually use the real data to make a time of Day A*
* ... Other stuff that impresses the marker for the final interesting algorithm or data stucture.


