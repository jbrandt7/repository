package mule.model.random_events;

import mule.Main;
import mule.model.player.Player;

public class AntiqueBoughtEvent extends RandomEvent {

    public AntiqueBoughtEvent(Player p) {
        super(p, "THE MUSEUM BOUGHT " + p.getName() + " ANTIQUE PERSONAL COMPUTER FOR $");
    }

    public final void commit() {
		super.getPlayer().addMoney(8 * Main.getTurn().getM());
    }

}

