package mule.model.random_events;

import mule.model.player.Player;
import mule.model.resources.Food;

public class DroughtEvent extends RandomEvent{
    public DroughtEvent(Player p) {
        super(p, "THE PLANET HAS BEEN IN A DROUGHT. ALL PLAYERS MUST EXPEND ONE FOOD TO SURVIVE.");
    }
    public final void commit() {
        player.getBag().remove(new Food(), 1);
    }
}
