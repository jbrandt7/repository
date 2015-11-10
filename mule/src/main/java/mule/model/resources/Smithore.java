package mule.model.resources;

public class Smithore extends Resource {

    private static final int VALUE = 50;

    public Smithore() {
        super();
    }

    public final boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (this == o) {
            return true;
        } else if (o instanceof Smithore) {
            return true;
        }
        return false;
    }

    public final int getCost() {
        return VALUE;
    }

    public final int hashCode() {
        return 31 * VALUE;
    }

    public final String toString() {
        return "Smithore";
    }

}
