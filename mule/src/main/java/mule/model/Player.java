package mule.model;

/***************************/
/**Player Class*************/
/**Last updated: 9/15/2015**/
import java.util.ArrayList;


public class Player {
	private String name, race, color;
	private int money, score;
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


	public Player(String name, String race, String color) {
		this.color = color;
		this.name = name;
		this.race = race;
		this.money = 100;
		this.mule = null;
		this.score = money;
		for (Resource r : bag.getResources()) {
			score += r.getValue();
		}
	}
	
	public String getName() {
		return name;
	}
	public String getRace() {
		return race;
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
	}
	public void addResource(Resource resource) {
		bag.add(resource);
	}
	public int getScore() {
		return score;
	}
	public int updateScore() {
	 	score = 0;
		score += money;
		for (Resource r : bag.getResources()) {
			score += r.getValue();
		}
		for (Plot p : land) {
			score += p.getValue();
		}
		return score;

	}
	

}//end class
