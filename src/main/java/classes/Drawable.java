package classes;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.shape.Shape;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface Drawable {
    List<Shape> getGui();
    String getName();
    ArrayList<Stop> getStops();
    Coordinate getFrom();
    Coordinate getTo();
    public double getTraffic();
    ArrayList<Shape> getSymbols();
    void setTrafficControl(Slider slider, Label label);
    void setTraffic(double t);
}
