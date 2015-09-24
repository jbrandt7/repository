package mule.model;

import java.util.ArrayList;

/**
 * Created by The Boat on 9/22/2015.
 */
public class ResourceBag {
    private ArrayList<Resource> list;

    public ResourceBag(ArrayList<Resource> list) {
        this.list = list;
    }
    public ArrayList<Resource> getResources() {
        return list;
    }
    public void add(Resource r) {
        list.add(r);
    }
}
