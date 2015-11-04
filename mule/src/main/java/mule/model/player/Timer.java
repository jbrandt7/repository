package mule.model.player;

public class Timer implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    private int time;
    private static final int TOTAL_TIME = 60;

    public Timer() {
        time = TOTAL_TIME;
    }

    public final void tick() {
        time--;
    }

    public final void reset() {
        time = TOTAL_TIME;
    }

    public final boolean outOfTime() {
        return time == 0;
    }

    public final int getTime() {
        return time;
    }

}
