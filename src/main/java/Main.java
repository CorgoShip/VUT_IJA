import classes.*;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        data.getStreets().forEach(System.out::println);

        //Street list imported form json file
        ArrayList<Street> ourStreets = data.getStreets();

        //adding vehicles to scene
        LayoutController controller = loader.getController();
        controller.setVehicles(Arrays.asList(new Vehicle(new Coordinate(200,200),"200"),new Vehicle(new Coordinate(100,100),"100")));

        //adding streets to scene
        for (Drawable item : ourStreets)
        {
            controller.setStreets(Arrays.asList(item));
        }

        //Starting timer
        controller.startTime();

        //System.out.println(data.getStreets());

    }
}
