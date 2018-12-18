package managers;

import gameplay.GameSettings;
import interfaces.ICommandController;
import interfaces.IGameWindowPrint;
import interfaces.INewGame;
import interfaces.IPlayer;
import models.ActionCommand;
import models.Item;
import models.Scenario;

public class ActionCommandControl implements ICommandController {

	public void ControlCommand(Scenario activeScenario, String playerCommand, GameSettings gameSettings, INewGame INewGame, IGameWindowPrint IGameWindowPrint, IPlayer IPlayer) {
		// Check if player asks for inventory
		String actionResult = null;
		if (playerCommand.equals("inventory") || playerCommand.equals("i")) {
			// Display current inventory
			IGameWindowPrint.printInventoryToGameArea(IPlayer.getInventory());
			return;
		}
		// Checks if player has tried to use an item
		if (StringUtilities.commandIsUse(playerCommand)) {
			System.out.println("is Use");
			String [] lineSplit = playerCommand.split("\\s+");
			for (Item aItem: gameSettings.getItemBank()) {
				if (aItem.getItemName().equalsIgnoreCase(lineSplit[1])) {

					// TODO check towards players inventory
					System.out.println(aItem.getItemName());
				}
			}
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
			IGameWindowPrint.printResponseToLog("What does actionCommand:'" + playerCommand + "' even mean?");
			return;
		}
		IGameWindowPrint.printResponseToLog("The actionCommand worked, but we have yet to develop that part of the game.");
	}
}
