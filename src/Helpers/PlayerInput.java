package Helpers;

import java.util.ArrayList;

public class PlayerInput {

    // TODO: Method: We'll need to get the game output [strings/text object] from the gameplay.
    //       Then we check them with the player commands. Either here og in it's own class(es). -kris

    ArrayList<String> commandList;

    public ArrayList<String> getCommandList() { return commandList; }

    // Validates input. Makes an arrayList, either with one word or two.
    public ArrayList<String> receiveCommand(String command) {
        commandList = new ArrayList<String>();
        cleanString(command);
        //Check if the resulting string is split by a space, thereby being two words.
        if(command.matches("\\s+")) {
            String actionCommand = command.substring(0, command.indexOf(" "));
            String commandTarget = command.substring(command.indexOf(" "), command.length());
            commandList.add(actionCommand);
            commandList.add(commandTarget);
        }
        else {
            commandList.add(command);
        }
        return commandList;
    }

    // Trim string, turn double+ whitespace into single, lowercase and remove all other than letters.
    public String cleanString(String string) {
        string = string.trim();
        string.replaceAll("[!@#$%^&*(),.?\":{}|<>0-9+/']+", "");
        string.replaceAll("/\\s{2,}/", " ");
        string.toLowerCase();
        return string;
    }

}
