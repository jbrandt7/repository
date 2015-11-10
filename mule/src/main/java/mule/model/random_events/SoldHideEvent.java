package mule.model.random_events;

import mule.Main;
import mule.model.player.Player;

public class SoldHideEvent extends RandomEvent {

    public SoldHideEvent(Player p) {
		super(p, p.getName() + ", YOU FOUND A DEAD MOOSE RAT AN SOLD THE HIDE FOR $" +
                (2 * Main.getTurn().getM()) + ".");
    }

    public final void commit() {
        super.getPlayer().addMoney(2 * Main.getTurn().getM());
    }

}
