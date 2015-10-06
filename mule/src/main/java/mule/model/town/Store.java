package mule.model.town;

import mule.model.*;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
/**
 * Created by harrylane on 9/16/15.
 */
public abstract class Store {

    protected Rectangle rep;
    protected Resource resource;
    protected ArrayList<Resource> typesOfResources;

    public Store(Rectangle rep) {
        this.rep = rep;
	typesOfResources.add(new Energy(40, "Energy"));
	typesOfResources.add(new Food(40, "Food"));
	typesOfResources.add(new Smithore(40, "Smithore"));		
	
    }

    public Store(Rectangle Rep, Resource resource) {
	this.rep = rep;
	this.resource = resource;
    }

    public Rectangle getRep() {
        return rep;
    }

    public boolean buyResource(Player p, Resource r) {
   	if (p.getMoney() < r.getCost()) {
		return false;
	}
	int i = 0;
	for (; i < typesOfResources.size(); i++) {
		if (r.getName().compareTo(typesOfResources
				.get(i).getName()) == 0) {
			break;
		}
			
	}
	p.getBag().add(typesOfResources.get(i));
	p.removeMoney(typesOfResources.get(i).getCost());
	return true;
    }		

}
