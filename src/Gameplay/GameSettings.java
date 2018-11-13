package Gameplay;

/*
Has edited this:
- Kristoffer
*/

/*
Singleton class for storing data. Currently here to store the the choice-id of the active choice.
*/

import Models.Choice;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameSettings {

    private static GameSettings instance = null;
    private static int currentChoiceId;

    // The map from json sys.outs: {"go back"=0, "open door"=1}
    HashMap<String, Integer> movementCommandArchive = new HashMap<String, Integer>() {
        {
            put("open door", 1);
            put("go back", 1);
        }
    };

    List<String> actionCommandArchive = Stream.of(
            "take key",
            "inventory"

    ).collect(Collectors.toList());

    List<String> combatCommandArchive = Stream.of(
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

    public static int getCurrentChoiceId() {
        return currentChoiceId;
    }

    public static void setCurrentChoiceId(int choiceId) {
        currentChoiceId = choiceId;
    }

    public static int getCurrentChoice() {
        return currentChoiceId;
    }

    public static void setCurrentChoice(Choice choice) {
        Choice currentChoice = choice;
    }
}
