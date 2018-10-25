package Managers;

import DataTransferObjects.PlayerCommand;
import java.util.ArrayList;

public class PlayerInput {

    ArrayList<String> commandList;
    PlayerCommand playerCommand;
    String shortcutCommand; // TODO: Functionality for shortcuts.

    // Called in gameWindow when given user input.
    public void receiveCommand(String input) {
        input = cleanString(input);
        // Check if it contains two words. Should not be any whitespace left after cleaning if it's one word.
        if(input.matches("\\s+")) {
            commandList = splitCommand(input);
            playerCommand = new PlayerCommand(commandList.get(0), commandList.get(1));
        }
        else {
            shortcutCommand = commandList.get(0); // Has no functionality yet.
        }
    }

    // Clean the String before using this!
    // TODO Should this just set the commandList and not return it
    public ArrayList<String> splitCommand(String input) {
        // TODO: Test this
        String actionCommand = input.substring(0, input.indexOf(" "));
        String commandTarget = input.substring(input.indexOf(" "), input.length());
        commandList = new ArrayList<>();
        commandList.add(actionCommand);
        commandList.add(commandTarget);
        return commandList;
    }

    // Trim string, turn double+ whitespace into single, lowercase and remove all other than letters.
    public String cleanString(String string) {
        string = string.trim();
        string = string.replaceAll("[-!@#$%^&*(),.?\":{}|<>0-9+/']+", "");
        string = string.replaceAll("[ ]{2,}", " ");
        string = string.toLowerCase();
        return string;
    }
}
