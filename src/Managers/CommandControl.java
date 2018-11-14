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

    GameSettings gameSettings = GameSettings.getInstance();
    private int nextChoiceId;
    private Choice activeChoice;
    private Printable printable;
    private Choosable choosable;

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

        CommandTypes commandType = controlPlayerCommandType(playerCommand, activeChoice);

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

    public void commandController(CommandTypes commandType) {

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

    public int controlMovementCommand(PlayerCommand playerCommand, Choice activeChoice) {
        int nextChoiceId = gameSettings.getMovementCommandArchive().get(playerCommand.getPlayerCommand());
        activeChoice.getAvailableMovementCommands().containsKey(playerCommand.getPlayerCommand());
        System.out.println(nextChoiceId);
        return nextChoiceId;
    }
}
