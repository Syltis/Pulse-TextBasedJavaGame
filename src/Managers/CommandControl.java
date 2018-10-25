package Managers;

import DataTransferObjects.PlayerCommand;
import DataTransferObjects.Situation;

import java.util.ArrayList;

//Give this class override constructor-method?
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
    public void controlPlayerCommand(PlayerCommand playerCommand, Situation situation) {

        ArrayList<String> actionCommandList = situation.getAvailableActionCommands();
        ArrayList<String> commandTargets = situation.getAvailableCommandTargets();
        //ArrayList<String>playerCommandList = command.getCommandList();

        for (String actionCommand:actionCommandList) {
            if (actionCommand.equals(playerCommand.getActionCommand())) {
                this.actionCommand = actionCommand;
            }
        }
        for (String commandTarget: commandTargets) {
            if (commandTarget.equals(playerCommand.getCommandTarget())) {
                this.commandTarget = commandTarget;
            }
        }

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
