package Managers;

import Gameplay.GameSettings;
import Interfaces.INewGame;
import Interfaces.IPlayer;
import Interfaces.IGameWindowPrint;
import Models.*;

/*
- This class receives the players command and the activeScenario, figures out the
command type and processes the command. It uses the lists of available commands from
the activeScenario and from gameSettings to confirm the commands and get the result.

*/

public class CommandControl {

    private final IGameWindowPrint IGameWindowPrint;
    private final INewGame INewGame;
    private final IPlayer IPlayer;
    private final GameSettings gameSettings = GameSettings.getInstance();
    private Scenario activeScenario;
    private String playerCommand;
    private enum CommandTypeEnum {
        MOVEMENTCOMMAND,
        ACTIONCOMMAND,
        ITEMCOMMAND,
        COMBATCOMMAND,
        NOMATCH
    }

    public CommandControl(Scenario activeScenario, String playerCommand, IGameWindowPrint IGameWindowPrint, INewGame INewGame, IPlayer IPlayer)
    {
        this.IGameWindowPrint = IGameWindowPrint;
        this.INewGame = INewGame;
        this.IPlayer = IPlayer;
        this.activeScenario = activeScenario;
        this.playerCommand = playerCommand;
        // Find out command type and send it to controlCommand-method
        controlCommand(findPlayerCommandType(playerCommand), playerCommand);
    }

    // TODO Move these pieces of logic to their own class/method
    private void controlCommand(CommandTypeEnum commandType, String playerCommand) {
        switch (commandType) {
            // Check the playercommand with the activeScenario-object, and compare their result's to get the matching object
            case MOVEMENTCOMMAND: ControlMovementCommand(); break;

            case ACTIONCOMMAND: ControlActionCommand(); break;

            case ITEMCOMMAND: ControlItemCommand(); break;

            case COMBATCOMMAND: ControlCombatCommand(); break;

            case NOMATCH:
                IGameWindowPrint.printResponseToLog("What does '" + playerCommand + "' even mean?");
                break;
        }
    }

    // Decide what type of command the player has entered, using the different lists from GameSettings
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

    private void ControlMovementCommand() {
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
            IGameWindowPrint.printResponseToLog("What does '" + playerCommand + "' even mean?");
            return;
        }
        // Print command to GameArea
        IGameWindowPrint.printToGameArea(playerCommand, true);
        IGameWindowPrint.printToGameArea("", false);
        // Clearing of sidebar must be done here and not in nextScenario(), though the new values are sent from there
        IGameWindowPrint.clearSideBarArea();
        // nextScenario() takes the new Scenario ID and displays the new Scenario
        INewGame.nextScenario(result);
    }

    private void ControlActionCommand() {
        // Check if player asks for inventory
        String actionResult = null;
        if (playerCommand.equals("inventory") || playerCommand.equals("i")) {
            // Display current inventory
            IGameWindowPrint.printInventoryToGameArea(IPlayer.getInventory());
            return;
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
            IGameWindowPrint.printResponseToLog("What does '" + playerCommand + "' even mean?");
            return;
        }
        IGameWindowPrint.printResponseToLog("The actionCommand worked, but we have yet to develop that part of the game.");
    }

    private void ControlItemCommand() {
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
            IGameWindowPrint.printResponseToLog("Hm, what does '" + playerCommand + "' even mean?");
            return;
        }
        // Check if item is marked an unique and if layer already has one
        for (Item anItem: IPlayer.getInventory()) {
            if (item.isUnique() && anItem.equals(item)) {
                IGameWindowPrint.printToGameArea("", false);
                IGameWindowPrint.printToGameArea("You have already picked up a " + item.getItemName(), true);
                return;
            }
        }
        // Print confirmatin to the GameArea and add item to inventory
        IGameWindowPrint.printToGameArea("", false);
        IGameWindowPrint.printToGameArea("A " + item.getItemName() + " was added to the inventory", true);
        IPlayer.addToInventory(item);
    }

    private void ControlCombatCommand() {
        String combatResult = null;
        for (CombatCommand aCombatCommand:activeScenario.getAvailableCombatCommands()) {
            if (aCombatCommand.getCommand().equals(playerCommand)) {
                for (CombatCommand anotherCombatCommand:gameSettings.getCombatCommandBank()) {
                    if (aCombatCommand.getResult().equals(anotherCombatCommand.getResult())) {
                        combatResult = aCombatCommand.getResult();
                        Enemy enemy = StringUtilities.generateEnemyFromCombatResult(combatResult);
                    }
                }
            }
        }
        if (combatResult == null) {
            IGameWindowPrint.printResponseToLog("What does '" + playerCommand + "' even mean?");
            return;
        }
        // TODO Instantiate battle, e.g. new Battle(Player, Enemy);
    }
}