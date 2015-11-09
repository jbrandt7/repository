package mule.model.resources;

/**
 * Represents a resoure that a player can have
 */
public abstract class Resource implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * @return The cost of the resource
     */
    public abstract int getCost();

    public abstract int hashCode();

}
