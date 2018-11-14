package Gameplay;

/*
Has edited this:
- Kristoffer
*/

/*
Singleton class for storing data. Currently here to store the the choice-id of the active choice.
*/

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameSettings {

    private static GameSettings instance = null;
    private static int currentChoiceId;
    private static HashMap<String, Integer> movementCommandArchive = null;

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

    // TODO Make the movementCommandArchive a running list of the json commands, and use strings, the titles of the choices, and the JSON id.
    public synchronized static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        // These are put when the singleton is instatiated
        movementCommandArchive = new HashMap<>();
                movementCommandArchive.put("open door", 1);
                movementCommandArchive.put("open west", 2);
                movementCommandArchive.put("open east", 3);
                movementCommandArchive.put("open north", 3);
                movementCommandArchive.put("go back", 0);
        return instance;
    }

    public HashMap<String, Integer> getMovementCommandArchive() {
        return movementCommandArchive;
    }

    public List<String> getActionCommandArchive() {
        return actionCommandArchive;
    }

    public List<String> getCombatCommandArchive() {
        return combatCommandArchive;
    }
}
