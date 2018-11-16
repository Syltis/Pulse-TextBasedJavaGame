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
    private static List<MovementCommand> movementCommandArchive = null;

    // The map from json sys.outs: {"go back"=0, "open door"=1}
    // The map from this hashmap is {go back=1, open door=1}

    private static final List<String> actionCommandArchive = Stream.of(
            "take key",
            "inventory"

    ).collect(Collectors.toList());

    private static final List<String> combatCommandArchive = Stream.of(
            "kill rat"

    ).collect(Collectors.toList());

    private GameSettings() {

    }

    // PASS GAMESETTINGS FROM OUTER CLASS
    public synchronized static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        // Set archive of movements when instantiated
        movementCommandArchive = new ArrayList<>();
        movementCommandArchive.add(new MovementCommand("open door","StartRoom2"));
        movementCommandArchive.add(new MovementCommand("go back","StartRoom"));
        movementCommandArchive.add(new MovementCommand("go back","StartRoom2"));

        return instance;
    }
    // TODO automatically get all movementcommands from JSON
    public List<MovementCommand> getMovementCommandArchive() {
        return movementCommandArchive;
    }

    public List<String> getActionCommandArchive() {
        return actionCommandArchive;
    }

    public List<String> getCombatCommandArchive() {
        return combatCommandArchive;
    }
}
