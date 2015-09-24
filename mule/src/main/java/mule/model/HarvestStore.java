package mule.model;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class HarvestStore extends Store {

    public HarvestStore(Rectangle rep) {
        super(rep);
        rep.setFill(Color.GREEN);
    }

}
