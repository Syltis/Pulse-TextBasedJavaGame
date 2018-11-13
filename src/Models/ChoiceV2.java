package Models;

/*
Has edited this:
- Kristoffer
*/

import Managers.JSONParsing;
import java.util.ArrayList;
import java.util.HashMap;

public class ChoiceV2 {

    private int id;
    private String description;
    private HashMap<String, Integer> availableMovementCommands;
    private ArrayList<String> availableActionCommands;
    private ArrayList<String> availableCombatCommands;

    JSONParsing JSONParser;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public HashMap<String, Integer> getAvailableMovementCommands() {
        return availableMovementCommands;
    }

    public void setAvailableMovementCommands(HashMap<String, Integer> hashMap) {
        this.availableMovementCommands = hashMap;
    }

    public ArrayList<String> getAvailableActionCommands() {
        return availableActionCommands;
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

    public ChoiceV2() {

    }

    public ChoiceV2(int id, String description,
                    HashMap availableMovementCommands,
                        ArrayList availableActionCommands,
                            ArrayList availableCombatCommands) {
        this.id = id;
        this.description = description;
        this.availableMovementCommands = availableMovementCommands;
        this.availableActionCommands = availableActionCommands;
        this.availableCombatCommands = availableCombatCommands;

    }
}
