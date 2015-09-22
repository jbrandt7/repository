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
    private static final int MAP_SIZE = 7;

    public Map(Group parent) {
        this.parent = parent;
        plots = new Plot[MAP_SIZE][MAP_SIZE];

        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[0].length; j++) {
                if (isCenter(i, j)) {
                    plots[i][j] = new TownPlot(new Rectangle(75 * i,
                                75 * j, 75, 75));
                } else if (isMiddle(i, j)) {
                    plots[i][j] = new RiverPlot(new Rectangle(75 * i,
                                75 * j, 75, 75));
                    plots[i][j].getRep().setFill(Color.BLUE);
                } else if (isCorner(i, j)) {
                    plots[i][j] = new MountainPlot(new Rectangle(75 * i,
                                75 * j, 75, 75));
                    plots[i][j].getRep().setFill(Color.GREY);
                } else {
                    plots[i][j] = new PlainPlot(new Rectangle(75 * i,
                                75 * j, 75, 75));
                    plots[i][j].getRep().setFill(Color.GREEN);
                }
                parent.getChildren().addAll(plots[i][j].getRep());
            }
        }

    }

    public Plot getPlot(Point p) {
        return plots[(int) p.getX()][(int) p.getY()];
    }

    private boolean isCorner(int x, int y) {
        return ((x % MAP_SIZE == MAP_SIZE / 4 && y % MAP_SIZE == MAP_SIZE / 4)
                || (x % MAP_SIZE == MAP_SIZE * 3 / 4 && y % MAP_SIZE == MAP_SIZE / 4)
                || (x % MAP_SIZE == MAP_SIZE / 4 && y % MAP_SIZE == MAP_SIZE * 3 / 4)
                || (x % MAP_SIZE == MAP_SIZE * 3 / 4 && y % MAP_SIZE == MAP_SIZE * 3 / 4));
    }

    private boolean isCenter(int x, int y) {
        return (x == MAP_SIZE / 2) && (y == MAP_SIZE / 2);
    }

    private boolean isMiddle(int x, int y) {
        return x % MAP_SIZE == MAP_SIZE / 2;
    }
}
