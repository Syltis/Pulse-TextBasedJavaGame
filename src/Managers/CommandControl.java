package Managers;

import Gameplay.GameSettings;
import Interfaces.Choosable;
import Interfaces.Printable;
import Models.Choice;
import Models.PlayerCommand;

/*
Has edited this:
- Kristoffer
*/

public class CommandControl {

    private Printable printable;
    private Choosable choosable;
    private PlayerCommand playerCommand;
    private GameSettings gameSettings = GameSettings.getInstance();
    private int nextChoiceId;
    private Choice activeChoice;

    enum CommandTypes {
        MOVEMENTCOMMAND,
        ACTIONCOMMAND,
        COMBATCOMMAND,
        NOMATCH
    }

    public CommandControl() {

    }

    public CommandControl(PlayerCommand playerCommand, Choice activeChoice, Printable printable, Choosable choosable) {
        this.printable = printable;
        this.choosable = choosable;
        this.activeChoice = activeChoice;
        this.playerCommand = playerCommand;

        CommandTypes commandType = controlPlayerCommandType(playerCommand, activeChoice);
        commandController(commandType);
    }

    public void commandController(CommandTypes commandType) {
        switch (commandType) {
            case MOVEMENTCOMMAND:
                // Build a method to update the NewGame choiceId, and run that as the next method in newgame
                this.nextChoiceId = controlMovementCommand(playerCommand, activeChoice);
                choosable.nextChoice(this.nextChoiceId);
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

    public CommandTypes controlPlayerCommandType(PlayerCommand playerCommand, Choice activeChoice) {
        if (gameSettings.getMovementCommandArchive().containsKey(playerCommand.getPlayerCommand())) {
            return CommandTypes.MOVEMENTCOMMAND;
        } else if (activeChoice.getAvailableActionCommands().contains(playerCommand.getPlayerCommand() + "\n")) {
            return CommandTypes.ACTIONCOMMAND;
        } else if (activeChoice.getAvailableCombatCommands().contains(playerCommand.getPlayerCommand() + "\n")) {
            return CommandTypes.COMBATCOMMAND;
        } else
            return CommandTypes.NOMATCH;
    }

    // TODO this doesnt work right
    public int controlMovementCommand(PlayerCommand playerCommand, Choice activeChoice) {
        int nextChoiceId = gameSettings.getMovementCommandArchive().get(playerCommand.getPlayerCommand());
        System.out.println(nextChoiceId);
        System.out.println(activeChoice.getId());

        return nextChoiceId;
    }
}
