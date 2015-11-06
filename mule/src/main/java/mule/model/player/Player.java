package mule.model.player;

/***************************/
/**Player Class*************/
/**Last updated: 9/15/2015**/
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import java.util.Arrays;

import mule.model.map.*;
import mule.model.resources.*;

public abstract class Player implements Comparable, java.io.Serializable {

    private static final long serialVersionUID = 42L;

	private final String name;
	private final double[] color;
	private int money, score;
	private final Timer timer;
	private final ResourceBag bag;
	private Mule mule;
	private final List<Plot> land;

    Player(String n, Color c) {
		this.name = n;
		this.color = new double[] { (c.getRed()),
                (c.getGreen()), (c.getBlue()) };
		this.money = 1000;
		this.mule = null;
		this.land = new ArrayList<>();
        this.bag = new ResourceBag();

		bag.add(new Food(), 8);
		bag.add(new Energy(), 4);

		this.score = money;
		this.timer = new Timer();
		score += bag.getTotalCost();

	}

	public final String getName() {
		return name;
	}

	public final Color getColor() {
        return new Color(color[0], color[1], color[2], 1.0);
	}

	public final int getMoney() {
		return money;
	}

	public final ResourceBag getBag() {
		return bag;
	}

	public final int addMoney(int m) {
		money += m;
		return money;
	}

	public final int removeMoney(int m) {
		if (m >= money) {
			money = 0;
		} else {
			money -= m;
		}
		return money;
	}

	public final void addMule(Mule m) {
		this.mule = m;
	}

	public final Mule removeMule() {
        Mule result = mule;
		this.mule = null;
        return result;
	}

    public final Mule getMule() {
        return mule;
    }

	public final void addPlot(Plot plot) {
		land.add(plot);
		plot.assignOwner(this);
		plot.draw(this);
	}

	public final void addResource(Resource resource, int amount) {
		bag.add(resource, amount);
	}

	public final void removeResource(Resource resource, int amount) {
		bag.remove(resource, amount);
	}

	public final int getResource(Resource resource) {
		return bag.get(resource);
	}

	public final int updateScore() {
	    score = money;
		score += bag.getTotalCost();

		for (Plot p : land) {
			score += p.getCost();
		}

		return score;
	}

	public final void produce() {
		for (Plot plot : land) {
			plot.produce();
		}
	}

	public final Timer getTimer() {
		return timer;
	}

    public final int getScore() {
        return score;
    }

	public final String toString() {
		return name + "\nCash: " + money + "\nFood: "
				+ bag.get(new Food()) + "\nEnergy: "
				+ bag.get(new Energy());
	}

    public final boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (other instanceof Player) {
            Player that = (Player) other;
            return that.name.equals(this.name)
                    && Arrays.equals(that.color, this.color);
        }

        return false;
    }

	public final int hashCode() {
		return score;
	}

	public final int compareTo(Object o) {
		return this.score - ((Player) o).score;
	}

	public final boolean hasMule() {
		return (this.mule == null);
	}


}
