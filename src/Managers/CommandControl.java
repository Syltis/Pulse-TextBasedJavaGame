package Managers;

import DataTransferObjects.PlayerCommand;
import DataTransferObjects.Situation;

import java.util.ArrayList;

//Give this class override constructor-method?
public class CommandControl {

    String actionCommand;
    String commandTarget;

    // public CommandControl() { }
    // TODO: How to access this class?
    public void controlPlayerCommand(PlayerCommand command, Situation situation) {

        ArrayList<String> actionCommandList = situation.getAvailableActionCommands();
        ArrayList<String> commandtargets = situation.getAvailableCommandTargets();
        ArrayList<String>playerCommandList = command.getCommandList();

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

        }
    }
}
