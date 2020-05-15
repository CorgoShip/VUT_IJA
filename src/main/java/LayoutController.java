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
    private LocalTime time = new Time(6, 0, 1).toLocalTime();
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

                addVehicle(getInitialPosition(line,count,vehicleID));
                count++;
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

                Platform.runLater(() -> {
                    //on time actions
                    for (Movable item : vehicles) {
                        //update vehicle position

                        if (item.move(time, streets) == false) {
                            map.getChildren().remove(item);
                            //vehicles.remove(item);
                        }
                        //System.out.println(item);
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

    public synchronized Vehicle getInitialPosition(Line line,int count,String id)
    {
        Coordinate vehiclePosition = new Coordinate(0,0);
        Line vehicleLine = new Line(line);

        Point prev = line.getPoints().get(0);
        LocalTime prevTime = LocalTime.parse(line.getPoints().get(0).getCasOdjezdu()).plusMinutes(count);
        for (Point point : line.getPoints())
        {

            LocalTime pointTime = LocalTime.parse(point.getCasOdjezdu());
            pointTime = pointTime.plusMinutes(count);

            if(time.compareTo(pointTime) == 0 )
            {
                vehiclePosition.setX(point.getCoordinate().getX());
                vehiclePosition.setY(point.getCoordinate().getY());
                break;
            }

            if(time.compareTo(pointTime) < 0  )
            {
                 double timeDifference = pointTime.getHour()*3600 + pointTime.getMinute()*60 + pointTime.getSecond() - prevTime.getHour()*3600 - prevTime.getMinute()*60 - prevTime.getSecond();

                 double timeTraveled = time.getHour()*3600 + time.getMinute()*60 + time.getSecond() - prevTime.getHour()*3600 - prevTime.getMinute()*60 - prevTime.getSecond();

                 double dx = point.getCoordinate().getX() - prev.getCoordinate().getX();
                 double dy = point.getCoordinate().getY() - prev.getCoordinate().getY();
                 double part = timeTraveled/timeDifference;

                 vehiclePosition.setX(prev.getCoordinate().getX() + dx*part);
                 vehiclePosition.setY(prev.getCoordinate().getY()+dy*part);
                 break;
            }
            prev = point;
            prevTime = pointTime;
            //TODO remove point from line
            vehicleLine.getPoints().remove(0);
        }

        return  new Vehicle(vehiclePosition,id,vehicleLine,count);
    }
}
