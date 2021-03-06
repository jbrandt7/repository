package mule.model.map;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Holder class for all the plots used throughout the game
 */
public class Map implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    transient Canvas parent;
    Plot[][] plots;
    public static final int MAP_WIDTH = 9;
    public static final int MAP_HEIGHT = 5;
    @SuppressWarnings("WeakerAccess")
    static final int PLOT_SIZE = 75;

    /**
     * Sets up an initial map of 9 X 5 with rivers, plains, mountains, and a town
     * @param canvas The canvas on which plots are drawn onto
     */
    public Map(Canvas canvas) {
        this.parent = canvas;
        plots = new Plot[MAP_WIDTH][MAP_HEIGHT];
    }

    /**
     * @param x the x coordinate of the plot
     * @param y the y coordinate of the plot
     * @return the plot at (x, y)
     */
    public final Plot getPlot(int x, int y) {
        return plots[x][y];
    }

    /**
     * Redraws all plots when a saved game is loaded
     * @param rep The canvas to draw onto
     */
    public final void redraw(Canvas rep) {
        parent = rep;

        for (Plot[] row : plots) {
            for (Plot p : row) {
                p.setRep(rep);
                p.redraw();
            }
        }

        parent.getGraphicsContext2D().setGlobalAlpha(1.0 / 2);

    }

    boolean isCorner(int x, int y) {
        return isUpperLeft(x, y) || isUpperRight(x, y) || isLowerRight(x, y) || isLowerLeft(x, y);
    }

    boolean isUpperLeft(int x, int y) {
        return x % MAP_WIDTH == MAP_WIDTH / 4 && y % MAP_HEIGHT == MAP_HEIGHT / 4;
    }

    boolean isUpperRight(int x, int y) {
        return x % MAP_WIDTH == MAP_WIDTH * 3 / 4 && y % MAP_HEIGHT == MAP_HEIGHT / 4;
    }

    boolean isLowerRight(int x, int y) {
        return x % MAP_WIDTH == MAP_WIDTH * 3 / 4 && y % MAP_HEIGHT == MAP_HEIGHT * 3 / 4;
    }

    boolean isLowerLeft(int x, int y) {
        return x % MAP_WIDTH == MAP_WIDTH / 4 && y % MAP_HEIGHT == MAP_HEIGHT * 3 / 4;
    }

    boolean isCenter(int x, int y) {
        return (x == MAP_WIDTH / 2) && (y == MAP_HEIGHT / 2);
    }

    boolean isMiddle(int x) {
        return x % MAP_WIDTH == MAP_WIDTH / 2;
    }

}
