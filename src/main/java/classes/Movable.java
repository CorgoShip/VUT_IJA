package classes;

import javafx.scene.shape.Shape;

import java.time.LocalTime;
import java.util.List;

public interface Movable {
    boolean move(LocalTime time,List<Drawable> streets);
    List<Shape> getGui();
    Coordinate getPosition();
}
