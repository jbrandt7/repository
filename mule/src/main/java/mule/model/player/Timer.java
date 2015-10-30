package mule.model;

public class Timer implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    private int time;
    private static final int TOTAL_TIME = 60;

    public Timer() {
        time = TOTAL_TIME;
    }

    public void tick() {
        time--;
    }

    public void reset() {
        time = TOTAL_TIME;
    }

    public boolean outOfTime() {
        return time == 0;
    }

    public int getTime() {
        return time;
    }

}
