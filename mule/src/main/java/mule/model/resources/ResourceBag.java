package mule.model.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResourceBag implements java.io.Serializable {

    private final Map<Resource, Integer> bag;

    public ResourceBag() {
        bag = new HashMap<>();
        bag.put(new Energy(), 0);
        bag.put(new Food(), 0);
        bag.put(new Smithore(), 0);
        bag.put(new Crystite(), 0);
    }

    public final void add(Resource type, int amount) {
        bag.put(type, bag.get(type) + amount);
    }

    public final void remove(Resource type, int amount) {
        int currentAmount = bag.get(type);
        if (currentAmount - amount < 0) {
            bag.put(type, 0);
        } else {
            bag.put(type, bag.get(type) - amount);
        }
    }

    public final int get(Resource type) {
        return bag.get(type);
    }

    public final int getTotalCost() {
        int result = 0;
        for (Resource r : bag.keySet()) {
            result += (r.getCost() * bag.get(r));
        }
        return result;
    }

    public final Set getResources() {
        return bag.keySet();
    }

}
