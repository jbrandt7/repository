package mule.model.resources;

public class Food extends Resource {

    private static final int VALUE = 30;

    public Food() {
        super(VALUE);
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (this == o) {
            return true;
        } else if (o instanceof Food) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Food";
    }
}
