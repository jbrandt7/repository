package mule.model.map;

public class Location {

    private int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)  {
            return false;
        } else if (this == o) {
            return true;
        }else if (o instanceof Location) {
            Location that = (Location) o;
            return that.x == this.x && that.y == this.y;
        }
        return false;
    }

    public int hashCode() {
        return 31 * x + y;
    }

    public String toString() {
        return "Location at: " + x + ", " + y;
    }

}
