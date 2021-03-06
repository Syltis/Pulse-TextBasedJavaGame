package models;

/*
Model for Items, which are read from json and parsed.
- Equals method has been overwritten so that objects can be compared properly.
 */

public class Item {

	private String itemName;
	private String itemType;
	private String effect;
	private boolean isUnique;

	public Item() {}

	public Item(String itemName, String itemType, String effect, boolean isUnique)
	{
		this.itemName = itemName;
		this.itemType = itemType;
		this.effect = effect;
		this.isUnique = isUnique;
	}
	public String getItemName() {
		return itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public String getEffect() { return effect; }

	public boolean isUnique() { return isUnique; }

	// Overridden .equals() that compares objects correctly
	public boolean equals(Object object) {
		if (!(object instanceof Item)) {
			return false;
		}
		Item aItem = (Item) object;

		return this.getItemName().equals(aItem.getItemName())
				&& this.getItemType().equals(aItem.getItemType())
				&& this.getEffect().equals(aItem.getEffect())
				&& this.isUnique == aItem.isUnique;
	}
}
