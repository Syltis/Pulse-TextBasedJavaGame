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
		// Default items for testing
		addToInventory(new Item("Excalibur", "Sword"));
		addToInventory(new Item("Bread", "Food"));
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

	public void addToInventory(Item item) {
		inventory.add(item);
	}
}
