import classes.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/layout.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        Reader reader = Files.newBufferedReader(Paths.get("convertcsv.json"));
        Gson gson = new Gson();
        Data data = gson.fromJson(reader, Data.class);
        //data.getStreets().forEach(System.out::println);


        //inicialization of controller
        LayoutController controller = loader.getController();
        controller.init();

        //Street list imported form json file
        ArrayList<Street> ourStreets = data.getStreets();

        //loading lines
        //TODO: tady by asi bylo idelani kdyby to vracelo list linek
        Reader line1Reader = Files.newBufferedReader(Paths.get("line1.json"));
        Line line1 = gson.fromJson(line1Reader, Line.class);



        /**
        for (Point item : line1.getPoints())
        {
            item.print();
        }
         */

        //adding vehicles to scene
        controller.setVehicles(Arrays.asList(new Vehicle(new Coordinate(71.30280079586885,278.6122408888676),"200",line1)));

        //adding streets to scene
        for (Drawable item : ourStreets)
        {
            controller.setStreets(Arrays.asList(item));
        }

        //Starting timer
        controller.addToList("a");
        controller.addToList("b");
        controller.addToList("c");






        /*
        Reader pointReader = Files.newBufferedReader(Paths.get("out.json"));
        Point point = gson.fromJson(pointReader, Point.class);
        point.print();
        */

        //System.out.println(data.getStreets());

        /*
        Reader line1Reader = Files.newBufferedReader(Paths.get("line1.json"));
        Line line1 = gson.fromJson(line1Reader, Line.class);

        for (Point item : line1.getPoints())
        {
            item.print();
        }
        */
        /*
        line1.setId("line1");
        line1.addVehicle("10");
        line1.addVehicle("11");
        line1.addVehicle("12");
        line1.addVehicle("13");
        line1.addVehicle("14");
        line1.addVehicle("15");
        line1.addVehicle("16");
        line1.addVehicle("17");
        line1.addVehicle("18");
        line1.addVehicle("19");


        //Point point = new Point(new Coordinate(12, 41), true, "Random", Time.valueOf("06:04:12"));
        Gson bson = new GsonBuilder().setPrettyPrinting().create();
        String pointS = bson.toJson(line1);

        try (PrintWriter out = new PrintWriter("linecka1.out"))
        {
            out.println(pointS);
        }
        */

    }
}
