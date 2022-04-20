package comp261.assig2;

/*
Dijkstra search alogrithm

@author: Simon McCallum, Github Copilot
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ResourceBundle.Control;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// dijkstra class for path finding in the graph
public class AStar {

    private static boolean timeHeuristic = true;
    private static String transportType = "All";

    public static ArrayList<Edge> findShortestPathEdges(Graph graph, Stop start, Stop end) {

        // check if the start and end stops are null
        if (start == null || end == null) {
            return null;
        }
        // used to check how many nodes you visited. Lower is better H.
        int totalExplored = 0;


        ArrayList<Stop> stops = graph.getStopList();

        // creating a comparison function for the priority queue based on Path
        Comparator<PathItem> pathStopCompare = new PathCostComparator();
        // create a new priority queue for the finge
        PriorityQueue<PathItem> fringe = new PriorityQueue<PathItem>(pathStopCompare);

        // create a new array list for the path to be extracted
        ArrayList<PathItem> visitedStops = new ArrayList<PathItem>();

        int currentFringeCost = 0;

        // vital step to make sure you can find the new path
        graph.resetVisited();

        start.setCost(0);

        double f = g(start) + heuristic(start, end); // also could be f(start,start, 0, start,end);
        fringe.add(new PathItem(start, 0, f, null, null)); // the input is (node, cost, f, prev, edge)

        // while the queue is not empty
        while (!fringe.isEmpty()) {
            // get the stop with the lowest cost
            // get the top of the queue
            PathItem current = fringe.poll();
            Stop currentStop = current.getStop();

            // check if the stop from the queue has been visited
            if (currentStop.isVisited()) {
                continue;
            }
            // if the stop has not been visited, mark as visited and add to
            // visitedStops list
            currentStop.setVisited(true);
            visitedStops.add(current);
            // set the cost for the current node
            totalExplored++;
            current.setCost(f);

            // if the current stop is the end stop
            if (currentStop == end) {
                ArrayList<Edge> shortestEdgePath = makeEdgePath(graph, visitedStops, start, end);
                return shortestEdgePath;
            }
            // go through each of the current stop's neighbours and add to the fringe
            for (Edge edge : currentStop.getNeighbours()) {
                // check if the transport type is specified
                if (!transportType.equals("All")){
                    // continue if the edge is NOT the transport type we are looking for
                    if (!Transport.getTransportType(edge.getTripId()).equals(AStar.transportType)) {
                        continue;
                    }
                }
                // get the neighbour from the edge
                Stop neighbour = edge.getToStop();
                // if the neighbour has not been visited already
                if (neighbour == null) {
                    continue;
                }
                // flexible walking distance calculated for challenge
                // if the edge is walking and we are using a time heuristic, set cost to distance / walking_speed_mps
                if(Transport.getTransportType(edge.getTripId()).equals("Walk")){
                    AStar.flexibleWalkingDistance(edge);
                }
                if (!neighbour.isVisited()) {
                    // set the neighbour's cost to the current stop's cost + the edge's cost (time or distance)
                    double g = current.getCost() + edge.getCost();
                    // Error checking is useful
                    if (g < 0) {
                        System.out.println("Error: negative cost");
                    }
                    neighbour.setCost(g);
                    // calculate the new f value using g edge cost and heuristic
                    f = g + heuristic(neighbour, end);
                    // add the neighbour to the queue as a new PathItem
                    fringe.add(new PathItem(neighbour, g, f, currentStop, edge));
                }
            }
        }
    // comment on failure
    System.out.println("Error: "+start+" to "+end+" not found");return new ArrayList<Edge>();
    }

    /**
     * build a path from the end back to the start from the PathItem data
     * 
     * @param graph
     * @param visited the nodes visited while searching for this trip
     * @param start
     * @param goal
     * @return the list of stops in the path
     */
    private static ArrayList<Edge> makeEdgePath(Graph graph, ArrayList<PathItem> visited, Stop start, Stop goal) {
        ArrayList<Edge> path = new ArrayList<Edge>();
        Edge currentItem = visited.get(visited.size() - 1).getEdge(); // the last edge added is on the path

        // while the current item is not the start
        while (currentItem.getFromStop() != start) {
            path.add(currentItem);
            // find the stop from the visited PathItems
            for (PathItem visitedItems : visited) {
                if (visitedItems.getEdge() == null) {
                    continue;
                }
                if (visitedItems.getEdge().getToStop() == currentItem.getFromStop()) {
                    if (visitedItems.getEdge().getToStop() == null) {
                        return null;
                    }
                    currentItem = visitedItems.getEdge();
                }
            }
            if (currentItem == null) { // if not invalid path
                System.out.println("error: ");
                return null;
            }
        }
        // finally add the start and return
        path.add(currentItem);
        return path;
    }

    public static double f(Stop current, double edgeCost, Stop neighbour, Stop end) {
        return g(current) + edgeCost + heuristic(neighbour, end);
    }

    public static double g(Stop current) {
        return current.getCost();
    }

    public static double heuristic(Stop current, Stop goal) {
        // calculate a heuristic for the current stop to the goal stop
        if(timeHeuristic){
            // heuristic should be admissible, never overestimate.
            double maxSpeed = Transport.getMaxMPS();
            double distance = current.distance(goal);
            return distance/maxSpeed;
        }
        return current.distance(goal);
    }

    /**
     * 
     * @return
     */
    public static boolean isTimeHeuristic() {
        return timeHeuristic;
    }

    public static void setTimeHeuristic(boolean timeHeuristic) {
        AStar.timeHeuristic = timeHeuristic;
    }

    public static void setTransportType(String transportValue) {
        AStar.transportType = transportValue;
    }

    /**
     * calculates the walking distance for the edge, whether the heuristic is time or distance
     * @param edge the edge to calculate the distance for
     */
    public static void flexibleWalkingDistance(Edge edge) {
        double distance = edge.getToStop().distance(edge.getFromStop());
        if(AStar.timeHeuristic){
            // need to recalculate from scratch, else distance may be equal to speed
            double time = distance / Transport.WALKING_SPEED_MPS;
            edge.setCost(time);
        }
        // set cost to distance
        edge.setCost(distance);
    }
}
