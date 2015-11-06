package mule.model.resources;

public abstract class Resource implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    public abstract int getCost();

    public abstract int hashCode();

}
