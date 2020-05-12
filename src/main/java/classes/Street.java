package classes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Street implements Drawable{
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

    @Override
    public List<Shape> getGui() {
        List<Shape> tmp = new ArrayList<Shape>();
        tmp.add(new Line(this.from.getX(),this.from.getY(),this.to.getX(),this.to.getY()));
        for (Stop item : stops)
        {
            tmp.add(new Circle(item.getCoordinate().getX(),item.getCoordinate().getY(),5, Color.GRAY));
            tmp.add(new Text(item.getCoordinate().getX()+4,item.getCoordinate().getY()+3,item.getId()));
        }

        tmp.add(new Text((this.getFrom().getX() + this.getTo().getX())/2,(this.getFrom().getY()+this.getTo().getY())/2,this.getName()));

        return tmp;
    }
}
