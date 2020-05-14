import classes.*;
import javafx.application.Platform;
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
    private List<Line> lines = new ArrayList<>();
    private List<Movable> vehicles = new ArrayList<>();
    private Timer timer;
    private LocalTime time = new Time(6, 15, 0).toLocalTime();
    private int rate = 1;
    private List<Movable> toRemove = new ArrayList<>();

    @FXML
    private ListView<String> vehicleList;

    @FXML
    private ListView<String> lineList;

    @FXML
    private TextField speed;

    @FXML
    private Pane map;

    @FXML
    private void onZoom(ScrollEvent event) {
        event.consume();
        if (event.getDeltaY() > 0) {
            map.setScaleX(map.getScaleX() * 1.25);
            map.setScaleY(map.getScaleY() * 1.25);
        } else {
            map.setScaleX(map.getScaleX() * 0.8);
            map.setScaleY(map.getScaleY() * 0.8);
        }
    }

    @FXML
    private void onSpeedChange() {
        try {
            double speed_d = Double.parseDouble(speed.getText());
            timer.cancel();
            startTime(speed_d);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Zadejte cislo v rozsahu 0 - 1000");
            alert.showAndWait();
        }
    }

    @FXML
    private void onMouseclicked() {
        vehicleList.getItems().clear();
        //viewlists
        String lineId = lineList.getSelectionModel().getSelectedItem();
        for (Line line : lines) {
            if (line.getId() == lineList.getSelectionModel().getSelectedItem()) {
                //TODO:zvyraznit ulice linky
                for (String vehicle : line.getVehicles()) {
                    vehicleList.getItems().add(vehicle);
                }
            }
        }
    }


    public void setStreets(List<Drawable> streets2) {
        this.streets.add(streets2.get(0));
        for (Drawable item : streets2) {
            map.getChildren().addAll(item.getGui());
        }

    }

    public void setLines(List<Line> lines) {
        this.lines = lines;

        for (Line line : lines) {
            addToList(line.getId());

            int count = 0;
            for (String vehicleID : line.getVehicles()) {
                //TODO:nastavit souradnice podle casu na spravnou pocatecni pozici
                //System.out.println(getInitialPosition(line).getX());
                //System.out.println(getInitialPosition(line).getY());
                addVehicle(new Vehicle(getInitialPosition(line), vehicleID, line));
            }
        }
    }

    public void addVehicle(Movable vehicle) {
        this.vehicles.add(vehicle);
        map.getChildren().addAll(vehicle.getGui());
    }

    public void startTime(double speed) {
        timer = new Timer(false);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time = time.plusSeconds(rate);
                //System.out.println(time);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //on time actions
                        for (Movable item : vehicles) {
                            //update vehicle position
                            if (item.move(time, streets) == false) {
                                map.getChildren().remove(this);
                                vehicles.remove(this);
                            }
                            //System.out.println(item);
                        }
                    }
                });
            }
        }, 0, (long) (1000 / speed));
    }

    public void addToList(String line) {
        lineList.getItems().add(line);
    }


    /**
     * public  void addToList(Line line)
     * {
     * lineList.getItems().add(line.getId());
     * }
     */

    public void init() {
        this.startTime(1);
    }

    public Coordinate getInitialPosition(Line line)
    {
        Point prev = null;
        LocalTime prevTime = null;
        for (Point point : line.getPoints())
        {
            System.out.println(point.getCasOdjezdu());
            /**
            LocalTime pointTime = LocalTime.parse(point.getCasOdjezdu());

            if(time.compareTo(pointTime) == 0 )
            {
                System.out.println(1);
                return new Coordinate(point.getCoordinate().getX(),point.getCoordinate().getY());
            }

            if(time.compareTo(pointTime) < 0  )
            {
                System.out.println(prev);
                System.out.println(prevTime);
                if (prevTime == null)
                {
                    System.out.println(2);
                    return new Coordinate(point.getCoordinate().getX(),point.getCoordinate().getY());
                }
                prev = point;
                prevTime = pointTime;
                /**
                double timeDifference = pointTime.getHour()*3600 + pointTime.getMinute()*60 + pointTime.getSecond() - prevTime.getHour()*3600 - prevTime.getMinute()*60 - prevTime.getSecond();

                double timeTraveled = time.getHour()*3600 + time.getMinute()*60 + time.getSecond() - prevTime.getHour()*3600 - prevTime.getMinute()*60 - prevTime.getSecond();

                double dx = point.getCoordinate().getX() - prev.getCoordinate().getX();
                double dy = point.getCoordinate().getY() - prev.getCoordinate().getY();
                double part = timeTraveled/timeDifference;

                return new Coordinate(prev.getCoordinate().getX() + dx*part,prev.getCoordinate().getY()+dy*part);
<<<<<<< HEAD

            }
            */
=======
                 */
            }
>>>>>>> 7119b71290726bdbb174f7b19b76dbddfd248f6e
            //prev = point;
            //prevTime = pointTime;
        }
        //return line.getPoints().get(0).getCoordinate();
        return  new Coordinate(1,2);
    }
}
