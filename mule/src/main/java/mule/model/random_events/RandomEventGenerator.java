package mule.model.random_events;

import java.util.ArrayList;
import java.util.Random;

import mule.model.player.Player;

public class RandomEventGenerator {

    private final RandomEvent event;

    public RandomEventGenerator(EventTypes type, Player p) {
        ArrayList<RandomEvent> events = new ArrayList<>();

        events.add(new AntiqueBoughtEvent(p));
        events.add(new GTAlumniEvent(p));
        events.add(new RepaidHospitalityEvent(p));
        events.add(new SoldHideEvent(p));

        if (type == EventTypes.BAD) {
            events.add(new GypsyMessEvent(p));
            events.add(new RoofEatenEvent(p));
            events.add(new ShedBrokenEvent(p));
        }

        int randIndex = new Random().nextInt(events.size());
        event = events.get(randIndex);
    }

    public final void generateAction() {
        event.commit();
    }

    public final RandomEvent getEvent() {
        return event;
    }

}
