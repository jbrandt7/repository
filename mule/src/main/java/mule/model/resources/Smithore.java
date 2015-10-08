package mule.model.resources;

public class Smithore extends Resource {

    private static final int VALUE = 50;

    public Smithore() {
        super(VALUE);
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (this == o) {
            return true;
        } else if (o instanceof Smithore) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Smithore";
    }

}
