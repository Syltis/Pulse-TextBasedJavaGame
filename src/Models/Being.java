package Models;

import Interfaces.Playable;

import java.util.ArrayList;
import java.util.List;

public class Being implements Playable {

	private String name;
	private String description;
	private int health;
	private int[] skills;
	private int money;
	private List<Item> inventory = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		description = description;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int[] getSkills() {
		return skills;
	}

	public void setSkills(int[] skills) {
		this.skills = skills;
	}

	public int getStat(String stat) {
		if(stat.equals("STR")) return this.skills[0];
		if(stat.equals("LCK")) return this.skills[1];
		if(stat.equals("CON")) return this.skills[2];

		return -1;
	}

	public void setStat(String stat, int value) {
		if(stat.equals("STR")) {
			this.skills[0] = value;
		}
		if(stat.equals("LCK")) this.skills[0] = value;
		if(stat.equals("CHR")) this.skills[1] = value;
		if(stat.equals("CON")) this.skills[2] = value;

	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void addToInventory(Item item) {
		inventory.add(item);
	}
}