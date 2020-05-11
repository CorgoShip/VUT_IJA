import classes.Drawable;
import javafx.fxml.FXML;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class LayoutController {

    private List<Drawable> items = new ArrayList<Drawable>();



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

    public void setItems(List<Drawable> items)
    {
        this.items = items;
        for(Drawable item : items)
        {
            map.getChildren().addAll(item.getGui());
        }
    }
}
