package models;

import interfaces.IPlayer;

/*
See superclass 'Being' for details
 */
public class Player extends Being implements IPlayer {

    private Item equippedItem = null;

    public Player()
    {
        super("Unnamed",
                "No description",
                100,
                new int[] {3,3,3},
                50

        );

        // Test item
        addToInventory(new Item("Bread", "Food", "health+10", false));
    }

    public Player getPlayerCharacter() {
        return Player.this;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

}