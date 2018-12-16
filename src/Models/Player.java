package Models;

import Interfaces.IPlayer;

/*
See superclass 'Being' for details
 */
public class Player extends Being implements IPlayer {

    private Item equippedItem;

    public Player(String name, String description, int health, int[] skills, int money)
    {
        super(name, description, health, skills, money);
        addToInventory(new Item("Bread", "food", "health+10", false));
    }
    
    public Player()
    {
        super("Unnamed",
                "No description",
                100,
                new int[] {3,3,3},
                50

        );
        addToInventory(new Item("Bread", "Food", "health+10", false));
    }

    public Player getPlayerCharacter() {
        return Player.this;
    }

    public Item getEquippedItem() { return equippedItem; }

    public void setEquippedItem(Item equippedItem) { this.equippedItem = equippedItem; }
}