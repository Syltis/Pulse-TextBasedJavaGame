package Managers;

import Gameplay.GameSettings;
import Interfaces.Choosable;
import Interfaces.Playable;
import Interfaces.Printable;
import Models.Choice;
import Models.MovementCommand;
import Models.PlayerCommand;

/*
Has edited this:
- Kristoffer


- This class receives the players command and the activeChoice, figures out the
command type and processes the command. It uses the lists of available commands from
the activeChoice and from gameSettings.

*/

public class CommandControl {

    private final Printable printable;
    private final Choosable choosable;
    private final Playable playable;
    private final GameSettings gameSettings = GameSettings.getInstance();
    private enum CommandTypeEnum {
        MOVEMENTCOMMAND,
        ACTIONCOMMAND,
        COMBATCOMMAND,
        NOMATCH
    }

    public CommandControl(PlayerCommand playerCommand, Choice activeChoice, Printable printable, Choosable choosable, Playable playable)
    {
        this.printable = printable;
        this.choosable = choosable;
        this.playable = playable;
        commandController(findPlayerCommandType(playerCommand, activeChoice), activeChoice, playerCommand);
    }

    private void commandController(CommandTypeEnum commandType, Choice activeChoice, PlayerCommand playerCommand) {
        String nextChoiceId = null;
        switch (commandType) {
            // Check the playercommand with the activeChoice-object, and compare their nextChoiceId's to get the matching object
            case MOVEMENTCOMMAND:
                for (MovementCommand aMovementCommand: activeChoice.getAvailableMovementCommands()) {
                    if (aMovementCommand.getMovementCommand().equals(playerCommand.getPlayerCommand())) {
                        for (MovementCommand anotherMovementCommand:gameSettings.getMovementCommandBank()) {
                            if (aMovementCommand.getNextChoiceId().equals(anotherMovementCommand.getNextChoiceId())) {
                                nextChoiceId = aMovementCommand.getNextChoiceId();
                            }
                        }
                    }
                }
                // Gives error if its is a viable command, but not for that Choice.
                if (nextChoiceId != null) {
                    printable.printCommandToGameArea(playerCommand.getPlayerCommand());
                    printable.clearSideBarArea();
                    choosable.nextChoice(nextChoiceId);
                } else
                    printable.printResponseToLog("What do you mean?");
                break;

            case ACTIONCOMMAND:
                /* TODO action logic
                    Inventory: Instead of having key in the room in the desc in JSON,
                Have a container to search, where yo get the item if you don't have it already.
                */
                break;

            case COMBATCOMMAND:
                // TODO combat logic
                break;

            case NOMATCH:
                printable.printResponseToLog("What does '" + playerCommand.getPlayerCommand() + "' mean?");
                break;
        }
    }

    private CommandTypeEnum findPlayerCommandType(PlayerCommand playerCommand, Choice activeChoice) {
        // Check if the command exists in gameSettings and in the activechoice
        for (MovementCommand aMovementCommand: gameSettings.getMovementCommandBank()) {
            if (aMovementCommand.getMovementCommand().equals(playerCommand.getPlayerCommand()))
                return CommandTypeEnum.MOVEMENTCOMMAND;
        }
        if (gameSettings.getActionCommandBank().contains(playerCommand.getPlayerCommand())
                && activeChoice.getAvailableActionCommands().contains(playerCommand.getPlayerCommand())) {
            return CommandTypeEnum.ACTIONCOMMAND;
        }
        else if (gameSettings.getActionCommandBank().contains(playerCommand.getPlayerCommand())
                && activeChoice.getAvailableCombatCommands().contains(playerCommand.getPlayerCommand())) {
            return CommandTypeEnum.COMBATCOMMAND;
        }
        else
            return CommandTypeEnum.NOMATCH;
    }
}