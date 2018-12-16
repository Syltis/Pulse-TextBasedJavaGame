package Managers;

import Gameplay.GameSettings;
import Interfaces.ICommandController;
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

    public CommandControl(
            Scenario activeScenario,
            String playerCommand,
            IGameWindowPrint IGameWindowPrint,
            INewGame INewGame,
            IPlayer IPlayer
    )
    {
        this.IGameWindowPrint = IGameWindowPrint;
        this.INewGame = INewGame;
        this.IPlayer = IPlayer;
        this.activeScenario = activeScenario;
        this.playerCommand = playerCommand;
        // Find out command type and send it to controlCommand-method
        controlCommand(findPlayerCommandType());
    }

    // TODO Move these pieces of logic to their own class/method
    private void controlCommand(CommandTypeEnum commandType) {
        switch (commandType) {
            // Check the playercommand with the activeScenario-object, and compare their result's to get the matching object
            case MOVEMENTCOMMAND:
                ICommandController movementCommandCtrl = new MovementCommandCtrl();
                movementCommandCtrl.ControlCommand(
                        activeScenario,
                        playerCommand,
                        gameSettings,
                        INewGame,
                        IGameWindowPrint,
                        IPlayer
                );
                break;

            case ACTIONCOMMAND:
                ICommandController actionCommandCtrl = new ActionCommandCtrl();
                actionCommandCtrl.ControlCommand(
                        activeScenario,
                        playerCommand,
                        gameSettings,
                        INewGame,
                        IGameWindowPrint,
                        IPlayer
                );
                break;

            case ITEMCOMMAND:
                ICommandController itemCommandCtrl = new ItemCommandCtrl();
                itemCommandCtrl.ControlCommand(
                        activeScenario,
                        playerCommand,
                        gameSettings,
                        INewGame,
                        IGameWindowPrint,
                        IPlayer
                );
                break;

            case COMBATCOMMAND:
                ICommandController combatCommandCtrl = new CombatCommandCtlr();
                combatCommandCtrl.ControlCommand(
                        activeScenario,
                        playerCommand,
                        gameSettings,
                        INewGame,
                        IGameWindowPrint,
                        IPlayer
                );
                break;

            case NOMATCH:
                IGameWindowPrint.printResponseToLog("What does '" + playerCommand + "' even mean?");
                break;
        }
    }

    // Decide what type of command the player has entered, using the different lists from GameSettings
    private CommandTypeEnum findPlayerCommandType() {
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