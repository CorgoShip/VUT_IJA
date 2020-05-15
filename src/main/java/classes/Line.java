package classes;

import java.util.ArrayList;

import java.util.*;


public class Line {
    private String id;
    private ArrayList<Point> points = new ArrayList<Point>();
    private ArrayList<String> vehicles = new ArrayList<String>();

    public Line() {
    }

    public String getId (){ return this.id;}

    public ArrayList<Point> getPoints() {
        return points;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addVehicle(String id)
    {
        vehicles.add(id);
    }

    public Line(Line line){
        this.id = line.id;
        this.vehicles = line.vehicles;
        for (Point point: line.getPoints())
        {
            this.points.add(new Point(point));
        }
    }

}
