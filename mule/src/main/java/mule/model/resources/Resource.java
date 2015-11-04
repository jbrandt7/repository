package mule.model.resources;

public class Resource implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    private int value;

    public Resource(int v) {
        this.value = v;
    }

    public int getCost() {
        return value;
    }

    public int hashCode() {
        return 31 * value + 11;
    }

}
