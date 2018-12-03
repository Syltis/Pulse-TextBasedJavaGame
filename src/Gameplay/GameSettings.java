package Gameplay;

/*
Has edited this:
- Kristoffer
*/

/*
Singleton class for storing game sava data and for saving a game.
A singleton has a private constructor, and can only be instantiated and accessed
    though the getInstance()-method. The method checks if the class has already been
    instantiated. If it has not, it instantiates it, and return the object.
*/

import Models.Item;
import Models.MovementCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameSettings {

    private static GameSettings instance = null;
    private static List<Item> itemBank  = null;
    private static List<MovementCommand> movementCommandBank = null;
    private static final List<String> actionCommandBank = Stream.of(
            "take key",
            "inventory"

    ).collect(Collectors.toList());
    private static final List<String> combatCommandBank = Stream.of(
            "attack rat"

    ).collect(Collectors.toList());

    // TODO this bugs int he beginning, 0-3
    private int turnCount = 1;

    // Turncount is used to count how many choices the user has made. It is accessed in
    //   NewGame.java in the nextScenario()-method, and is displayed in the GameWindow.
    public int getTurnCount() { return turnCount; }

    public void upTurnCount() { turnCount = turnCount + 1; }

    public synchronized static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        // Instantiate bank of movements when the singleton is instantiated
        // TODO: These could be retrieved from JSON automatically
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

        // Instantiate bank of movements when the singleton is instantiated
        itemBank = new ArrayList<>();
        // Test-items
        itemBank.add(new Item("Rusty key", "key"));

        return instance;
    }

    // TODO automatically get all movementcommands from JSON
    public List<MovementCommand> getMovementCommandBank() {
        return movementCommandBank;
    }

    public List<String> getActionCommandBank() {
        return actionCommandBank;
    }

    public List<String> getCombatCommandBank() {
        return combatCommandBank;
    }
}
