package mule.model;

public class Timer {

    private int time;
    private static final int TOTAL_TIME = 30;

    public Timer() {
        time = 0;
    }

    public void tick() {
        time++;
    }

    public void reset() {
        time = 0;
    }

    public boolean outOfTime() {
        return time >= TOTAL_TIME;
    }

    public int getTime() {
        return time;
    }

}
