package Managers;

import DataTransferObjects.PlayerCommand;
import DataTransferObjects.Situation;
import java.util.ArrayList;

/*
Has edited this:
- Kristoffer
*/

public class CommandControl {

    String actionCommand;
    String commandTarget;
    PlayerCommand command;
    Situation situation;

    public void setCommand(PlayerCommand command) { this.command = command; }

    public  void setSituation(Situation situation) { this.situation = situation; }

    /* TODO: UNTESTED CLASS. How to access this class from playerinput with situation
        TODO: Instantiate JSONParsing here!
            - Kris
    */

    // Earlier this set the objects actionCommand and commandTarget and returned void
    // Currently return a String[1]. This is better if this method will be called in another class. -Kris
    // Returns empty if no match
    public String[] controlPlayerCommand(PlayerCommand playerCommand, Situation situation) {
        String[] output = new String[1];
        ArrayList<String> actionCommandList = situation.getAvailableActionCommands();
        ArrayList<String> commandTargets = situation.getAvailableCommandTargets();
        //ArrayList<String>playerCommandList = command.getCommandList();

        for (String actionCommand:actionCommandList) {
            if (actionCommand.equals(playerCommand.getActionCommand())) {
                // this.actionCommand = actionCommand;
                output[0] = actionCommand;
            }
        }
        for (String commandTarget: commandTargets) {
            if (commandTarget.equals(playerCommand.getCommandTarget())) {
                // this.commandTarget = commandTarget;
                output[1] = commandTarget;
            }
        }
        return output;

        // Earlier version where command gave the player's commands through an ArrayList.
        /*
        for (String playerCommand:playerCommandList) {
            for (String actionCommand: actionCommandList) {
                if (playerCommand.equals(actionCommand)) {
                    this.actionCommand = actionCommand;
                }
            }
        }
        for (String playerCommand:playerCommandList) {
            for (String commandTarget: commandtargets) {
                if (playerCommand.equals(commandTarget)) {
                    this.commandTarget = commandTarget;
                }

            }

        }*/
    }
}
