package managers;

import gameplay.GameSettings;
import interfaces.ICommandControl;
import interfaces.INewGame;
import interfaces.IPlayer;
import interfaces.IGameWindowPrint;
import models.*;

/*
- This class receives the players command and the activeScenario, figures out the
command type and processes the command. It uses the lists of available commands from
the activeScenario and from gameSettings to confirm the commands and get the result.

*/

public class CommandControlHub {

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

    public CommandControlHub(
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

    // Decide what type of command the player has entered, using the different lists from GameSettings
    private CommandTypeEnum findPlayerCommandType() {
        // Check if the command exists in gameSettings and in the activechoice
        for (MovementCommand aMovementCommand: gameSettings.getMovementCommandBank()) {
            if (aMovementCommand.getCommand().equals(playerCommand))
                return CommandTypeEnum.MOVEMENTCOMMAND;
        }
        if (StringUtilities.commandIsUse(playerCommand)) {
            return CommandTypeEnum.ACTIONCOMMAND;
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

    // TODO Move these pieces of logic to their own class/method
    private void controlCommand(CommandTypeEnum commandType) {
        switch (commandType) {
            // Check the playercommand with the activeScenario-object, and compare their result's to get the matching object
            case MOVEMENTCOMMAND:
                ICommandControl movementCommandCtrl = new MovementCommandControl();
                movementCommandCtrl.ControlCommand(
                        activeScenario, playerCommand,
                        gameSettings, INewGame,
                        IGameWindowPrint, IPlayer
                );
                break;

            case ACTIONCOMMAND:
                ICommandControl actionCommandCtrl = new ActionCommandControl();
                actionCommandCtrl.ControlCommand(
                        activeScenario, playerCommand,
                        gameSettings, INewGame,
                        IGameWindowPrint, IPlayer
                );
                break;

            case ITEMCOMMAND:
                ICommandControl itemCommandCtrl = new ItemCommandControl();
                itemCommandCtrl.ControlCommand(
                        activeScenario, playerCommand,
                        gameSettings, INewGame,
                        IGameWindowPrint, IPlayer
                );
                break;

            case COMBATCOMMAND:
                ICommandControl combatCommandCtrl = new CombatCommandControl();
                combatCommandCtrl.ControlCommand(
                        activeScenario, playerCommand,
                        gameSettings, INewGame,
                        IGameWindowPrint, IPlayer
                );
                break;

            case NOMATCH:
                IGameWindowPrint.printResponseToLog("What does '" + playerCommand + "' even mean?");
                break;
        }
    }
}