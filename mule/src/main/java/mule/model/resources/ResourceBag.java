package mule.model.resources;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by The Boat on 9/22/2015.
 */
public class ResourceBag implements java.io.Serializable {

    private HashMap<Resource, Integer> bag;

    public ResourceBag() {
        bag = new HashMap<Resource, Integer>();
        bag.put(new Energy(), 0);
        bag.put(new Food(), 0);
        bag.put(new Smithore(), 0);
        bag.put(new Crystite(), 0);
    }

    public void add(Resource type, int amount) {
        bag.put(type, bag.get(type) + amount);
    }

    public void remove(Resource type, int amount) {
        int currentAmount = bag.get(type);
        if (currentAmount - amount < 0) {
            bag.put(type, 0);
        } else {
            bag.put(type, bag.get(type) - amount);
        }
    }

    public int get(Resource type) {
        return bag.get(type);
    }

    public int getTotalCost() {
        int result = 0;
        for (Resource r : bag.keySet()) {
            result += (r.getCost() * bag.get(r));
        }
        return result;
    }

    public Set getResources() {
        return bag.keySet();
    }

}
