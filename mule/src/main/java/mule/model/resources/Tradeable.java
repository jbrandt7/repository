package mule.model.resources;

import mule.model.*;
import mule.model.player.*;

public interface Tradeable {

    boolean buy(Player p);

    int getCost();

}
