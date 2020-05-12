package classes;

import java.util.ArrayList;

public class Data {
    private ArrayList<Stop> stops = new ArrayList<Stop>();
    private ArrayList<Street> streets = new ArrayList<Street>();

    public Data(ArrayList<Stop> stops, ArrayList<Street> streets) {
        this.stops = stops;
        this.streets = streets;
    }

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public ArrayList<Street> getStreets() {
        return streets;
    }

    public void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
    }

    public void setStreets(ArrayList<Street> streets) {
        this.streets = streets;
    }
}
