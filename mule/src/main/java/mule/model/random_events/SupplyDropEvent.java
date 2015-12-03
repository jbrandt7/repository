package mule.model.random_events;

import mule.model.*;
import mule.model.player.Player;
import mule.model.resource.Food;
import mule.model.resource.Energy;
import mule.model.resource.ResourceBag;

public class SupplyDropEvent extends RandomEvent {
    public SupplyDropEvent(Player p, String msg) {
        super(p, "A SUPPLY DROP HAS ARRIVED. ALL PLAYERS RECEIVE ONE ENERGY AND ONE FOOD.");
    }
    public void commit() {
        p.getBag().addResource(new Food(), 1);
        p.getBag().addResource(new Energy(), 1);
    }
}
