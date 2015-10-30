package mule.model.town;

import mule.model.*;
import mule.model.resources.*;
import mule.model.player.*;
import java.util.HashMap;
import javafx.scene.shape.Rectangle;
/**
 * Created by harrylane on 9/16/15.
 */
public class Store implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    protected transient Rectangle rep;
    protected Resource resource;
    private HashMap<Resource, Integer> inventory;

    public Store(Rectangle rep) {
        this.rep = rep;
        inventory = new HashMap<Resource, Integer>();
        inventory.put(new Food(), 16);
        inventory.put(new Energy(), 16);
        inventory.put(new Smithore(), 0);
        inventory.put(new Crystite(), 0);
        inventory.put(new Mule(), 25);
    }

    public Rectangle getRep() {
        return rep;
    }

    public boolean buyResource(Player p, Resource r) {
   	    if (p.getMoney() < r.getCost()) {
            return false;
        }
        inventory.put(r, inventory.get(r) - 1);
        p.addResource(r, 1);
        p.removeMoney(r.getCost());
        return true;
    }

    public boolean sellResource(Player p, Resource r) {
        if (p.getBag().get(r) == 0) {
            return false;
        }
        inventory.put(r, inventory.get(r) + 1);
        p.removeResource(r, 1);
        p.addMoney(r.getCost());
        return true;
    }

    public boolean buyMule(Player p, Resource type) {
        Mule toAdd = new Mule(type);
        if (p.getMoney() < (toAdd.getCost())) {
            return false;
        }
        System.out.println(inventory.get(new Mule()));
        inventory.put(type, inventory.get(new Mule()) - 1);
        p.addMule(toAdd);
        p.removeMoney(toAdd.getCost());
        return true;
    }

}
