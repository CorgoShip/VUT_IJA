package classes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class Vehicle implements Drawable{
    private Coordinate position;
    private String id;

    public Vehicle(Coordinate p,String id)
    {
        this.position = p;
        this.id = id;
    }

    @Override
    public List<Shape> getGui() {
        return Arrays.asList(
                new Circle(position.getX(),position.getY(),5, Color.BLUE),
                new Text(position.getX()+4,position.getY()+3,id)
        );
    }
}
