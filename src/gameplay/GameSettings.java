package gameplay;

/*
Singleton class for storing game sava data and for saving a game.
A singleton has a private constructor, and can only be instantiated and accessed
    though the getInstance()-method. The method checks if the class has already been
    instantiated. If it has not it instantiates it and returns the object.
*/

import json.JSONParsing;
import models.*;

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

    // Method for instantiating and getting the singleton instance
    public synchronized static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        readListsFromJson();
        return instance;
    }

    // Lists which keeps all the available commands and items. The list is used for reference, as in CommandControl
    public List<MovementCommand> getMovementCommandBank() { return movementCommandBank; }

    public List<ActionCommand> getActionCommandBank() { return actionCommandBank; }

    public List<ItemCommand> getItemCommandBank() { return itemCommandBank; }

    public List<CombatCommand> getCombatCommandBank() { return combatCommandBank; }

    public List<Item> getItemBank() { return itemBank; }

    // Method for adding all the available commands to the lists, from json
    private static void readListsFromJson() {
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
