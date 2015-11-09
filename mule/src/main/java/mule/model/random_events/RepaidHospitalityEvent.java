package mule.model.random_events;

import mule.model.player.Player;
import mule.model.resources.Smithore;

public class RepaidHospitalityEvent extends RandomEvent {

    public RepaidHospitalityEvent(Player p) {
		super(p, p.getName() + ", A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING " +
                "TWO BARS OF ORE.");
    }

    public final void commit() {
		super.getPlayer().getBag().add(new Smithore(), 2);
    }

}
