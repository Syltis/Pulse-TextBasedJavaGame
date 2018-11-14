package Models;

/*
Has edited this:
- Kristoffer
*/

import java.util.ArrayList;

public class ChoiceV2 {

    private String id;
    private String title;
    private String description;

    private MovementCommand[] availableMovementCommands;
    private ArrayList<String> availableActionCommands;
    private ArrayList<String> availableCombatCommands;

    public ChoiceV2() {

    }

    public ChoiceV2(String id, String title, String description,
                    MovementCommand[] availableMovementCommands,
                    ArrayList availableActionCommands,
                    ArrayList availableCombatCommands) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.availableMovementCommands = availableMovementCommands;
        this.availableActionCommands = availableActionCommands;
        this.availableCombatCommands = availableCombatCommands;
    }

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

    public ArrayList<String> getAvailableActionCommands() {
        return this.availableActionCommands;
    }

    public void setAvailableActionCommands(ArrayList<String> availableActionCommands) {
        this.availableActionCommands = availableActionCommands;
    }

    public ArrayList<String> getAvailableCombatCommands() {
        return availableCombatCommands;
    }

    public void setAvailableCombatCommands(ArrayList<String> availableCombatCommands) {
        this.availableCombatCommands = availableCombatCommands;
    }
}
