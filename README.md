# COMP261 Assignment 2 A*

Due: Wed 13 April 2022 (first week of mid-trimester break)

### [Q&A](Q&A.md)

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

The submission will include
* The Java files and the data files needed for the project - if submitting by gitlab repo this is included in the submission
* A Readme.md in the root file replacing this with a description of which parts of the assignment you completed
* A section in the readme discussing directed multigraphs and why the edges have to be stored as the path is generated  

The places to Edit are listed with TODOs.  Ones that say Assignment 1 should be able to be replaced with how you did assignment 1.

**You can change any of the code to make it easier for you to use and you can add any imports you want**



### Minimum: A* search [0-40]
A* to find the shortest path between two nodes. This will be done by using the Start bus stop and the Goal bus stop.  
The code template assigns the start and goal by clicking on a node to set it to start and then the next clicking on the goal node. 
The search strings display the start and goal, and if changed will initiate search when you press enter 
This will call **your A* Algorithm**. The path is displayed in the tripText area for the sequence of stops and the stop_patterns used to link those stops. 
* [10] Find any path between two nodes
* [10] Find the shortest path between two nodes (Dijkstra from Tutorial 3)
* [20] Find the shortest path limiting the number of nodes visited by using a distance heuristic (Adding heuristic = A*)
i.e. each outgoing edge on a stop should have a stop_pattern_id associated with that edge. The output should be something like:
```
Start: Wellington University - Stop B
5915:4915	walking  	cost 61	    :to goal 05:02
4915:4914	22_1  	    cost 242	:to goal 04:02
Goal The Terrace (near 230) 05:02
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

## File description
Main parts of Assignment 2 needing updates
* Astar.java - This has all the A* code to implement in TODOs
* Graph.java - This is where the connected components are calculated - find the TODOs and add the algorithm here.
* GraphController.java - There are TODOs in here to be updated with drawing code both Assignment 2 and Assignment 1 updates.

Update from Assignment 1
* Parser.java - This has a couple of the assignment 1 updates to fix in the TODOs
* Trie.java - currently this is empty so you can use your code from Assignment 1 and hook it up to the start and goal search. 

Do not need to be changed but can be
* Stops.jave - this is the structure that holds the stop information - you may want to merge with your own Assignment 1 version
* Tranport.java - holds important statics for working out what kind of journey a trip_id is and speeds
* GisPoint.java - Updated to calculate distance correctly in meters. Critical for locating the stops
* Projection.java - helper class which solves all the projections clearnly.  Use [`Projection.screenToModel()`](src/main/java/comp261/assig2/Projection.java#17)
* Edge.java - is the data structure for an edge, it holds the stops and the cost/time/distance
* PathCostComparitor.java - used by the Priority queue to sort the PathItems so the lowest is at the top to guide path searching
* Zoning.java - used to load the zones from the Wellington.gis.json.csv file  
* Main.java - cleaned up so not longer has static globals - look under the Graphcontroller for variables.
* Mapview.fxml - you can improve the GUI but it should have the buttons and controls you need.
* PathItem.java - This stores the pathItem for A*, this is mostly a data structure with getters and setters and a toString
* Trip.java - mainly a data structure for holding the read information about a trip, used in graph to create neighbouring edges.




