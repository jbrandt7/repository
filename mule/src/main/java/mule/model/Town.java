package mule.model;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Group;

/**
* Created by Ryan Chiang 9/22/15
*/

public class Town {

    private Group parent;
    private Store[][] stores;
    private static final int MAP_SIZE = 5;

    public Town(Group parent) {
        this.parent = parent;
        stores = new Store[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < stores.length; i++) {
            for (int j = 0; j < stores[0].length; j++) {
                    if (i == 0 && j == 0) {
                        stores[i][j] = new HarvestStore(new Rectangle(75 * i,
                                75 * j, 75, 75));
                        stores[i][j].getRep().setFill(Color.BLUE);
                    } else if (i == 0 && j == 5) {
                        stores[i][j] = new MuleStore(new Rectangle(75 * i,
                                75 * j, 75, 75));
                        stores[i][j].getRep().setFill(Color.GREEN);
                    } else if (i == 5 && j == 0) {
                         stores[i][j] = new AssayStore(new Rectangle(75 * i,
                            75 * j, 75, 75));
                        stores[i][j].getRep().setFill(Color.RED);
                    } else if (i ==5 && j== 5) {
                        stores[i][j] = new Pub(new Rectangle(75 * i,
                            75 * j, 75, 75));
                        stores[i][j].getRep().setFill(Color.BROWN);
                    } else {
                        stores[i][j] = new Store(new Rectangle(75 * i,
                                75 * j, 75, 75));
                        stores[i][j].getRep().setFill(Color.GREY);
                    }
                parent.getChildren().addAll(stores[i][j].getRep());
                }
            }
    }

}