/**
 * Autori: Zbynek Lamacka xlamac01
 *         Simon  Pomykal xpomyk04
 *
 * Trida reprezentuje ulici. Ma nazev, seznam zastavek, ktere na ni lezi,
 * Souradnice zacatku a konce a hodnotu dopravy (ta je vyjadrena v procentech,
 * 0 znamena plna rychlost vozidel, 100 znamena, ze vozidla stoji). Obsahuje
 * jeste prvky pro UI.
 */

package classes;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    private transient ArrayList<Shape> symbols = new ArrayList<>();
    private transient Slider slider;
    private transient Label label;

    public Street() {

    }

    public void setTrafficControl(Slider slider, Label label)
    {
        this.slider = slider;
        this.label = label;
    }

    public ArrayList<Shape> getSymbols() {
        return symbols;
    }

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

    public void setTraffic(double t)
    {
       this.traffic = t;
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

        Shape line = new Line(this.from.getX(),this.from.getY(),this.to.getX(),this.to.getY());
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                slider.adjustValue(traffic);
                label.setText(name);
            }
        });

        tmp.add(line);
        for (Stop item : stops)
        {
            tmp.add(new Circle(item.getCoordinate().getX(),item.getCoordinate().getY(),5, Color.GRAY));
            Text text = new Text(item.getCoordinate().getX()-30,item.getCoordinate().getY()-6,item.getId());
            text.setFont(new Font(8));
            tmp.add(text);
        }

        Text text = new Text((this.getFrom().getX() + this.getTo().getX())/2 - (this.getName().length()*3),((this.getFrom().getY()+this.getTo().getY())/2) -4,this.getName());

        symbols.addAll(tmp);
        text.setFont(new Font(12));
        tmp.add(text);

        return tmp;
    }
}
