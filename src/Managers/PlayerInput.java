package Managers;

/*
Has edited this:
- Kristoffer
*/

import Interfaces.Choosable;
import Interfaces.Playable;
import Interfaces.Printable;
import Models.PlayerCommand;

import java.util.ArrayList;

public class PlayerInput {

    // TODO Figure out if this class can be removed

    private final Printable printable;
    private final Choosable choosable;
    private final Playable playable;

    public PlayerInput(Printable printable, Choosable choosable, Playable playable)
    {
        this.printable = printable;
        this.choosable = choosable;
        this.playable = playable;
    }

    public void receiveCommand(String input) {
        PlayerCommand playerCommand = new PlayerCommand(cleanString(input));
        new CommandControl(playerCommand,choosable.getActiveScenario(), printable, choosable, playable);
    }

    // Separates string by whitespace
    // Clean the String before using this!
    public ArrayList<String> splitCommand(String input) {
        String actionCommand = input.substring(0, input.indexOf(" "));
        String commandTarget = input.substring(input.indexOf(" "), input.length());
        ArrayList<String> splitCommandList = new ArrayList<>();
        splitCommandList.add(actionCommand);
        splitCommandList.add(commandTarget);
        return splitCommandList;
    }

    // Trim string, turn double+ whitespace into single, lowercase and remove everything but letters.
    private static String cleanString(String string) {
        string = string.trim();
        string = string.replaceAll("[-!@#$%^&*(),.?\":{}|<>0-9+/']+", "");
        string = string.replaceAll("[ ]{2,}", " ");
        string = string.toLowerCase();
        return string;
    }

    /* private void checkSwearing(PlayerCommand playerCommand) {
        for (String aSwearWord:GameSettings.getSwearWords()) {
            if (aSwearWord.equalsIgnoreCase(playerCommand.getPlayerCommand()) {

            }
    } */
}
