package Gameplay;

/*
Has edited this:
- Kristoffer
*/

/*
Singleton class for storing game sava data and for saving a game.
A singleton has a private constructor, and can only be instantiated and accessed
    though the getInstance()-method. The method checks if the class has already been
    instantiated. If it has not it instantiates it and returns the object.
*/

import Models.*;

import java.util.ArrayList;
import java.util.List;

public class GameSettings {

    private static GameSettings instance = null;
    private static List<Item> itemBank  = null;
    private static List<MovementCommand> movementCommandBank = null;
    private static List<ActionCommand> actionCommandBank = null;
    private static List<ItemCommand> itemCommandBank = null;
    private static List<CombatCommand> combatCommandBank = null;

    private static int turnCount = 1;

    // Turncount is used to count how many choices the user has made. It is accessed in
    //   NewGame.java in the nextScenario()-method, and is displayed in the GameWindow.
    public static int getTurnCount() { return turnCount; }

    public void upTurnCount() { turnCount = turnCount + 1; }

    public synchronized static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        // TODO: These could be retrieved from JSON automatically
        // Instantiate bank of movements when the singleton is instantiated
        movementCommandBank = new ArrayList<>();
        // IntroRooms 0-4
        movementCommandBank.add(new MovementCommand("go north","introRoom1"));
        movementCommandBank.add(new MovementCommand("north","introRoom1"));
        movementCommandBank.add(new MovementCommand("go west","introRoom2"));
        movementCommandBank.add(new MovementCommand("west","introRoom2"));
        movementCommandBank.add(new MovementCommand("go east","introRoom3"));
        movementCommandBank.add(new MovementCommand("east","introRoom3"));
        movementCommandBank.add(new MovementCommand("go back","introRoom0"));
        movementCommandBank.add(new MovementCommand("go north","introRoom4"));
        movementCommandBank.add(new MovementCommand("go back","introRoom1"));
        movementCommandBank.add(new MovementCommand("go back","introRoom2"));

        // Instantiates bank for actionCommands when the singleton is instantiated
        actionCommandBank = new ArrayList<>();
        actionCommandBank.add(new ActionCommand("inventory", "inventory"));

        itemCommandBank = new ArrayList<>();
        itemCommandBank.add(new ItemCommand("take key", "Brown Key"));
        itemCommandBank.add(new ItemCommand("take gun", "9mm Pistol"));

        combatCommandBank = new ArrayList<>();
        combatCommandBank.add(new CombatCommand("attack rat", "rat221"));

        // Instantiate bank of movements when the singleton is instantiated
        // TODO The items could be stored as objects in a json file.
        itemBank = new ArrayList<>();
        // Test-items
        itemBank.add(new Item("Brown Key", "key", true));
        itemBank.add(new Item("Excalibur", "Sword", true));
        itemBank.add(new Item("Bread", "Food", false));
        itemBank.add(new Item("Health Potion", "Healing", false));
        itemBank.add(new Item("9mm Pistol", "gun", false));

        return instance;
    }

    public List<MovementCommand> getMovementCommandBank() {
        return movementCommandBank;
    }

    public List<ActionCommand> getActionCommandBank() { return actionCommandBank; }

    public List<ItemCommand> getItemCommandBank() { return itemCommandBank; }

    public List<CombatCommand> getCombatCommandBank() {
        return combatCommandBank;
    }

    public List<Item> getItemBank() { return itemBank; }
}
