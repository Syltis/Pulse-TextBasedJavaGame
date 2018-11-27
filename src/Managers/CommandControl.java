package Managers;

import Gameplay.GameSettings;
import Interfaces.Choosable;
import Interfaces.Playable;
import Interfaces.Printable;
import Models.MovementCommand;
import Models.PlayerCommand;
import Models.Scenario;

import java.util.List;

/*
Has edited this:
- Kristoffer


- This class receives the players command and the activeScenario, figures out the
command type and processes the command. It uses the lists of available commands from
the activeScenario and from gameSettings.

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

    public CommandControl(PlayerCommand playerCommand, Scenario activeScenario, Printable printable, Choosable choosable, Playable playable)
    {
        this.printable = printable;
        this.choosable = choosable;
        this.playable = playable;
        // Find out commands type and send it to commandController
        commandController(findPlayerCommandType(playerCommand, activeScenario), activeScenario, playerCommand);
    }

    private void commandController(CommandTypeEnum commandType, Scenario activeScenario, PlayerCommand playerCommand) {
        String nextScenarioId = null;
        switch (commandType) {
            // Check the playercommand with the activeScenario-object, and compare their nextScenarioId's to get the matching object
            case MOVEMENTCOMMAND:
                for (MovementCommand aMovementCommand: activeScenario.getAvailableMovementCommands()) {
                    if (aMovementCommand.getMovementCommand().equals(playerCommand.getPlayerCommand())) {
                        for (MovementCommand anotherMovementCommand:gameSettings.getMovementCommandBank()) {
                            if (aMovementCommand.getNextScenarioId().equals(anotherMovementCommand.getNextScenarioId())) {
                                nextScenarioId = aMovementCommand.getNextScenarioId();
                            }
                        }
                    }
                }
                // Gives error if its is a viable command, but not for that Scenario.
                if (nextScenarioId == null) {
                    printable.printResponseToLog("What does '" + playerCommand.getPlayerCommand() + "' even mean?");
                    break;
                }
                // Next scenario
                printable.printCommandToGameArea(playerCommand.getPlayerCommand());
                // Clearing of sidebar must be done here and not in nextScenario(), though the new values are sent from there
                printable.clearSideBarArea();
                choosable.nextScenario(nextScenarioId);
                break;

            case ACTIONCOMMAND:
                if (playerCommand.getPlayerCommand().equals("inventory")) {
                    printable.printInventoryToGameArea(playable.getInventory());
                    break;
                }
                // Check if two words
                if (playerCommand.getPlayerCommand().contains(" ")) {
                    List<String> splitCommand = StringUtilities.splitCommand(playerCommand.getPlayerCommand());
                    System.out.println(splitCommand.toString());
                }
                else printable.printResponseToLog("What does '" + playerCommand.getPlayerCommand() + "' even mean??");
                break;

            case COMBATCOMMAND:
                // TODO combat logic
                break;

            case NOMATCH:
                printable.printResponseToLog("What does '" + playerCommand.getPlayerCommand() + "' even mean?");
                break;
        }
    }

    // Decide what type of command the player has entered
    private CommandTypeEnum findPlayerCommandType(PlayerCommand playerCommand, Scenario activeScenario) {
        // Check if the command exists in gameSettings and in the activechoice
        for (MovementCommand aMovementCommand: gameSettings.getMovementCommandBank()) {
            if (aMovementCommand.getMovementCommand().equals(playerCommand.getPlayerCommand()))
                return CommandTypeEnum.MOVEMENTCOMMAND;
        }
        if (gameSettings.getActionCommandBank().contains(playerCommand.getPlayerCommand())
                && activeScenario.getAvailableActionCommands().contains(playerCommand.getPlayerCommand())) {
            return CommandTypeEnum.ACTIONCOMMAND;
        }
        else if (gameSettings.getActionCommandBank().contains(playerCommand.getPlayerCommand())
                && activeScenario.getAvailableCombatCommands().contains(playerCommand.getPlayerCommand())) {
            return CommandTypeEnum.COMBATCOMMAND;
        }
        else
            return CommandTypeEnum.NOMATCH;
    }
}