package DataTransferObjects;

import java.util.ArrayList;

public class Situation {

    private int id;
    private String description;
    private ArrayList<String> availableActionCommands;
    private ArrayList<String> availableCommandTargets;

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
}
