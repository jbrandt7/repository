package mule.model.random_events;

import mule.Main;
import mule.model.player.Player;

public class GypsyMessEvent extends RandomEvent {

    public GypsyMessEvent(Player p) {
		super(p, p.getName() + ", YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $"
				+ (6 * Main.getTurn().getM()) + " TO CLEAN IT UP.");
    }

    public final void commit() {
		super.getPlayer().removeMoney(6 * Main.getTurn().getM());
    }

}
