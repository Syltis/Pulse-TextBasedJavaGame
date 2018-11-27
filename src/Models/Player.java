package Models;

import Interfaces.Playable;
import java.util.ArrayList;
import java.util.List;

/*
Has edited this:
- Kristoffer
*/

public class Player implements Playable {

	private String playerName;

	private List<Item> inventory = new ArrayList<>();

	public Player(String playerName)
	{
		this.playerName = playerName;
		addToInventory(new Item("Excalibur", "Sword"));
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	public void addToInventory(Item item) {
		inventory.add(item);
	}
}
