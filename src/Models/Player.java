package Models;

import Interfaces.Playable;

import java.util.ArrayList;
import java.util.List;

public class Player implements Playable {

	private String playerName;

	private List<String> inventory = new ArrayList<>();

	public Player(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public List<String> getInventory() {
		return inventory;
	}

	public void setInventory(List<String> inventory) {
		this.inventory = inventory;
	}
}
