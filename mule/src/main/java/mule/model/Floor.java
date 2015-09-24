package mule.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Floor extends Store {

    public Floor(Rectangle rep) {
        super(rep);
        rep.setFill(Color.GREY);
    }

}
