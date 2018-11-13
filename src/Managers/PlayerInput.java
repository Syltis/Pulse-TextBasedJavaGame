package Managers;

/*
Has edited this:
- Kristoffer
*/

import Interfaces.Choosable;
import Interfaces.Printable;
import Models.PlayerCommand;

import java.util.ArrayList;

public class PlayerInput {

    private Printable printable;
    private Choosable choosable;
    private ArrayList<String> splitCommandList;
    private PlayerCommand playerCommand;


    public PlayerInput() {};

    public PlayerInput(Printable printable, Choosable choosable) {

        this.printable = printable;
        this.choosable = choosable;
    }

    public void receiveCommand(String input) {
        input = cleanString(input);
        playerCommand = new PlayerCommand();

        // Check if it contains two words. Should not be any whitespace left after cleaning if it's one word.
        if(input.matches("\\s+")) {
            splitCommandList = splitCommand(input);
            playerCommand = new PlayerCommand(splitCommandList.get(0), splitCommandList.get(1));
        }
        else {
            // This is for the optional shortcut functionality
            playerCommand = new PlayerCommand(input, " ");
        }
        choosable.setActivePlayerCommand(playerCommand);
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
