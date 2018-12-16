package Managers;

import Gameplay.GameSettings;
import Interfaces.ICommandController;
import Interfaces.IGameWindowPrint;
import Interfaces.INewGame;
import Interfaces.IPlayer;
import Models.Item;
import Models.ItemCommand;
import Models.Scenario;

public class ItemCommandCtrl implements ICommandController {

	public void ControlCommand(Scenario activeScenario, String playerCommand, GameSettings gameSettings, INewGame INewGame, IGameWindowPrint IGameWindowPrint, IPlayer IPlayer) {
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

	}
}
