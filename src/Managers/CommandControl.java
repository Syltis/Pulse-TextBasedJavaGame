package Managers;

import Gameplay.GameSettings;
import Interfaces.IActiveScenario;
import Interfaces.IPlayerBeing;
import Interfaces.IGameWindowPrinter;
import Models.*;

/*
HHas edited this:
- Kristoffer
*/

/*
- This class receives the players command and the activeScenario, figures out the
command type and processes the command. It uses the lists of available commands from
the activeScenario and from gameSettings.

*/

public class CommandControl {

    private final IGameWindowPrinter IGameWindowPrinter;
    private final IActiveScenario IActiveScenario;
    private final IPlayerBeing IPlayerBeing;
    private final GameSettings gameSettings = GameSettings.getInstance();
    private enum CommandTypeEnum {
        MOVEMENTCOMMAND,
        ACTIONCOMMAND,
        ITEMCOMMAND,
        COMBATCOMMAND,
        NOMATCH
    }

    public CommandControl(String playerCommand, Scenario activeScenario, IGameWindowPrinter IGameWindowPrinter, IActiveScenario IActiveScenario, IPlayerBeing IPlayerBeing)
    {
        this.IGameWindowPrinter = IGameWindowPrinter;
        this.IActiveScenario = IActiveScenario;
        this.IPlayerBeing = IPlayerBeing;
        // Find out command type and send it to commandControl-method
        commandControl(findPlayerCommandType(playerCommand), activeScenario, playerCommand);
    }

    // TODO Move these pieces of logic to their own class/method
    // TODO Get rid of the PlayerCommand Object
    private void commandControl(CommandTypeEnum commandType, Scenario activeScenario, String playerCommand) {
        switch (commandType) {
            // Check the playercommand with the activeScenario-object, and compare their result's to get the matching object
            case MOVEMENTCOMMAND:
                String result = null;
                for (MovementCommand aMovementCommand: activeScenario.getAvailableMovementCommands()) {
                    if (aMovementCommand.getCommand().equals(playerCommand)) {
                        for (MovementCommand anotherMovementCommand:gameSettings.getMovementCommandBank()) {
                            if (aMovementCommand.getResult().equals(anotherMovementCommand.getResult())) {
                                result = aMovementCommand.getResult();
                            }
                        }
                    }
                }
                if (result == null) {
                    IGameWindowPrinter.printResponseToLog("What does '" + playerCommand + "' even mean?");
                    break;
                }
                IGameWindowPrinter.printToGameArea(playerCommand, true);
                IGameWindowPrinter.printToGameArea("", false);
                // Clearing of sidebar must be done here and not in nextScenario(), though the new values are sent from there
                IGameWindowPrinter.clearSideBarArea();
                IActiveScenario.nextScenario(result);
                break;

            case ACTIONCOMMAND:
                // Check if player asks for inventory
                String actionResult = null;
                if (playerCommand.equals("inventory")) {
                    IGameWindowPrinter.printInventoryToGameArea(IPlayerBeing.getInventory());
                    break;
                }
                for (ActionCommand aActionCommand: activeScenario.getAvailableActionCommands()) {
                    if (aActionCommand.getCommand().equals(playerCommand)) {
                        for (ActionCommand anotherActionCommand:gameSettings.getActionCommandBank()) {
                            if (aActionCommand.getResult().equals(anotherActionCommand.getResult())) {
                                actionResult = aActionCommand.getResult();
                            }
                        }
                    }
                }
                if (actionResult == null) {
                    IGameWindowPrinter.printResponseToLog("What does '" + playerCommand + "' even mean?");
                    break;
                }
                IGameWindowPrinter.printResponseToLog("The actionCommand worked, but we have yet to develop that part of the game.");
                break;

            case ITEMCOMMAND:
                Item item = null;
                for (ItemCommand aItemCommand: activeScenario.getAvailableItemCommands()) {
                    if (aItemCommand.getCommand().equals(playerCommand)) {
                        for (ItemCommand anotherItemCommand: gameSettings.getItemCommandBank()) {
                            if (aItemCommand.getResult().equals(anotherItemCommand.getResult())) {
                                for (Item aItem: gameSettings.getItemBank()) {
                                    if (aItemCommand.getResult().equals(aItem.getItemName())) {
                                        item = aItem;
                                    }
                                }
                            }
                        }
                    }
                }
                if (item == null) {
                    IGameWindowPrinter.printResponseToLog("Hm, what does '" + playerCommand + "' even mean?");
                    break;
                }
                for (Item anItem: IPlayerBeing.getInventory()) {
                    if (item.isUnique() && anItem.equals(item)) {
                        IGameWindowPrinter.printToGameArea("", false);
                        IGameWindowPrinter.printToGameArea("You have already picked up a " + item.getItemName(), true);
                        return;
                    }
                }
                IGameWindowPrinter.printToGameArea("", false);
                IGameWindowPrinter.printToGameArea("A " + item.getItemName() + " was added to the inventory", true);
                IPlayerBeing.addToInventory(item);
                break;

            case COMBATCOMMAND:
                String combatResult = null;
                for (CombatCommand aCombatCommand:activeScenario.getAvailableCombatCommands()) {
                    if (aCombatCommand.getCommand().equals(playerCommand)) {
                        for (CombatCommand anotherCombatCommand:gameSettings.getCombatCommandBank()) {
                            if (aCombatCommand.getResult().equals(anotherCombatCommand.getResult())) {
                                combatResult = aCombatCommand.getResult();
                                EnemyBeing enemy = StringUtilities.getCharacterFromCombatResult(combatResult);

                            }
                        }
                    }
                }
                if (combatResult == null) {
                    IGameWindowPrinter.printResponseToLog("What does '" + playerCommand + "' even mean?");
                    break;
                }
                // TODO new Battle(IPlayerBeing.getPlayerCharacter, enemy)
                break;

            case NOMATCH:
                IGameWindowPrinter.printResponseToLog("What does '" + playerCommand + "' even mean?");
                break;
        }
    }

    // Decide what type of command the player has entered
    private CommandTypeEnum findPlayerCommandType(String playerCommand) {
        // Check if the command exists in gameSettings and in the activechoice
        for (MovementCommand aMovementCommand: gameSettings.getMovementCommandBank()) {
            if (aMovementCommand.getCommand().equals(playerCommand))
                return CommandTypeEnum.MOVEMENTCOMMAND;
        }
        for (ActionCommand aActionCommand: gameSettings.getActionCommandBank()) {
            if (aActionCommand.getCommand().equals(playerCommand))
                return CommandTypeEnum.ACTIONCOMMAND;
        }
        for (ItemCommand aItemCommand: gameSettings.getItemCommandBank()) {
            if (aItemCommand.getCommand().equals(playerCommand))
                return CommandTypeEnum.ITEMCOMMAND;
        }
        for (CombatCommand aCombatCommand: gameSettings.getCombatCommandBank()) {
            if (aCombatCommand.getCommand().equals(playerCommand))
                return CommandTypeEnum.COMBATCOMMAND;
        }
        return CommandTypeEnum.NOMATCH;
    }
}