package mule.model;

/***************************/
/**Player Class*************/
/**Last updated: 9/15/2015**/
import java.util.ArrayList;
import javafx.scene.paint.Color;

import mule.model.map.*;

public class Player implements Comparable {
	private String name, race;
	private Color color;
	private int money, score;
	private Timer timer;
	private ResourceBag bag;
	private Mule mule;
	private ArrayList<Plot> land;
	private Location location;


	private class Location {
		private int x, y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getX(){
			return x;
		}
		public int getY() {
			return y;
		}
		public void setLocation(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}


	public Player(String name, String race, Color color) {
		this.color = color;
		this.name = name;
		this.race = race;
		this.color = color;
		this.money = 100;
		this.mule = null;
		this.land = new ArrayList<Plot>();
        this.bag = new ResourceBag(new ArrayList<Resource>());
		this.score = money;
		this.timer = new Timer();
		for (Resource r : bag.getResources()) {
			score += r.getCost();
		}
	}

	public String getName() {
		return name;
	}

	public String getRace() {
		return race;
	}

	public Color getColor() {
		return color;
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
		money -= m;
		return money;
	}

	public void addMule(Mule mule) {
		this.mule = mule;
	}

	public void removeMule() {
		this.mule = null;
	}

	public void setLoc(int x, int y) {
		location.setLocation(x, y);
	}

	public void addPlot(Plot plot) {
		land.add(plot);
		plot.assignOwner(this);
		plot.getRep().setStroke(plot.getRep().getFill());
		plot.getRep().setFill(getColor());
	}

	public void addResource(Resource resource) {
		bag.add(resource);
	}

	public int getScore() {
		return score;
	}

	public int updateScore() {
	 	score = money;
		for (Resource r : bag.getResources()) {
			score += r.getCost();
		}
		for (Plot p : land) {
			score += p.getCost();
		}
		return score;
	}

	public Timer getTimer() {
		return timer;
	}

	public String toString() {
		return name;
	}

	public int hashCode() {
		return score;
	}

	public int compareTo(Object o) {
		return this.score - ((Player) o).score;
	}


}//end class
