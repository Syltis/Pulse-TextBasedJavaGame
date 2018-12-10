package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Item;
import Models.PlayerBeing;

import java.util.List;

public interface IPlayerBeing {

	List<Item> getInventory();

	void addToInventory(Item item);

	PlayerBeing getPlayerCharacter();
}
