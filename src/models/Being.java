package models;

import java.util.ArrayList;
import java.util.List;

/*
Superclass for characters(Player, Enemy)
- Game has three skills, STR(Strength), CON(Constitution), LCK(Luck).
- These skills will in the future be used for having battles between the player and enemies
 */
public class Being {

	private String name;
	private String description;
	private int health;
	private int[] skills;
	private int money;
	private final List<Item> inventory = new ArrayList<>();

	Being(String name, String description, int health, int[] skills, int money)
	{
		this.name = name;
		this.description = description;
		this.health = health;
		this.skills = skills;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getStat(String stat) {
		if(stat.equals("STR")) return this.skills[0];
		if(stat.equals("CON")) return this.skills[1];
		if(stat.equals("LCK")) return this.skills[2];
		return -1;
	}

	public void setStat(String stat, int value) {
		if(stat.equals("STR")) this.skills[0] = value;
		if(stat.equals("CON")) this.skills[0] = value;
		if(stat.equals("LCK")) this.skills[2] = value;
	}

	public int getMoney() {
		return money;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void addToInventory(Item item) {
		inventory.add(item);
	}

	public boolean isAlive() {
		return health >= 0;
	}
}
