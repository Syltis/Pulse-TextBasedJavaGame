package Models;

public class Enemy extends Being {

	public Enemy(String name, String description, int health, int[] skills, int money)
	{
		super(name, description, health, skills, money);
		addToInventory(new Item("Bread", "Food", "health+10", false));
	}
}
