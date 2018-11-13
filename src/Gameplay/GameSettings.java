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

    // The map from json sys.outs: {"go back"=0, "open door"=1}
    // The map from this hashmap is {go back=1, open door=1}
    private static final HashMap<String, Integer> movementCommandArchive = new HashMap<String, Integer>() {
        {
            put("open door", 1);
            put("go back", 1);
        }
    };

    private static final List<String> actionCommandArchive = Stream.of(
            "take key",
            "inventory"

    ).collect(Collectors.toList());

    private static final List<String> combatCommandArchive = Stream.of(
            "kill rat"

    ).collect(Collectors.toList());

    private GameSettings() {

    }

    public synchronized static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
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
