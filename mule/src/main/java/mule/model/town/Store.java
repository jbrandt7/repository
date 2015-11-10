package mule.model.town;

import mule.model.resources.*;
import mule.model.player.Player;
import java.util.HashMap;
import java.util.Map;

public class Store implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    private static final int STARTINGFOOD = 16;
    private static final int STARTINGENERGY = 16;
    private static final int STARTINGMULES = 25;

    private Map<Resource, Integer> inventory;

    /**
     * Creates a new store with a starting inventory
     */
    public Store() {
        inventory = new HashMap<>();
        inventory.put(new Food(), STARTINGFOOD);
        inventory.put(new Energy(), STARTINGENERGY);
        inventory.put(new Smithore(), 0);
        inventory.put(new Crystite(), 0);
        inventory.put(new Mule(), STARTINGMULES);
    }

    /**
     * Attempts to buy a resource for a player
     * @param p the player trying to buy the resource
     * @param r the type of resource attempting to be bought
     * @return whether or not the transaction was successful
     */
    public final boolean buyResource(Player p, Resource r) {
   	    if (p.getMoney() < r.getCost() || getResource(r) == 0) {
            return false;
        }
        inventory.put(r, inventory.get(r) - 1);
        p.addResource(r, 1);
        p.removeMoney(r.getCost());
        return true;
    }

    /**
     * Attempts to sell a resource from a player
     * @param p the player trying to sell the resource
     * @param r the type of resource attempting to be sold
     * @return whether or not the transaction was successful
     */
    public final boolean sellResource(Player p, Resource r) {
        if (p.getBag().get(r) == 0) {
            return false;
        }
        inventory.put(r, inventory.get(r) + 1);
        p.removeResource(r, 1);
        p.addMoney(r.getCost());
        return true;
    }

    /**
     * Attempts to buy a mule for a player
     * @param p the player trying to buy the mule
     * @param type the type of mule attempting to be bought
     * @return whether or not the transaction was successful
     */
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

    /**
     * @param r the type of resource being inquired into
     * @return the amount of the resource left in the inventory
     */
    public final int getResource(Resource r) {
        return inventory.get(r);
    }

}
