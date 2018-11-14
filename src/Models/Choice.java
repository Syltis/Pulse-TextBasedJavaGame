package Models;

/*
Has edited this:
- Kristoffer
*/

import java.util.ArrayList;
import java.util.HashMap;

public class Choice {

    private int id;
    private String title;
    private String description;
    private HashMap<String, Integer> availableMovementCommands;
    private ArrayList<String> availableActionCommands;
    private ArrayList<String> availableCombatCommands;

    public Choice() {

    }

    public Choice(int id, String title, String description,
                  HashMap availableMovementCommands,
                  ArrayList availableActionCommands,
                  ArrayList availableCombatCommands) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.availableMovementCommands = availableMovementCommands;
        this.availableActionCommands = availableActionCommands;
        this.availableCombatCommands = availableCombatCommands;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title)  { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public HashMap<String, Integer> getAvailableMovementCommands() {
        return this.availableMovementCommands;
    }

    public void setAvailableMovementCommands(HashMap<String, Integer> hashMap) {
        this.availableMovementCommands = hashMap;
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
