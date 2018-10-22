package Helpers;

import GUI.GameWindow;

import java.util.ArrayList;

public class PlayerInput {

    // TODO: Method: We'll need to get the game output [strings/text object] from the gameplay.
    // TODO: Should this class be static?
    //       Then we check them with the player commands. Preferably in its own class. -kris
    ArrayList<String> commandList;
    GameWindow gameWindow;

    // Called in gameWindow when given user input.
    public void receiveCommand(String command) {
        command = cleanString(command);
        Command commandList = new Command();
        commandList.setCommandList(splitCommand(command));
    }

    // Makes an arrayList, either with one word or two.
    // Clean the String before using this!
    // TODO Should this just set the commandList and not return it
    public ArrayList<String> splitCommand(String command) {
        commandList = new ArrayList<>();
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
        string = string.replaceAll("[-!@#$%^&*(),.?\":{}|<>0-9+/']+", "");
        string = string.replaceAll("[ ]{2,}", " ");
        string = string.toLowerCase();
        return string;
    }

    public void printToLog(String string) {
        gameWindow.printToLog(string);
    }

    public void printToGameArea(String string) {
        gameWindow.printToGameArea(string);
    }
}
