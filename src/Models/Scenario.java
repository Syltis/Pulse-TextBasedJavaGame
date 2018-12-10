package Models;

/*
Has edited this:
- Kristoffer
*/

public class Scenario {

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

    public void setAvailableMovementCommands(MovementCommand[] availableMovementCommands) {
        this.availableMovementCommands = availableMovementCommands;
    }

    public ActionCommand[] getAvailableActionCommands() {
        return this.availableActionCommands;
    }

    public void setAvailableActionCommands(ActionCommand[] availableActionCommands) {
        this.availableActionCommands = availableActionCommands;
    }

    public ItemCommand[] getAvailableItemCommands() {
        return availableItemCommands;
    }

    public void setAvailableItemCommands(ItemCommand[] availableItemCommands) {
        this.availableItemCommands = availableItemCommands;
    }

    public CombatCommand[] getAvailableCombatCommands() {
        return availableCombatCommands;
    }

    public void setAvailableCombatCommands(CombatCommand[] availableCombatCommands) {
        this.availableCombatCommands = availableCombatCommands;
    }
}
