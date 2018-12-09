package Models;
/*
Has edited this:
- Kristoffer
*/
import java.util.ArrayList;
import java.util.List;

public class EnemyCharacter extends Being {

	private String name;
	private String description;
	private int health;
	private int[] skills;
	private int money;
	private List<Item> inventory = new ArrayList<>();

	public EnemyCharacter(String name, String description, int health, int[] skills, int money) {
		this.name = name;
		this.description = description;
		this.health = health;
		this.skills = skills;
		this.money = money;
		addToInventory(new Item("Excalibur", "Sword", true));
		addToInventory(new Item("Bread", "Food", false));
	}
}
