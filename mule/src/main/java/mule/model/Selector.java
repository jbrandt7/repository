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

    public void move(Direction d) {
        switch (d) {
            case UP:
                if (p.getY() > 0) {
                    p.setLocation(p.getX(), p.getY() - 75);
                }
                break;
            case RIGHT:
                if (p.getX() < 525) {
                    p.setLocation(p.getX() + 75, p.getY());
                }
                break;
            case DOWN:
                if (p.getY() < 525) {
                    p.setLocation(p.getX(), p.getY() + 75);
                }
                break;
            case LEFT:
                if (p.getX() > 0) {
                    p.setLocation(p.getX() - 75, p.getY());
                }
                break;
            default:
                break;
        }
    }
}