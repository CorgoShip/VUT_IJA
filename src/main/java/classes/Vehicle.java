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
    private Line line;
    private Line currentLine;

    public Vehicle(Coordinate p,String id,Line line)
    {
        this.position = p;
        this.id = id;
        gui = new ArrayList<Shape>();
        gui.add(new Circle(position.getX(),position.getY(),5, Color.BLUE));
        gui.add(new Text(position.getX()+4,position.getY()+3,id));
        this.line = line;

        //TODO: current line se bude nastavovat podle casu (odstrani se ze zecatku pointe ktere se uz ujely)
        this.currentLine = line;
    }

    @Override
    public List<Shape> getGui() {
        return this.gui;
    }

    @Override
    public void move() {
        //ziskam bod ke kteremu mam prave jet
        Point nextpoint = currentLine.getPoints().get(0);

        //vypocitam vzdalenost na x a y ose a velikost primky mezi vozidlem a bodem
        double dx = nextpoint.getCoordinate().getX() - this.position.getX();
        double dy = nextpoint.getCoordinate().getY() - this.position.getY();
        double lenght = Math.sqrt(dx*dx + dy*dy);


        //vzdalenost od bodu je mensi nez rychlost za jednotku casu
        if(lenght <= 5)
        {
            //odstranim bod ke ktermu jsem prave prijel
            currentLine.getPoints().remove(0);


            //nastavim pozici vozidla na pozici bodu
            this.position.setX(this.position.getX() + dx);
            this.position.setY(this.position.getY() + dy);

            for (Shape item: gui)
            {
                item.setTranslateX(dx + item.getTranslateX());
                item.setTranslateY(dy + item.getTranslateY());
            }

            //TODO: tady bude if - else , pokud to bude zastavka tak stojim, pokud ot bude krizovatka, zmenim ulici
        }

        //vzdalenost od bodu je vetsi nez rychlost za jednotku casu
        else
         {
             //spocitam cast vzdalenosti, kterou chci ujet
             //TODO: 5 se prepise rychlosti dopravni situaci ulice, na ktere je vozidlo
             double part = 5 / lenght;

             this.position.setX(this.position.getX() + dx * part);
             this.position.setY(this.position.getY() + dy * part);

             for (Shape item: gui)
             {
                 item.setTranslateX(dx * part + item.getTranslateX());
                 item.setTranslateY(dy * part + item.getTranslateY());
             }
         }

        //System.out.println(nextpoint.getCoordinate().getX());
        //System.out.println(nextpoint.getCoordinate().getY());
    }
}
