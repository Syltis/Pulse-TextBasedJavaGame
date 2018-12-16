package Managers;

import Gameplay.GameSettings;
import Interfaces.ICommandController;
import Interfaces.IGameWindowPrint;
import Interfaces.INewGame;
import Interfaces.IPlayer;
import Models.ActionCommand;
import Models.Scenario;

public class ActionCommandCtrl implements ICommandController {

	public void ControlCommand(Scenario activeScenario, String playerCommand, GameSettings gameSettings, INewGame INewGame, IGameWindowPrint IGameWindowPrint, IPlayer IPlayer) {
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
}
