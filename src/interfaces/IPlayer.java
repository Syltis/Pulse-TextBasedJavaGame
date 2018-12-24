package interfaces;

import models.Item;
import models.Player;

import java.util.List;

/*
Interface for accessing the player-character and the inventory
 */
public interface IPlayer {

	List<Item> getInventory();

	void addToInventory(Item item);

	Player getPlayerCharacter();

	Item getEquippedItem();

	void setEquippedItem(Item equippedItem);
}
