package classes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
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
            Text text = new Text(item.getCoordinate().getX()-30,item.getCoordinate().getY()-6,item.getId());
            text.setFont(new Font(8));
            tmp.add(text);
        }

        Text text = new Text((this.getFrom().getX() + this.getTo().getX())/2 - (this.getName().length()*3),((this.getFrom().getY()+this.getTo().getY())/2) -4,this.getName());
/**
        double angle = Math.atan2(this.getFrom().getY() - this.getTo().getY(), this.getFrom().getX() - this.getTo().getX());
        angle = angle * 180 /  Math.PI ;
        //text.setRotate(angle);

        if (angle < 90)
        {
            text.setRotate(angle + 180);
            text.setX(text.getX() + 5);

        }
        else if (angle < 180)
        {
            text.setRotate(-(180-angle));
        }
        else if (angle < 270)
        {
            text.setRotate(angle - 180);
        }
        else
        {
            text.setRotate(-(360-angle));
            System.out.println(this.getName());
        }
*/
        text.setFont(new Font(12));
        tmp.add(text);

        return tmp;
    }
}
