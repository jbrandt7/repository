package mule.model;

/**
 * Created by The Boat on 9/22/2015.
 */
public class Resource {
    private int type, value;
    public Resource(int type, int value) {
        this.type = type;
        this.value = value;
    }
    public int getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
}
