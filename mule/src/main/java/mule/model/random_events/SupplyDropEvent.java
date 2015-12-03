package mule.model.random_events;

import mule.model.*;
import mule.model.player.Player;
import mule.model.resources.Food;
import mule.model.resources.Energy;
import mule.model.resources.ResourceBag;

public class SupplyDropEvent extends RandomEvent {
    public SupplyDropEvent(Player p) {
        super(p, "A SUPPLY DROP HAS ARRIVED. ALL PLAYERS RECEIVE ONE ENERGY AND ONE FOOD.");
    }
    public void commit() {
        super.getPlayer().getBag().add(new Food(), 1);
        super.getPlayer().getBag().add(new Energy(), 1);
    }
}
