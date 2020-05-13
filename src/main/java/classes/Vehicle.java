package classes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Drawable, Movable{
    private Coordinate position;
    private String id;
    private transient List<Shape> gui;

    public Vehicle(Coordinate p,String id)
    {
        this.position = p;
        this.id = id;
        gui = new ArrayList<Shape>();
        gui.add(new Circle(position.getX(),position.getY(),5, Color.BLUE));
        gui.add(new Text(position.getX()+4,position.getY()+3,id));
    }

    @Override
    public List<Shape> getGui() {
        return this.gui;
    }

    @Override
    public void move() {
        //2. pohnu se k bodu
        double dx = 100 - this.position.getX();
        double dy = 100 - this.position.getY();
        double lenght = Math.sqrt(dx*dx + dy*dy);

        //System.out.println(dx);
        //System.out.println(dy);
        //System.out.println(lenght);

        if(lenght <= 5)
        {
            this.position.setX(this.position.getY() + dx);
            this.position.setY(this.position.getY() + dy);

            for (Shape item: gui)
            {
                item.setTranslateX(dx + item.getTranslateX());
                item.setTranslateY(dy + item.getTranslateY());
            }
        }
        else
         {
             double part = 5 / lenght;

             this.position.setX(this.position.getX() + dx * part);
             this.position.setY(this.position.getY() + dy * part);

             for (Shape item: gui)
             {
                 item.setTranslateX(dx * part + item.getTranslateX());
                 item.setTranslateY(dy * part + item.getTranslateY());
             }
         }
    }
}
