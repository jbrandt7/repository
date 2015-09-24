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

        stores[0][0] = new AssayStore(new Rectangle(0, 0, 337.5, 125));

        stores[0][1] = new Floor(new Rectangle(0, 125, 337.5, 125));

        stores[0][2] = new MuleStore(new Rectangle(0, 250, 337.5, 125));
        stores[0][2].getRep().setFill(Color.GREEN);

        stores[1][0] = new HarvestStore(new Rectangle(337.5, 0, 337.5, 125));
        stores[1][0].getRep().setFill(Color.YELLOW);

        stores[1][1] = new Floor(new Rectangle(337.5, 125, 337.5, 125));

        stores[1][2] = new Pub(new Rectangle(337.5, 250, 337.5, 125));
        stores[1][2].getRep().setFill(Color.RED);

        for (int i = 0; i < TOWN_WIDTH; i++) {
            for (int j = 0; j < TOWN_HEIGHT; j++) {
                parent.getChildren().addAll(stores[i][j].getRep());
            }
        }
    }

    public Store getStore(int x, int y) {
        return stores[x][y];
    }

}
