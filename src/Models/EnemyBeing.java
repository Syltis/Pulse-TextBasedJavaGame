package Models;
/*
Has edited this:
- Kristoffer
*/
public class EnemyBeing extends Being {

	public EnemyBeing(String name, String description, int health, int[] skills, int money)
	{
		super(name, description, health, skills, money);
		addToInventory(new Item("Bread", "Food", false));
	}
}
