package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Item;

import java.util.List;

public interface Playable {

	List<Item> getInventory();

	void setInventory(List<Item> inventory);

	void addToInventory(Item item);

}
