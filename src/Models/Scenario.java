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

    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title)  { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

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
