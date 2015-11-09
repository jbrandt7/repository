package mule.model.random_events;

import mule.model.player.Player;
import mule.model.resources.Food;
import mule.model.resources.Energy;

public class GTAlumniEvent extends RandomEvent {

    public GTAlumniEvent(Player p) {
		super(p, p.getName() + "YOU JUST RECEIVED A " +
                "PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS");
    }

    public final void commit() {
		super.getPlayer().getBag().add(new Food(), 3);
		super.getPlayer().getBag().add(new Energy(), 2);
    }

}
