package Managers;

import Gameplay.GameSettings;
import Models.ChoiceV2;
import Models.PlayerCommand;

/*
Has edited this:
- Kristoffer
*/

public class CommandControl {

    GameSettings gameSettings = GameSettings.getInstance();
    int nextChoiceId;

    enum CommandTypes {
        MOVEMENTCOMMAND,
        ACTIONCOMMAND,
        COMBATCOMMAND,
        NOMATCH
    }

    public CommandControl() {

    }

    public CommandControl(PlayerCommand playerCommand, ChoiceV2 activeChoice) {
        CommandTypes commandType = controlPlayerCommandType(playerCommand, activeChoice);

        switch (commandType) {
            case MOVEMENTCOMMAND:
                this.nextChoiceId = controlMovementCommand(playerCommand);
                System.out.println("Movement-match:" + playerCommand.getCommand() + "Key: " + this.nextChoiceId + "\n");
                break;

            case ACTIONCOMMAND:
                System.out.println("Action-match " + playerCommand.getCommand());
                break;

            case COMBATCOMMAND:
                System.out.println("Combat-match " + playerCommand.getCommand());
                break;

            case NOMATCH:
                System.out.print("No match: " + playerCommand.getCommand() + "\n");
                break;
        }
    }

    public CommandTypes controlPlayerCommandType(PlayerCommand playerCommand, ChoiceV2 activeChoice) {

        if (gameSettings.getMovementCommandArchive().containsKey(playerCommand.getCommand())) {
            return CommandTypes.MOVEMENTCOMMAND;
        } else if (activeChoice.getAvailableActionCommands().contains(playerCommand.getCommand() + "\n")) {
            return CommandTypes.ACTIONCOMMAND;
        } else if (activeChoice.getAvailableCombatCommands().contains(playerCommand.getCommand() + "\n")) {
            return CommandTypes.COMBATCOMMAND;
        } else
            return CommandTypes.NOMATCH;
    }

    public int controlMovementCommand(PlayerCommand playerCommand) {
        int nextChoiceId = gameSettings.getMovementCommandArchive().get(playerCommand.getCommand());
        System.out.println(nextChoiceId);
        return nextChoiceId;
    }
}
