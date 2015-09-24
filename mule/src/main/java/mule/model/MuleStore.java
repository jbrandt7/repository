package mule.model;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class MuleStore extends Store {

    public MuleStore(Rectangle rep) {
        super(rep);
        rep.setFill(Color.YELLOW);
    }

}
