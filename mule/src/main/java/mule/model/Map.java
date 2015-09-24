package mule.model;

import java.awt.Point;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Created by harrylane on 9/16/15.
 */
public class Map {

    private Group parent;
    private Plot[][] plots;
    private static final int MAP_WIDTH = 9;
    private static final int MAP_HEIGHT = 5;

    public Map(Group parent) {
        this.parent = parent;
        plots = new Plot[MAP_WIDTH][MAP_HEIGHT];

        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[0].length; j++) {
                Rectangle temp = new Rectangle(75 * i, 75 * j, 75, 75);
                if (isCenter(i, j)) {
                    plots[i][j] = new TownPlot(temp);
                } else if (isMiddle(i, j)) {
                    plots[i][j] = new RiverPlot(temp);
                    plots[i][j].getRep().setFill(Color.BLUE);
                } else if (isCorner(i, j)) {
                    plots[i][j] = new MountainPlot(temp);
                    plots[i][j].getRep().setFill(Color.GREY);
                } else {
                    plots[i][j] = new PlainPlot(temp);
                    plots[i][j].getRep().setFill(Color.GREEN);
                }
                parent.getChildren().addAll(plots[i][j].getRep());
            }
        }

    }

    public Plot getPlot(int x, int y) {
        return plots[x][y];
    }

    private boolean isCorner(int x, int y) {
        return ((x % MAP_WIDTH == MAP_WIDTH / 4 && y % MAP_HEIGHT == MAP_HEIGHT / 4)
                || (x % MAP_WIDTH == MAP_WIDTH * 3 / 4 && y % MAP_HEIGHT == MAP_HEIGHT / 4)
                || (x % MAP_WIDTH == MAP_WIDTH / 4 && y % MAP_HEIGHT == MAP_HEIGHT * 3 / 4)
                || (x % MAP_WIDTH == MAP_WIDTH * 3 / 4 && y % MAP_HEIGHT == MAP_HEIGHT * 3 / 4));
    }

    private boolean isCenter(int x, int y) {
        return (x == MAP_WIDTH / 2) && (y == MAP_HEIGHT / 2);
    }

    private boolean isMiddle(int x, int y) {
        return x % MAP_WIDTH == MAP_WIDTH / 2;
    }
}
