import classes.Drawable;
import classes.Movable;
import javafx.fxml.FXML;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import jdk.vm.ci.meta.Local;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LayoutController {

    private List<Drawable> streets = new ArrayList<Drawable>();
    private List<Movable> vehicles = new ArrayList<>();
    private Timer timer;
    private LocalTime time = new Time(6,0,0).toLocalTime();
    private int rate = 1;

    @FXML
    private Pane map;

    @FXML
    private void onZoom(ScrollEvent event)
    {
        event.consume();
        if(event.getDeltaY() > 0 )
        {
            map.setScaleX(map.getScaleX()*1.25);
            map.setScaleY(map.getScaleY()*1.25);
        }

        else
        {
            map.setScaleX(map.getScaleX()*0.8);
            map.setScaleY(map.getScaleY()*0.8);
        }
    }

    public void setStreets(List<Drawable> streets2)
    {
        this.streets .add(streets2.get(0));
        for(Drawable item : streets2)
        {
            map.getChildren().addAll(item.getGui());
        }

    }

    public void setVehicles(List<Movable> vehicles)
    {
        //TODO: upravit na add pokud bysme vozidla prifavali ve for cklyu po jednom
        this.vehicles = vehicles;
        for(Movable item : this.vehicles)
        {
            map.getChildren().addAll(item.getGui());
        }
    }

    public void startTime()
    {
        timer = new Timer(false);
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                time = time.plusSeconds(rate);
                //System.out.println(time);

                //move all vehicles
                for (Movable item : vehicles)
                {
                    item.move(time, streets);
                    //System.out.println(item);
                }

            }
        },0,1000);
    }
}
