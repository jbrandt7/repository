package mule.model.random_events;

import mule.model.player.Player;

@SuppressWarnings("CanBeFinal")
public abstract class RandomEvent {
    private static String message;
    private Player player;

    RandomEvent(Player p, String msg) {
        player = p;
        message = msg;
    }

    public abstract void commit();

    public final String getMessage() {
        return message;
    }

    public final Player getPlayer() { return player; }
}
