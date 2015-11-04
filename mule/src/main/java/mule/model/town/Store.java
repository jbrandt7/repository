package mule.model.town;

import mule.model.resources.*;
import mule.model.player.Player;
import java.util.HashMap;
import javafx.scene.shape.Rectangle;

public class Store implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    private static final int STARTINGFOOD = 16;
    private static final int STARTINGENERGY = 16;
    private static final int STARTINGMULES = 25;

    protected Resource resource;
    private HashMap<Resource, Integer> inventory;

    public Store() {
        inventory = new HashMap<>();
        inventory.put(new Food(), STARTINGFOOD);
        inventory.put(new Energy(), STARTINGENERGY);
        inventory.put(new Smithore(), 0);
        inventory.put(new Crystite(), 0);
        inventory.put(new Mule(), STARTINGMULES);
    }

    public final boolean buyResource(Player p, Resource r) {
   	    if (p.getMoney() < r.getCost()) {
            return false;
        }
        inventory.put(r, inventory.get(r) - 1);
        p.addResource(r, 1);
        p.removeMoney(r.getCost());
        return true;
    }

    public final boolean sellResource(Player p, Resource r) {
        if (p.getBag().get(r) == 0) {
            return false;
        }
        inventory.put(r, inventory.get(r) + 1);
        p.removeResource(r, 1);
        p.addMoney(r.getCost());
        return true;
    }

    public final boolean buyMule(Player p, Resource type) {
        Mule toAdd = new Mule(type);
        if (p.getMoney() < (toAdd.getCost())) {
            return false;
        }
        inventory.put(type, inventory.get(new Mule()) - 1);
        p.addMule(toAdd);
        p.removeMoney(toAdd.getCost());
        return true;
    }

}
