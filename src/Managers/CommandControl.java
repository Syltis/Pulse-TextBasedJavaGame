package Managers;

import Gameplay.GameSettings;
import Interfaces.Choosable;
import Interfaces.Printable;
import Models.ChoiceV2;
import Models.MovementCommand;
import Models.PlayerCommand;

/*
Has edited this:
- Kristoffer
*/

public class CommandControl {

    private Printable printable;
    private Choosable choosable;
    private GameSettings gameSettings = GameSettings.getInstance();

    private enum CommandTypeEnum {
        MOVEMENTCOMMAND,
        ACTIONCOMMAND,
        COMBATCOMMAND,
        NOMATCH
    }

    public CommandControl(PlayerCommand playerCommand, ChoiceV2 activeChoice, Printable printable, Choosable choosable) {
        this.printable = printable;
        this.choosable = choosable;

        // Get right commandType-enum
        CommandTypeEnum commandType = controlPlayerCommandType(playerCommand, activeChoice);
        commandController(commandType, activeChoice, playerCommand);
    }

    private void commandController(CommandTypeEnum commandType, ChoiceV2 activeChoice, PlayerCommand playerCommand) {
        switch (commandType) {
            case MOVEMENTCOMMAND:
                // Build a method to update the NewGame choiceId, and run that as the next method in newgame
                //String nextChoiceId = activeChoice.getAvailableMovementCommands().get(playerCommand.getPlayerCommand());
                printable.printCommandToGameArea(playerCommand.getPlayerCommand());
                printable.clearSideBarArea();
                //choosable.nextChoice(nextChoiceId);
                break;

            case ACTIONCOMMAND:
                System.out.println("Action-match " + playerCommand.getPlayerCommand());
                break;

            case COMBATCOMMAND:
                System.out.println("Combat-match " + playerCommand.getPlayerCommand());
                break;

            case NOMATCH:
                System.out.print("No match: " + playerCommand.getPlayerCommand() + "\n");
                printable.printResponseToLog("What do you mean?");
                break;
        }
    }

    private CommandTypeEnum controlPlayerCommandType(PlayerCommand playerCommand, ChoiceV2 activeChoice) {
        // Check if the command exists in gameSettings and in the activechoice
        for (MovementCommand aMovementCommand: gameSettings.getMovementCommandArchive()) {
            if (aMovementCommand.getMovementCommand().equals(playerCommand.getPlayerCommand()))
                return CommandTypeEnum.MOVEMENTCOMMAND;
        }
        if (gameSettings.getActionCommandArchive().contains(playerCommand.getPlayerCommand())
                && activeChoice.getAvailableActionCommands().contains(playerCommand.getPlayerCommand())) {
            return CommandTypeEnum.ACTIONCOMMAND;
        }
        else if (gameSettings.getActionCommandArchive().contains(playerCommand.getPlayerCommand())
                && activeChoice.getAvailableCombatCommands().contains(playerCommand.getPlayerCommand())) {
            return CommandTypeEnum.COMBATCOMMAND;
        }
        else
            return CommandTypeEnum.NOMATCH;
    }
}
