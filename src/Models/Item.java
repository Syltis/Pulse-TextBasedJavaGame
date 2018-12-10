package Models;

/*
Has edited this:
- Kristoffer
*/

public class Item {

	private String itemName;
	private String itemType;
	private boolean isUnique;

	public Item(String itemName, String itemType, boolean isUnique)
	{
		this.itemName = itemName;
		this.itemType = itemType;
		this.isUnique = isUnique;
	}
	public String getItemName() {
		return itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public boolean isUnique() { return isUnique; }

	// Overridden .equals() that compares objects correctly
	public boolean equals(Object object) {
		if (!(object instanceof Item)) {
			return false;
		}
		Item aItem = (Item) object;

		return this.getItemName().equals(aItem.getItemName())
				&& this.getItemType().equals(aItem.getItemType())
				&& this.isUnique == aItem.isUnique;
	}
}
