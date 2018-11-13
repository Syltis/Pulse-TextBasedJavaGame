package Managers;

/*
Has edited this:
- Kristoffer
*/

import Gameplay.GameSettings;
import Interfaces.Choosable;
import Interfaces.Printable;
import Models.PlayerCommand;

import java.util.ArrayList;

public class PlayerInput {

    private Printable printable;
    private Choosable choosable;
    private ArrayList<String> splitCommandList;
    private PlayerCommand playerCommand;
    private GameSettings gameSettings;

    public PlayerInput() {}

    public PlayerInput(Printable printable, Choosable choosable) {

        this.printable = printable;
        this.choosable = choosable;
    }

    public void receiveCommand(String input) {
        playerCommand = new PlayerCommand(cleanString(input));
        choosable.setActivePlayerCommand(playerCommand);
        new CommandControl(playerCommand, choosable.getActiveChoice());
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
