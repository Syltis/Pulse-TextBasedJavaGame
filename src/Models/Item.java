package Models;

/*
Has edited this:
- Kristoffer
*/

public class Item {

	private String itemName;
	private String itemType;

	boolean isUnique;

	public Item(String itemName, String itemType, boolean isUnique)
	{
		this.itemName = itemName;
		this.itemType = itemType;
		this.isUnique = isUnique;
	}
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public boolean isUnique() { return isUnique; }

	public void setUnique(boolean unique) { isUnique = unique; }
}
