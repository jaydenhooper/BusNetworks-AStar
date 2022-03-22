# COMP261 Assignment 2 A* 
# Draft
The focus of this assignment is to demonstrate understanding of A* and algorithms related to graphs.  A* will be used to find the shortest paths in the Wellington Public Transport network. Then we will extend to consider other constrainst on travel around the graph.  This assignment builds on [Assignment 1](https://gitlab.ecs.vuw.ac.nz/lms/comp261/2022/ass1) adding searching and interaction with the data.

We will use additional information from [open data at Metlink](https://opendata.metlink.org.nz/).  Assignment 1 used stops.txt and stop_patterns.txt. For the challenge exercises you can download and extract additional information. The files can be downloaded from https://static.opendata.metlink.org.nz/v1/gtfs/full.zip 

### Minimum: A* search
A* working to find shortest path between two nodes.  This will be done by using the Start Busstop and Goal Busstop is done by clicking on a node to set it to start and then the next click will be the end node.  The path must be displayed in the tripText area for the sequence of stops and the stop_patterns used to link those stops. ie each outgoing edge on a stop should have a stop_pattern_id associated with that edge.  The output should be something like:   

### Core: Components and Connections
1. Show the components. Use depth first search to show the connected components. Use a recurvise depth first search to build a list of the connected components in the graph.  This will go through all stops, and set the visited field while keeping count of components found and the root node where you started the depth first search. Colour the stops in the different components to make them visible. 
2. Connect components with walking edges. Connect the separate graphs in the system with walking edges.  This will also connect Busstops that are on either side of a road. Update A* so that it can take the walking edges. After clicking on the check box include walking edges - update and show the component colours on the graph. 

### Complete: Time taken
Walking, busses, trains, cable car and the ferry,  all travel at different speeds. We are going to use average times for each based on the distance traveled.  The additional `speed.txt` file gives the speed for each stop_pattern_id. This is averaged from the original data from Metlink which uses the time for arrival at each stop on every possible trip during the day. Calculate the fastest time between two locations including walking up to 50 meters. Display time and distance traveled in the tripText 

### Challenge: Fun stuff
 * Limit travel by transportation type
 * Flexible walking distance calculated within A*
 * Implement pin dropping
 * Implement a onRoute stop (ie start to mid then mid to goal)
 * Download the metlink data and implment the Trips and Stoptime to actually use the real data to make a time of Day A*


