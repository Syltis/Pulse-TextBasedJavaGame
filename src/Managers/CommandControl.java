package Managers;

import Gameplay.GameSettings;
import Interfaces.Choosable;
import Interfaces.Printable;
import Models.Choice;
import Models.MovementCommand;
import Models.PlayerCommand;

/*
Has edited this:
- Kristoffer
*/

public class CommandControl {

    private final Printable printable;
    private final Choosable choosable;
    private final GameSettings gameSettings = GameSettings.getInstance();
    private enum CommandTypeEnum {
        MOVEMENTCOMMAND,
        ACTIONCOMMAND,
        COMBATCOMMAND,
        NOMATCH
    }

    public CommandControl(PlayerCommand playerCommand, Choice activeChoice, Printable printable, Choosable choosable) {
        this.printable = printable;
        this.choosable = choosable;

        // Get right commandType-enum
        // Send to the right 'controller' for processing
        commandController(controlPlayerCommandType(playerCommand, activeChoice), activeChoice, playerCommand);
    }

    private void commandController(CommandTypeEnum commandType, Choice activeChoice, PlayerCommand playerCommand) {
        String nextChoiceId = null;

        switch (commandType) {
            case MOVEMENTCOMMAND:
                for (MovementCommand aMovementCommand: activeChoice.getAvailableMovementCommands()) {
                    if (aMovementCommand.getMovementCommand().equals(playerCommand.getPlayerCommand())) {
                        nextChoiceId = aMovementCommand.getNextChoiceId();
                    }
                }
                printable.printCommandToGameArea(playerCommand.getPlayerCommand());
                printable.clearSideBarArea();
                choosable.nextChoice(nextChoiceId);
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

    private CommandTypeEnum controlPlayerCommandType(PlayerCommand playerCommand, Choice activeChoice) {
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
