package classes;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Movable {
    private Coordinate position;
    private String id;
    private transient List<Shape> gui;
    private Line line;
    private Line currentLine;
    private double speed = 1;
    private int timeoffset;
    private List<Drawable> streets = new ArrayList<Drawable>();
    private boolean indicated = true;
    private String StreetId;

    public int getTimeoffset() {
        return timeoffset;
    }

    public Line getLine() {
        return line;
    }

    public Coordinate getPosition() {
        return position;
    }

    @Override
    public String getid() {
        return this.id;
    }

    public Vehicle(Coordinate p, String id, Line line, Line cline, int to,List<Drawable> streets) {
        this.timeoffset = to;
        this.position = p;
        this.id = id;
        this.streets = streets;
        gui = new ArrayList<Shape>();

        Circle symbol = new Circle(position.getX(), position.getY(), 5, Color.rgb(line.hashCode()%255,(line.getPoints().size()*30)%255,line.getId().hashCode()%255));



        //set on mouse clicked method
        symbol.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                indicated = !indicated;
                for (Drawable street : streets) {
                    for (Point point : line.getPoints())
                    {
                        if (street.getName().equals(point.getStreetId()))
                        {
                            //System.out.println(street.getName());

                            if(indicated) {
                                for (Shape shape : street.getSymbols())
                                {
                                    shape.setStroke(Color.GRAY);
                                    shape.setStrokeWidth(1);
                                }
                            }
                            else {
                                for (Shape shape : street.getSymbols())
                                {
                                        shape.setStroke( Color.rgb(line.hashCode()%255,(line.getPoints().size()*30)%255,line.getId().hashCode()%255));
                                        shape.setStrokeWidth(2);
                                }

                            }
                        }
                    }
                }
                mouseEvent.consume();
            }
        });


        gui.add(symbol);
        gui.add(new Text(position.getX() + 4, position.getY() + 3, id));
        this.line = line;
        this.currentLine = cline;
    }

    @Override
    public List<Shape> getGui() {
        return this.gui;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean move(LocalTime time,List<Drawable> streets) {
        //ziskam bod ke kteremu mam prave jet
        if(currentLine.getPoints().isEmpty())
        {
            //this.position.setX(0);
            //this.position.setY(0);
            return false;
        }

        Point nextpoint = currentLine.getPoints().get(0);
        this.StreetId = currentLine.getPoints().get(0).getStreetId();

        //zjistim na ktere jsem ulici
        Drawable street = this.getStreet(streets);
        double traffic = street.getTraffic();

        //vypocitam vzdalenost na x a y ose a velikost primky mezi vozidlem a bodem
        double dx = nextpoint.getCoordinate().getX() - this.position.getX();
        double dy = nextpoint.getCoordinate().getY() - this.position.getY();
        double lenght = Math.sqrt(dx*dx + dy*dy);


        //vzdalenost od bodu je mensi nez rychlost za jednotku casu
        if(lenght <= (speed - speed*traffic*0.01))
        {
            if(nextpoint.getZastavka())
            {
                LocalTime stoptime = LocalTime.parse(line.getPoints().get(0).getCasOdjezdu()).plusMinutes(timeoffset);
                if(time.compareTo(stoptime) < 0)
                {
                   //System.out.println(time + " <->" +stoptime);
                    return true;
                }

            }


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
            double part = ((speed - speed*traffic*0.01) / lenght);

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
        return true;
    }

    private Drawable getStreet(List<Drawable> streets)
    {
        for (Drawable item : streets)
        {
            //System.out.println("item.getName() " + item.getName());
            //System.out.println("this.StreetId: " + this.StreetId);

            if (item.getName().equals(this.StreetId))
            {
                //System.out.println("su na: " + item.getName());
                //System.out.println("su na: " + this.StreetId);
                return item;
            }
        }
        //System.out.println("su na: null");
        return null;
    }

}

