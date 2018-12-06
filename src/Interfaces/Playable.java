package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Gameplay.Character;
import Models.Item;

import java.util.List;

public interface Playable {

	List<Item> getInventory();

	void addToInventory(Item item);

	Character getCharacter();

}
