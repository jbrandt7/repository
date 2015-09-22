package mule.model;

import java.awt.*;

/**
 * Created by The Boat on 9/22/2015.
 */
public class Selector {
    private Point p;

    public Selector(Point p) {
        this.p = p;
    }

    public Point getPoint() {
        return p;
    }

    public void setPoint(Point p) {
        this.p = p;
    }
}