package comp261.assig2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.paint.Color;

public class Graph {

    private HashMap<String, Stop> stops;
    private HashMap<String, Trip> trips;

    private ArrayList<Stop> stopList;
    public Trie trie;

    public Zoning geoJson;

    private int subGraphs = 0;

    public Graph(HashMap<String, Stop> stops, HashMap<String, Trip> trips) {
        this.stops = stops;
        this.trips = trips;
        buildStopList();
        createNeighbours();
    }

    // constructor with parsing
    public Graph(File stopFile, File tripFile, File geoJsonFile) {
        stops = new HashMap<String, Stop>();
        trips = new HashMap<String, Trip>();
        stops = Parser.parseStops(stopFile);
        trips = Parser.parseTrips(tripFile);
        geoJson = Parser.parseGeoJson(geoJsonFile);
        // caclculate exact memory usage of the graph

        buildStopList();
        buildTripData();
        createNeighbours();

    }

    // buildStoplist from hashmap
    private void buildStopList() {
        stopList = new ArrayList<Stop>();
        for (Stop s : stops.values()) {
            stopList.add(s);
        }
    }



    // buildTripData into stops
    private void buildTripData() {
        for (Trip trip : trips.values()) {
            trip.setColour(Color.hsb(Math.random() * 360.0, 1.0, (Math.random() * 0.5) + 0.5));
            for (String stopId : trip.getStops()) {
                Stop stop = stops.get(stopId);
                if (stop != null) {
                    stop.addTrip(trip);
                } else {
                    System.out.println("Missing stop pattern id: " + stopId);
                }
            }
        }
    }

    // build linked list
    private void createNeighbours() {
        for (Stop stops : stops.values()) {
            stops.makeNeighbours(this);
        }
    }

    // get first stop that starts with a search string
    public Stop getFirstStop(String search) {
        // Search for the first stop matching the search string
        // This is slow and would be faster with a Trie 
        Stop firstStop = null;
        for (Stop stop : stopList) {
            if (stop.getName().startsWith(search)) {
                firstStop = stop;
                break;
            }
        }
        return firstStop;
        // This would be the call to the Trie from Assignment 1
        //return trie.getAll(search).get(0);
    }

    // get all stops that start with a search string
    public List<Stop> getAllStops(String search) {
        //search for all stops matching the search string
        List<Stop> allStops = new ArrayList<Stop>();
        for (Stop stop : stopList) {
            if (stop.getName().startsWith(search)) {
                allStops.add(stop);
            }
        }
        return allStops;
        // This would be the call to the Trie from Assignment 1
        // return trie.getAll(search);
    }

    // getter for stopList
    public ArrayList<Stop> getStopList() {
        return stopList;
    }

    public HashMap<String, Stop> getStops() {
        return stops;
    }

    public void setStops(HashMap<String, Stop> stops) {
        this.stops = stops;
    }

    public HashMap<String, Trip> getTrips() {
        return trips;
    }

    public void setTrips(HashMap<String, Trip> trips) {
        this.trips = trips;
    }



    public void resetVisited() {
        for (Stop stop : stopList) {
            stop.setVisited(false);
            stop.setCost(Double.MAX_VALUE);
        }
    }


    // find graph components and lable the nodes in each component
    public int findComponents() {
        // TODO: implement component analysis

        // TODO: reset visited and cost        
        
        // TODO: go through all stops use recursive to visitAllConnections and build visit order

        // TODO: search in reverse visit order setting the root node recurssively on assignRoot
        // TODO: keep a count of the components
        
        // TODO: set the subgraphs to number of components and return it
        // something like subGraphs = components;
        return subGraphs;
    }

    /**
     * Recursively visit all connections of a stop and build a list of stops visited from this stop
     */
    private void visitAllConnections(Stop stop, ArrayList<Stop> visitOrder) {
        // TODO: set vistited to true
        // TODO: add stop to visitOrder
        // TODO: for each edge of the stop if the ToStop is not visited, recurse this function with the toStop of the neighbour Edge
        
    }

    /**
     * Recursively assign the root node of a subgraph
     */
    public void assignRoot(Stop stop, Stop root, int component) {
        // TODO: set the root of the subgraph to the stop, and the subgraph ID
        // TODO: for each of the edges in neighbours if the toStop root is empty recurse assigning root and component
    }

    /**
     * reset the root and the subgraph ID of all stops
     */
    public void resetRoot() {
        for (Stop stop : stopList) {
            stop.setRoot(null);
            stop.setSubGraphId(-1);
        }
    }


    public int getSubGraphCount() {
        return subGraphs;
    }

    // add walking edges
    public void addWalkingEdges(double walkingDistance) {
        // TODO: add walking edges to all stops
        int count = 0;
        // TODO: step through all stops and all potential neighbours 
         {
             {
                //TODO: check the distannce between to stops and if it is less then walkingDistance add an edge to the graph
                // something like:   
                // stopFrom.addNeighbour( new Edge(stopFrom, stopTo, Transport.WALKING_TRIP_ID, stopFrom.distance(stopTo)/Transport.WALKING_SPEED_MPS));
                
                // count the number of edges added
                count++;

            }
        }
        System.out.println("Walking edges added: " + count);
    }

    // remove walking edges  - could just make them invalid or check the walking_checkbox
    public void removeWalkingEdges() {
        for (Stop stop : stopList) {
            stop.deleteAllWalkingEdges();
        }
    }

    // A nicer way to build a string of a list of stops
    public String DisplayStops(List<Stop> listOfStops) {
        String returnString = "";
        for (Stop stop : listOfStops) {
            returnString += stop.getName() + "\n";
        }
        return returnString;
    }

}
