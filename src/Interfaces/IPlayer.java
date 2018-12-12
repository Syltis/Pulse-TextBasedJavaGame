package Interfaces;

import Models.Item;
import Models.Player;

import java.util.List;

/*
Interface for accessing the player-character and the inventory
 */
public interface IPlayer {

	List<Item> getInventory();

	void addToInventory(Item item);

	Player getPlayerCharacter();
}
