package mule.model;

/**
 * Created by The Boat on 9/22/2015.
 */
public class Resource {
    private int value;
    private String name;
    public Resource(int value, String name) {
        this.value = value;
	this.name = name;
    }

    public int getCost() {
        return value;
    }
    public String getName() {
    	return name;
    }
}
