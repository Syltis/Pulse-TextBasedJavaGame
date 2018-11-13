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

    enum CommandTypes {
        MOVEMENTCOMMAND,
        ACTIONCOMMAND,
        COMBATCOMMAND,
        NOMATCH
    }

    public CommandTypes controlPlayerCommandType(PlayerCommand playerCommand, ChoiceV2 choice) {

        if (gameSettings.getMovementCommandArchive().containsKey(playerCommand.getCommand())) {
            System.out.println("Movement-match: " + playerCommand.getCommand() + "\n");
            return CommandTypes.MOVEMENTCOMMAND;
        } else if (choice.getAvailableActionCommands().contains(playerCommand.getCommand() + "\n")) {
            System.out.println("Action-match " + playerCommand.getCommand());
            return CommandTypes.ACTIONCOMMAND;
        } else if (choice.getAvailableCombatCommands().contains(playerCommand.getCommand() + "\n")) {
            System.out.println("Combat-match " + playerCommand.getCommand());
            return CommandTypes.COMBATCOMMAND;
        } else
            System.out.print("No match: " + playerCommand.getCommand() + "\n");
            return CommandTypes.NOMATCH;
    }
}
