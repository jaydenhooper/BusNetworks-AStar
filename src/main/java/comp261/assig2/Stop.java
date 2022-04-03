package comp261.assig2;

import java.util.ArrayList;
import java.util.Iterator;

public class Stop {
        private GisPoint loc;
        private String name;
        private String id;
        private int orderId;
        
        private ArrayList<Trip> trips;
        private ArrayList<Edge> neighbors;

        private boolean visited; // a temp visited flag useful for DFS, connected components and pathfinding
        private double cost; // temp cost associated with a path finding task

        private int subGraphId;
        private Stop root;
    
        public Stop(double lon, double lat, String name, String id, int orderId) {
            this.loc = new GisPoint(lon, lat);
            this.name = name;
            this.id = id;
            this.orderId = orderId;
        }

        public Stop(double lon, double lat, String id) {
            this.loc = new GisPoint(lon, lat);
            this.id = id;
            this.orderId = -1;
        }
    
        public Stop(double lon, double lat) {
            this.loc = new GisPoint(lon, lat);
            this.name = "";
            this.id = "lon:" + lon + ";lat:" + lat;
            this.orderId = -1;
        }
    
        public double getLon() {
            return this.loc.getLon();
        }

        public double getLat() {
            return this.loc.getLat();
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public GisPoint getPoint() {
            return this.loc;
        }   

        public double distance(GisPoint loc) {
            return this.loc.distance(loc.getLon(), loc.getLat());
        }   

        public String toString() {
            return id + ": " + name + " at (" + loc.getLon() + ", " + loc.getLat() + ")";       
        }

        public boolean equals(Stop node) {
            return this.id == node.id;
        }


        public boolean equals(String name) {
            return this.name.equals(name);
        }

        public boolean equals(GisPoint point) {
            return this.loc.equals(point);
        }

        public double distance(Stop toStop) {
            return this.loc.distance(toStop.loc);
        }

        public void addTrip(Trip trip) {
            if (this.trips == null) {
                this.trips = new ArrayList<Trip>();
            }
            this.trips.add(trip);
            
        }

        //get trips
        public ArrayList<Trip> getTrips() {
            return this.trips;
        }

        public void makeNeighbours(Graph graph){
            if(neighbors == null){
                neighbors = new ArrayList<Edge>();
            }
            if (trips != null) {
                for(Trip t : trips){
                    for( int i = 0; i< t.getStops().size()-1; i++){
                        if(t.getStops().get(i).equals(this.id)){
                            Stop neighbour = graph.getStops().get(t.getStops().get(i+1));
                            Double time = Double.POSITIVE_INFINITY;
                            
                            double speed=Transport.getSpeedMPS(t.getStop_pattern_id());
                            if (t.getTimes().get(i)==null || t.getTimes().get(i+1)==null){
                                System.out.println("Time is null");
                                time = this.distance(neighbour)/speed;
                            }else{
                                time = t.getTimes().get(i+1)-t.getTimes().get(i);
                                if (time<0) {
                                    System.out.println("Time is negative");
                                    time = this.distance(neighbour)/speed;
                                }
                            }
                            
                            Edge e = new Edge(this, neighbour, t.getStop_pattern_id(), time);
                            this.neighbors.add(e);
                        }
                    }
                }
            }else{
                System.out.println("No trips for stop: " + this.id);
            }
        }
        
        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public double getCost() {
            return cost;
        }

        public ArrayList<Edge> getNeighbours() {
            return neighbors;
        }

        public void setNeighbours(ArrayList<Edge> neighbours) {
            this.neighbors = neighbours;
        }

        public void setSubGraphId(int subGraphId) {
            this.subGraphId = subGraphId;
        }

        public int getSubGraphId() {
            return subGraphId;
        }

        public void setRoot(Stop root) {
            this.root = root;
        }

        public Stop getRoot() {
            return root;
        }

        //add neighbour
        public void addNeighbour(Edge e) {
            if (this.neighbors == null) {
                this.neighbors = new ArrayList<Edge>();
            }
            this.neighbors.add(e);
        }

        public void deleteEdge(Stop to, String tripId) {
            for (Edge edge :this.neighbors) {
                if (edge.getToStop().equals(to) && edge.getTripId().equals(tripId)) {
                 //   this.neighbors.remove(edge);
                }
            }
        }

        // Read more: https://www.java67.com/2014/03/2-ways-to-remove-elementsobjects-from-ArrayList-java.html#ixzz7OrkLI5fh
        public void deleteAllWalkingEdges() {
            // remove edges that match the walking trip pattern id
            Iterator<Edge> edge_itr = this.neighbors.iterator();
            while (edge_itr.hasNext()) { 
                Edge edge = edge_itr.next(); 
                if (edge.getTripId().equals(Transport.WALKING_TRIP_ID)) { 
                    edge_itr.remove(); 
                }
            }

        }



    }
