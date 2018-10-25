package Managers;

import DataTransferObjects.PlayerCommand;
import GUI.GameWindow;
import java.util.ArrayList;

public class PlayerInput {

    ArrayList<String> commandList;
    ArrayList<String> availableCommandList;
    GameWindow gameWindow;

    // Called in gameWindow when given user input.
    public void receiveCommand(String playerCommand) {
        playerCommand = cleanString(playerCommand);
        PlayerCommand commandList = new PlayerCommand();
        commandList.setCommandList(splitCommand(playerCommand));
    }

    // Makes an arrayList, either with one word or two.
    // Clean the String before using this!
    // TODO Should this just set the commandList and not return it
    public ArrayList<String> splitCommand(String playerCommand) {
        commandList = new ArrayList<>();
        //Check if the resulting string is split by a space, thereby being two words.
        if(playerCommand.matches("\\s+")) {
            String actionCommand = playerCommand.substring(0, playerCommand.indexOf(" "));
            String commandTarget = playerCommand.substring(playerCommand.indexOf(" "), playerCommand.length());
            commandList.add(actionCommand);
            commandList.add(commandTarget);
        }
        else {
            commandList.add(playerCommand);
        }
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

    public void checkIfPlayerInput() {
        // TODO: Confirm player input and report to NewGame.
    }
}
