package mule.model.map;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Created by harrylane on 9/16/15.
 */
public class Map {

    private Canvas parent;
    private Plot[][] plots;
    public static final int MAP_WIDTH = 9;
    public static final int MAP_HEIGHT = 5;

    public Map(Canvas parent) {
        this.parent = parent;
        plots = new Plot[MAP_WIDTH][MAP_HEIGHT];

        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[0].length; j++) {
                Image image;

                if (isCenter(i, j)) {
                    plots[i][j] = new TownPlot(parent, i * 75, j * 75);
                    image = new Image("mule/view/town.jpg", false);
                } else if (isMiddle(i, j)) {
                    plots[i][j] = new RiverPlot(parent, i * 75, j * 75);
                    image = new Image("mule/view/river.jpg", false);
                } else if (isCorner(i, j)) {
                    plots[i][j] = new MountainPlot(parent, i * 75, j * 75);
                    image = new Image("mule/view/mountain.jpg", false);
                } else {
                    plots[i][j] = new PlainPlot(parent, i * 75, j * 75);
                    image = new Image("mule/view/plain.jpg", false);
                }
                parent.getGraphicsContext2D().drawImage(image, 75 * i, 75 * j);
            }
        }
        parent.getGraphicsContext2D().setGlobalAlpha(.5);
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
