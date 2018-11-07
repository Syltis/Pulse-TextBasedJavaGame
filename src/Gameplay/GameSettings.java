package Gameplay;

/*
Has edited this:
- Kristoffer

Singleton class for storing data. Currently here to store the the choice-id of the active choice.
*/

public class GameSettings {

    private static GameSettings instance = null;
    private static int currentChoiceId;

    private GameSettings() {}

    public synchronized static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }

    public static int getCurrentChoiceId() {
        return currentChoiceId;
    }

    public static void setCurrentChoiceId(int currentChoiceId) {
        currentChoiceId = currentChoiceId;
    }
}
