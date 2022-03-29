package comp261.assig2;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Trip {
    
    private String stop_pattern_id;
    ArrayList<String> stopIds;
    ArrayList<Double> times;
    private Color colour;

    // constructor post parsing
    public Trip(String stop_pattern_id, ArrayList<String> stopIds, ArrayList<Double> times) {
        this.stop_pattern_id = stop_pattern_id;
        this.stopIds = stopIds;
    }

    // constructor pre parsing
    public Trip(String stop_pattern_id) {
        this.stop_pattern_id = stop_pattern_id;
        this.stopIds = new ArrayList<String>();
        this.times = new ArrayList<Double>();
    }

    public void addStopId(String stopId, Integer stop_sequence, Double time) {
        this.stopIds.add(stopId);
        this.times.add(time);
    }

    public String getStop_pattern_id() {
        return stop_pattern_id;
    }

    public void setStop_pattern_id(String stop_pattern_id) {
        this.stop_pattern_id = stop_pattern_id;
    }

    public ArrayList<String> getStopIds() {
        return stopIds;
    }

    public void setStopIds(ArrayList<String> stopIds) {
        this.stopIds = stopIds;
    }

    // to string
    public String toString() {
        String s = "";
        s += "Trip: " + stop_pattern_id + "\t stops: " + stopIds.toString() + "\t times: " + times.toString();
        return s;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public ArrayList<Double> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<Double> times) {
        this.times = times;
    }

}
