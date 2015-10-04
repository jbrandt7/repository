package mule.model.town;

import mule.model.*;
import javafx.scene.shape.Rectangle;
/**
 * Created by harrylane on 9/16/15.
 */
public abstract class Store {

    protected Rectangle rep;
    protected Resource resource;

    public Store(Rectangle rep) {
        this.rep = rep;
    }

    public Store(Rectangle Rep, Resource resource) {
	this.rep = rep;
	this.resource = resource;
    }

    public Rectangle getRep() {
        return rep;
    }

    public void buyResource(Player p) {
   	
    }		

}
