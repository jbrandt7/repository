package mule.model.resources;

import mule.model.*;

public interface Tradeable {

    boolean buy(Player p);

    int getCost();

}
