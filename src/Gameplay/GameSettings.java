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

import Managers.JSONParsing;
import Models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
        readListsFromJson();
        return instance;
    }

    public List<MovementCommand> getMovementCommandBank() { return movementCommandBank; }

    public List<ActionCommand> getActionCommandBank() { return actionCommandBank; }

    public List<ItemCommand> getItemCommandBank() { return itemCommandBank; }

    public List<CombatCommand> getCombatCommandBank() { return combatCommandBank; }

    public List<Item> getItemBank() { return itemBank; }

    private static void readListsFromJson() {
        // Instantiate lists of available commands for referencing in CommandControl
        movementCommandBank = new ArrayList<>();
        for (Scenario aScenario:JSONParsing.getScenarioListFromJson()) {
            movementCommandBank.addAll(Arrays.asList(aScenario.getAvailableMovementCommands()));
        }
        actionCommandBank = new ArrayList<>();
        for (Scenario aScenario:JSONParsing.getScenarioListFromJson()) {
            actionCommandBank.addAll(Arrays.asList(aScenario.getAvailableActionCommands()));
        }
        itemCommandBank = new ArrayList<>();
        for (Scenario aScenario:JSONParsing.getScenarioListFromJson()) {
            itemCommandBank.addAll(Arrays.asList(aScenario.getAvailableItemCommands()));
        }
        combatCommandBank = new ArrayList<>();
        for (Scenario aScenario:JSONParsing.getScenarioListFromJson()) {
            combatCommandBank.addAll(Arrays.asList(aScenario.getAvailableCombatCommands()));
        }
        // Instantiate a bank of item to reference in commandControl
        itemBank = new ArrayList<>();
        Collection<Item> itemList = JSONParsing.getItemListFromJson();
        itemBank.addAll(itemList);
    }
}
