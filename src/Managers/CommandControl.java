package Managers;

import Gameplay.GameSettings;
import Interfaces.Choosable;
import Interfaces.Playable;
import Interfaces.Printable;
import Models.*;

/*
Has edited this:
- Kristoffer
*/

/*
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
        // Find out command type and send it to commandControl-method
        commandControl(findPlayerCommandType(playerCommand, activeScenario), activeScenario, playerCommand);
    }

    // TODO Move these pieces of logic to their own class/method
    private void commandControl(CommandTypeEnum commandType, Scenario activeScenario, PlayerCommand playerCommand) {
        switch (commandType) {
            // Check the playercommand with the activeScenario-object, and compare their nextScenarioId's to get the matching object
            case MOVEMENTCOMMAND:
                String nextScenarioId = null;
                for (MovementCommand aMovementCommand: activeScenario.getAvailableMovementCommands()) {
                    if (aMovementCommand.getMovementCommand().equals(playerCommand.getPlayerCommand())) {
                        for (MovementCommand anotherMovementCommand:gameSettings.getMovementCommandBank()) {
                            if (aMovementCommand.getNextScenarioId().equals(anotherMovementCommand.getNextScenarioId())) {
                                nextScenarioId = aMovementCommand.getNextScenarioId();
                            }
                        }
                    }
                }
                if (nextScenarioId == null) {
                    printable.printResponseToLog("What does '" + playerCommand.getPlayerCommand() + "' even mean?");
                    break;
                }
                printable.printCommandToGameArea(playerCommand.getPlayerCommand());
                // Clearing of sidebar must be done here and not in nextScenario(), though the new values are sent from there
                printable.clearSideBarArea();
                choosable.nextScenario(nextScenarioId);
                break;

            case ACTIONCOMMAND:
                // Check if player asks for inventory
                String actionResult = null;
                if (playerCommand.getPlayerCommand().equals("inventory")) {
                    printable.printInventoryToGameArea(playable.getInventory());
                    break;
                }
                for (ActionCommand aActionCommand:activeScenario.getAvailableActionCommands()) {
                    if (aActionCommand.equals(playerCommand.getPlayerCommand())) {
                        for (ActionCommand anotherActionCommand:gameSettings.getActionCommandBank()) {
                            if (aActionCommand.getActionResult().equals(anotherActionCommand.getActionResult())) {
                                actionResult = aActionCommand.getActionResult();
                            }
                        }
                    }
                }
                if (actionResult == null) {
                    printable.printResponseToLog("What does '" + playerCommand.getPlayerCommand() + "' even mean?");
                    break;
                }
                break;

            case COMBATCOMMAND:
                String combatResult = null;
                for (CombatCommand aCombatCommand:activeScenario.getAvailableCombatCommands()) {
                    if (aCombatCommand.getCombatCommand().equals(playerCommand.getPlayerCommand())) {
                        for (CombatCommand anotherCombatCommand:gameSettings.getCombatCommandBank()) {
                            if (aCombatCommand.getCombatResult().equals(anotherCombatCommand.getCombatResult())) {
                                combatResult = aCombatCommand.getCombatResult();
                                EnemyCharacter enemy = StringUtilities.getCharacterFromCombatResult(combatResult);
                                // TODO new Battle(playable.getBeing, enemy)
                            }
                        }
                    }
                }
                if (combatResult == null) {
                    printable.printResponseToLog("What does '" + playerCommand.getPlayerCommand() + "' even mean?");
                    break;
                }
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
        for (ActionCommand aActionCommand: gameSettings.getActionCommandBank()) {
            if (aActionCommand.getActionCommand().equals(playerCommand.getPlayerCommand()))
                return CommandTypeEnum.ACTIONCOMMAND;
        }
        for (CombatCommand aCombatCommand: gameSettings.getCombatCommandBank()) {
            if (aCombatCommand.getCombatCommand().equals(playerCommand.getPlayerCommand()))
                return CommandTypeEnum.COMBATCOMMAND;
        }
        return CommandTypeEnum.NOMATCH;
    }
}