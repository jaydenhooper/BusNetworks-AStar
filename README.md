## What the code does
* [x] Minimum
* [x] Core
* [x] Completion
* Challenge
   * [x] Allow time or distance in A* search 
   * [x] Limit travel by transportation type
   * [x] Flexible walking distance calculated within A*


## Discussion on directed multigraphs and why the edges have to be stored as the path is generated
Directed multigraphs are graphs that have more than 1 component with directed edges. Components are subgraphs or disconnected graphs. Directed edges are edges that can only go one way. 

Edges have to be stored before the path is generated so that we can calculate the f-costs (actual cost + heuristic) accurately when going through the graph. 

## Challenge notes
Time/Distance cost can be changed by clicking a checkbox in the GUI.
Travel has been limited by transportation type through a drop down box in the GUI. 
The walking distance has been recaclulated during the A* search to update for time/distance.




