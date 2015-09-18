package mule.model;

import java.awt.Point;

/**
 * Created by harrylane on 9/16/15.
 */
public class Map {
    private Plot[] plots;
    private static final int MAP_SIZE = 5;

    public Map() {
        plots = new Plot[MAP_SIZE];
    }

    public Plot getPlot(Point p) {
        return plots[(int) p.getX() * MAP_SIZE + (int) p.getY()];
    }
}
