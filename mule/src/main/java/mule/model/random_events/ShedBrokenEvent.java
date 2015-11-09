package mule.model.random_events;

import mule.model.player.Player;
import mule.model.resources.Food;

public class ShedBrokenEvent extends RandomEvent {

    public ShedBrokenEvent(Player p) {
		super(p, p.getName() + ", MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
    }

    public final void commit() {
		int foodCount = super.getPlayer().getBag().get(new Food()) / 2;
		super.getPlayer().getBag().remove(new Food(), foodCount);
    }

}
