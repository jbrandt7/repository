package mule.model.random_events;

import mule.model.player.Player;
import mule.model.resource.Food;

public class DroughtEvent extends RandomEvent{
    public DroughtEvent(Player p) {
        super(player, "THE PLANET HAS BEEN IN A DROUGHT. ALL PLAYERS MUST EXPEND ONE FOOD TO SURVIVE.");
    }
    public final void commit() {
        player.getBag().removeResource(new Food(), 1);
    }
}
