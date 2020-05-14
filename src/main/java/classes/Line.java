package classes;

import java.util.ArrayList;

public class Line {
    private String id;
    private ArrayList<Point> points = new ArrayList<Point>();
    private ArrayList<String> vehicles = new ArrayList<>();

    public Line() {
    }

    public String getId (){ return this.id;}

    public ArrayList<Point> getPoints() {
        return points;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }
}
