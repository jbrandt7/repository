/***************************/
/**Player Class*************/
/**Last updated: 9/15/2015**/

public class Player {
	private String name;
	private String race;
	private int money;
	private ResourceBag bag;
	private Mule mule;
	private Plot[] land;
	private int[][] location;
	
	public Player(String name, String race, int money, ResourceBag bag) {
		this.name = name;
		this.race = race;
		this.money = money;
		this.bag = bag;
		this.mule = null;
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
	public int addMoney(int m) {
		money += m;
		return money;
	}
	public int removeMoney(int m) {
	}

}
