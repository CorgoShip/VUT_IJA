package classes;

import java.util.ArrayList;

public class Street {
    private String name;
    private ArrayList<Stop> stops = new ArrayList<Stop>();
    private Coordinate from;
    private Coordinate to;
    private boolean closed;
    private double traffic;

    public String getName() {
        return name;
    }

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public Coordinate getFrom() {
        return from;
    }

    public Coordinate getTo() {
        return to;
    }

    public boolean isClosed() {
        return closed;
    }

    public double getTraffic() {
        return traffic;
    }

    public void printAll()
    {
        System.out.println(this.name);
        System.out.println("from:");
        this.from.printAll();
        System.out.println("to:");
        this.to.printAll();
        System.out.println(this.closed);
        System.out.println(this.traffic);

        for (Stop item : this.getStops()) {
            System.out.println(item.getId());
            item.getCoordinate().printAll();
            System.out.println();
        }
    }
}
