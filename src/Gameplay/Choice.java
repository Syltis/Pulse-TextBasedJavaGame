package Gameplay;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Choice {

    private String choiceDescription;
    private ArrayList<String> acceptedCommands;

    public void setChoice(String choiceDescription, ArrayList<String> acceptedCommands) {
        setChoiceDescription(choiceDescription);
        setAcceptedCommands(acceptedCommands);
    }

    public String getChoiceDescription() {
        return choiceDescription;
    }

    public void setChoiceDescription(String choiceDescription) {
        this.choiceDescription = choiceDescription;
    }

    public ArrayList<String> getAcceptedCommands() {
        return acceptedCommands;
    }

    public void setAcceptedCommands(ArrayList<String> acceptedCommands) {
        this.acceptedCommands = acceptedCommands;
    }
}
