package Managers;

import DataTransferObjects.PlayerCommand;
import GUI.GameWindow;
import Gameplay.GameSettings;
import java.util.ArrayList;

/*
Has edited this:
- Kristoffer
*/

public class PlayerInput {

    GameSettings gameSettings;
    ArrayList<String> splitCommandList;
    PlayerCommand playerCommand;
    GameWindow gameWindow;
    String shortcutCommand; // TODO: Functionality for shortcuts.

    // Called in gameWindow when given user input.
    public void receiveCommand(String input) {
        input = cleanString(input);

        // Check if it contains two words. Should not be any whitespace left after cleaning if it's one word.
        if(input.matches("\\s+")) {
            splitCommandList = splitCommand(input);
            playerCommand = new PlayerCommand(splitCommandList.get(0), splitCommandList.get(1));
        }
        else
            // This is for the optional shortcut functionality
            playerCommand = new PlayerCommand(input, " ");

    }

    // Separates string by whitespace
    // Clean the String before using this!
    public ArrayList<String> splitCommand(String input) {
        String actionCommand = input.substring(0, input.indexOf(" "));
        String commandTarget = input.substring(input.indexOf(" "), input.length());
        splitCommandList = new ArrayList<>();
        splitCommandList.add(actionCommand);
        splitCommandList.add(commandTarget);
        return splitCommandList;
    }

    // Trim string, turn double+ whitespace into single, lowercase and remove everything but letters.
    public String cleanString(String string) {
        string = string.trim();
        string = string.replaceAll("[-!@#$%^&*(),.?\":{}|<>0-9+/']+", "");
        string = string.replaceAll("[ ]{2,}", " ");
        string = string.toLowerCase();
        return string;
    }
}
