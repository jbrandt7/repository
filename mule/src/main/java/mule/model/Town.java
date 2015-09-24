package mule.model;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Town {

    private Group parent;

    private Store[][] stores;

    private final static int TOWN_WIDTH = 2;
    private final static int TOWN_HEIGHT = 3;

    public Town(Group parent) {
        this.parent = parent;
        stores = new Store[TOWN_WIDTH][TOWN_HEIGHT];
    }
}
