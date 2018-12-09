package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Being;
import Models.Item;

import java.util.List;

public interface Playable {

	List<Item> getInventory();

	void addToInventory(Item item);

	Being getBeing();

}
