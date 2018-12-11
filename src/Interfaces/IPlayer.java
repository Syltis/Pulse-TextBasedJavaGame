package Interfaces;

import Models.Item;
import Models.Player;

import java.util.List;

public interface IPlayer {

	List<Item> getInventory();

	void addToInventory(Item item);

	Player getPlayerCharacter();
}
