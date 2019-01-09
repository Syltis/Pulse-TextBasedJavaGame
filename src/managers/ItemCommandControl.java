package managers;

import gameplay.GameSettings;
import interfaces.ICommandControl;
import interfaces.IGameWindowPrint;
import interfaces.INewGame;
import interfaces.IPlayer;
import models.Item;
import models.ItemCommand;
import models.Scenario;

public class ItemCommandControl implements ICommandControl {

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
			IGameWindowPrint.printResponseToLog("What does itemCommand '" + playerCommand + "' even mean?");
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
}
