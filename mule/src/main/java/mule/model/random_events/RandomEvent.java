package mule.model.random_events;

import mule.model.player.Player;

@SuppressWarnings("CanBeFinal")
public abstract class RandomEvent {
    protected String message;
    protected Player player;
    protected boolean isGlobal;

    RandomEvent(Player p, String msg) {
        player = p;
        message = msg;
    }

    public abstract void commit();

    public final String getMessage() {
        return message;
    }
    public boolean isGlobal() { return isGlobal; }

    public final Player getPlayer() { return player; }

    public final void updatePlayer(Player p) {
        player = p;
    }
}
