package mule.model.random_events;

import mule.Main;
import mule.model.player.Player;

public class RoofEatenEvent extends RandomEvent {

    public RoofEatenEvent(Player p) {
		super(p, p.getName() + ", FLYING CAT-BUGS ATE THE ROOF OF YOUR HOUSE. REPAIRS COST $" +
                (4 * Main.getTurn().getM()) + ".");
    }

    public final void commit() {
        super.getPlayer().removeMoney(4 * Main.getTurn().getM());
    }

}
