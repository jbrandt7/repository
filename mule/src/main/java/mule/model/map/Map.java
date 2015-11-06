package mule.model.map;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Holder class for all the plots used throughout the game
 */
public class Map implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    private transient Canvas parent;
    private Plot[][] plots;
    public static final int MAP_WIDTH = 9;
    public static final int MAP_HEIGHT = 5;
    @SuppressWarnings("WeakerAccess")
    private static final int PLOT_SIZE = 75;

    /**
     * Sets up an initial map of 9 X 5 with rivers, plains, mountains, and a town
     * @param canvas The canvas on which plots are drawn onto
     */
    public Map(Canvas canvas) {
        this.parent = canvas;
        plots = new Plot[MAP_WIDTH][MAP_HEIGHT];

        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[0].length; j++) {
                Image image;

                if (isCenter(i, j)) {
                    plots[i][j] = new TownPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/town.jpg", false);
                } else if (isMiddle(i)) {
                    plots[i][j] = new RiverPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/river.jpg", false);
                } else if (isCorner(i, j)) {
                    plots[i][j] = new MountainPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/mountain.jpg", false);
                } else {
                    plots[i][j] = new PlainPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/plain.jpg", false);
                }
                parent.getGraphicsContext2D().drawImage(image, PLOT_SIZE * i, PLOT_SIZE * j);
            }
        }
        parent.getGraphicsContext2D().setGlobalAlpha(1.0 / 2);
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

    private boolean isCorner(int x, int y) {
        return ((x % MAP_WIDTH == MAP_WIDTH / 4 && y % MAP_HEIGHT == MAP_HEIGHT / 4)
                || (x % MAP_WIDTH == MAP_WIDTH * 3 / 4 && y % MAP_HEIGHT == MAP_HEIGHT / 4)
                || (x % MAP_WIDTH == MAP_WIDTH / 4 && y % MAP_HEIGHT == MAP_HEIGHT * 3 / 4)
                || (x % MAP_WIDTH == MAP_WIDTH * 3 / 4 && y % MAP_HEIGHT == MAP_HEIGHT * 3 / 4));
    }

    private boolean isCenter(int x, int y) {
        return (x == MAP_WIDTH / 2) && (y == MAP_HEIGHT / 2);
    }

    private boolean isMiddle(int x) {
        return x % MAP_WIDTH == MAP_WIDTH / 2;
    }

}
