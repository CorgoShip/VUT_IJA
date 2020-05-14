import classes.Drawable;
import classes.Line;
import classes.Movable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import jdk.vm.ci.meta.Local;

import javafx.scene.control.ListView;
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
    private List<Movable> toRemove = new ArrayList<>();

    @FXML
    private ListView lineList;

    @FXML
    private TextField speed;

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

    @FXML
    private void onSpeedChange()
    {
        try {
            double speed_d = Double.parseDouble(speed.getText());
            timer.cancel();
            startTime(speed_d);
        } catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Zadejte cislo v rozsahu 0 - 1000");
            alert.showAndWait();
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
        //TODO: upravit na add pokud bysme vozidla prifavali ve for cyklu po jednom
        this.vehicles = vehicles;
        for(Movable item : this.vehicles)
        {
            map.getChildren().addAll(item.getGui());
        }
    }

    public void startTime(double speed)
    {
        timer = new Timer(false);
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                time = time.plusSeconds(rate);
                //System.out.println(time);

                //mark  mariking selected lines
                ObservableList selectedIndices = lineList.getSelectionModel().getSelectedItems();
                for(Object item : selectedIndices){
                    System.out.println(item);
                }


                //on time actions
                for (Movable item : vehicles)
                {
                    //update vehicle position
                    if(item.move(time, streets) == false)
                    {
                        map.getChildren().remove(this);
                        vehicles.remove(this);
                    }
                    //System.out.println(item);
                }

            }
        },0, (long) (1000 / speed));
    }

    public  void addToList(String line)
    {
        lineList.getItems().add(line);
    }


    /**
    public  void addToList(Line line)
    {
        lineList.getItems().add(line.getId());
    }
     */

    public void init()
    {
        this.startTime(1);
        lineList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
