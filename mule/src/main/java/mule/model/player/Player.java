package mule.model;

/***************************/
/**Player Class*************/
/**Last updated: 9/15/2015**/
import java.util.ArrayList;
import javafx.scene.paint.Color;

import mule.model.map.*;
import mule.model.*;
import mule.model.resources.*;

public abstract class Player implements Comparable, java.io.Serializable {

    private static final long serialVersionUID = 42L;

	private String name, race;
	private double[] color;
	private int money, score;
	private Timer timer;
	private ResourceBag bag;
	private Mule mule;
	private ArrayList<Plot> land;

	public Player(String name, Color color) {
		this.name = name;
		this.race = race;
		this.color = new double[] { (color.getRed()),
                (color.getGreen()), (color.getBlue()) };
		this.money = 1000;
		this.mule = null;
		this.land = new ArrayList<Plot>();
        this.bag = new ResourceBag();

		bag.add(new Food(), 8);
		bag.add(new Energy(), 4);

		this.score = money;
		this.timer = new Timer();
		score += bag.getTotalCost();

	}

	public String getName() {
		return name;
	}

	public String getRace() {
		return race;
	}

	public Color getColor() {
        return new Color(color[0], color[1], color[2], 1.0);
	}

	public int getMoney() {
		return money;
	}

	public ResourceBag getBag() {
		return bag;
	}

	public int addMoney(int m) {
		money += m;
		return money;
	}

	public int removeMoney(int m) {
		if (m >= money) {
			money = 0;
		} else {
			money -= m;
		}
		return money;
	}

	public void addMule(Mule mule) {
		this.mule = mule;
	}

	public Mule removeMule() {
        Mule result = mule;
		this.mule = null;
        return result;
	}

    public Mule getMule() {
        return mule;
    }

	public void addPlot(Plot plot) {
		land.add(plot);
		plot.assignOwner(this);
		plot.draw(this);
	}

	public void addResource(Resource resource, int amount) {
		bag.add(resource, amount);
	}

	public void removeResource(Resource resource, int amount) {
		bag.remove(resource, amount);
	}

	public int getScore() {
		return score;
	}

	public int updateScore() {
	    score = money;
		score += bag.getTotalCost();

		for (Plot p : land) {
			score += p.getCost();
		}

		return score;
	}

	public void produce() {
		for (Plot plot : land) {
			plot.produce();
		}
	}

	public Timer getTimer() {
		return timer;
	}

	public String toString() {
		return name + "\nCash: " + money + "\nFood: "
				+ bag.get(new Food()) + "\nEnergy: "
				+ bag.get(new Energy());
	}

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (other instanceof Player) {
            Player that = (Player) other;
            return that.name.equals(this.name)
                    && that.race.equals(this.name)
                    && that.color.equals(this.color);
        }

        return false;
    }

	public int hashCode() {
		return score;
	}

	public int compareTo(Object o) {
		return this.score - ((Player) o).score;
	}
	public boolean hasMule() {
		return (this.mule == null);
	}

    private String colorToString(Color c) {
        return String.format("#%02X%02X%02X", (int) (c.getRed() * 255),
                (int) (c.getGreen() * 255), (int) (c.getBlue() * 255));
    }


}
