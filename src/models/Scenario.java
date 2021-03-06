package models;

/*
Model for the scenarios that are read from json and used to store data on the scenario and which commands are available.
 */
public class Scenario {

    // Variables not assigned as they are parsed directly from json
    private String id;
    private String title;
    private String description;
    private MovementCommand[] availableMovementCommands;
    private ActionCommand[] availableActionCommands;
    private ItemCommand[] availableItemCommands;
    private CombatCommand[] availableCombatCommands;

    public String getId() { return id; }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public MovementCommand[] getAvailableMovementCommands() {
        return availableMovementCommands;
    }

    public ActionCommand[] getAvailableActionCommands() {
        return this.availableActionCommands;
    }

    public ItemCommand[] getAvailableItemCommands() {
        return availableItemCommands;
    }

    public CombatCommand[] getAvailableCombatCommands() {
        return availableCombatCommands;
    }
}
