package Gameplay;

/*
Has edited this:
- Kristoffer
*/

/*
Singleton class for storing game sava data and for saving a game.
*/

import Models.MovementCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameSettings {

    private static GameSettings instance = null;
    private static List<MovementCommand> movementCommandBank = null;
    private static final List<String> actionCommandBank = Stream.of(
            "take key",
            "inventory"

    ).collect(Collectors.toList());
    private static final List<String> combatCommandBank = Stream.of(
            "kill rat"

    ).collect(Collectors.toList());
    private int turnCount = 1;

    private static final List<String> swearWords = Stream.of(
            "fuck",
            "asshole",
            "bitch",
            "shit"
    ).collect(Collectors.toList());

    private GameSettings()
    {

    }

    public int getTurnCount() { return turnCount; }

    public void upTurnCount() { turnCount = turnCount + 1; }

    public static List<String> getSwearWords() {
        return swearWords;
    }

    public synchronized static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        // Set archive of movements when instantiated
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
