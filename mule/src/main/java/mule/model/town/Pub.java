package mule.model.town;

import mule.model.player.*;

public class Pub extends Store {

    public Pub() {
        super();
    }

    public final int cashOut(Player player) {
        int result = player.getTimer().getTime();
        return result;
    }

}
