package mule.model.town;

import mule.model.*;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class MuleStore extends Store {
    private ArrayList<Mule> muleSupply;
    public MuleStore(Rectangle rep, int quantity) {
        super(rep);
        rep.setFill(Color.YELLOW);
	muleSupply = new ArrayList<Mule>(quantity);
	for (int i = 0; i < quantity; i++) {
		muleSupply.add(new Mule());
	}
    }
    public boolean buyMule(Player p) {
	if (muleSupply.size() == 0 || p.getMoney() < muleSupply.get(0).getCost()
			|| p.hasMule()) {
		return false;
	}

	p.removeMoney(muleSupply.get(0).getCost());
	p.addMule(muleSupply.get(muleSupply.size() - 1));
	muleSupply.remove(muleSupply.size() - 1);
	return true;

    }
    public int getMulesRemaining() {
    	return muleSupply.size();
    }
}
