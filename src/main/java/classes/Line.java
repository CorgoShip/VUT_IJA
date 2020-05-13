package classes;

import java.util.ArrayList;

public class Line {
    private ArrayList<Point> points = new ArrayList<Point>();
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public Line() {
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}
