package Models;

import Managers.JSONParsing;

import java.util.ArrayList;

/*
Has edited this:
- Kristoffer
*/

public class Choice {

    private int id;
    private String description;
    private ArrayList<String> availableActionCommands;
    private ArrayList<String> availableCommandTargets;
    JSONParsing JSONParser;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public ArrayList<String> getAvailableActionCommands() { return availableActionCommands; }

    public void setAvailableActionCommands(ArrayList<String> availableActionCommands) {
        this.availableActionCommands = availableActionCommands;
    }

    public ArrayList<String> getAvailableCommandTargets() { return availableCommandTargets; }

    public void setAvailableCommandTargets(ArrayList<String> availableCommandTargets) {
        this.availableCommandTargets = availableCommandTargets;
    }

    public Choice getChoiceJSON(int id) {
        JSONParser = new JSONParsing();
        return JSONParser.getChoiceFromJson(id);
    }
}
