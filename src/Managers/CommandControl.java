package Managers;

import Models.Choice;
import Models.PlayerCommand;

import java.util.ArrayList;

/*
Has edited this:
- Kristoffer
*/

public class CommandControl {

    // Check for command type
    // Returns empty if no match
    public String[] controlPlayerCommand(PlayerCommand playerCommand, Choice choice) {
        String[] output = new String[2];
        ArrayList<String> actionCommandList = choice.getAvailableActionCommands();
        ArrayList<String> commandTargets = choice.getAvailableCommandTargets();
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
