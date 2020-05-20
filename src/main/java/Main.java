/**
 * Autori: Zbynek Lamacka xlamac01
 *         Simon  Pomykal xpomyk04
 *
 * Trida se stara o spusteni aplikace a nacteni dat
 */

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

        Reader reader = Files.newBufferedReader(Paths.get("data/convertcsv.json"));
        Gson gson = new GsonBuilder().setDateFormat(0).create();
        Data data = gson.fromJson(reader, Data.class);
        //data.getStreets().forEach(System.out::println);


        //inicialization of controller
        LayoutController controller = loader.getController();

        //Street list imported form json file
        ArrayList<Street> ourStreets = data.getStreets();

        //loading lines
        //TODO: tady by asi bylo idelani kdyby to vracelo list linek
        Reader line1Reader = Files.newBufferedReader(Paths.get("data/line1.json"));
        Line line1 = gson.fromJson(line1Reader, Line.class);
        Reader line2Reader = Files.newBufferedReader(Paths.get("data/line2.json"));
        Line line2 = gson.fromJson(line2Reader, Line.class);
        Reader line3Reader = Files.newBufferedReader(Paths.get("data/line3.json"));
        Line line3 = gson.fromJson(line3Reader, Line.class);

        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);

        controller.setLines(lines);


        //adding streets to scene
        for (Drawable item : ourStreets)
        {
            controller.setStreets(Arrays.asList(item));
        }

        controller.init();
    }
}

