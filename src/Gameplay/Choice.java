package Gameplay;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Choice {

    private String choiceDescription;
    private ArrayList<String> commands;
    private ArrayList<String> acceptedCommands;

    public void setChoice(String choiceDescription, ArrayList<String> commands, ArrayList<String> acceptedCommands) {
        setChoiceDescription(choiceDescription);
        setCommands(commands);
        setAcceptedCommands(acceptedCommands);

    }

    public String getChoiceDescription() {
        return choiceDescription;
    }

    public void setChoiceDescription(String choiceDescription) {
        this.choiceDescription = choiceDescription;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<String> commands) {
        this.commands = commands;
    }

    public ArrayList<String> getAcceptedCommands() {
        return acceptedCommands;
    }

    public void setAcceptedCommands(ArrayList<String> acceptedCommands) {
        this.acceptedCommands = acceptedCommands;
    }
}
