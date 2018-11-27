package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Item;

import java.util.List;

public interface Playable {

	public List<Item> getInventory();

	public void setInventory(List<Item> inventory);

}
