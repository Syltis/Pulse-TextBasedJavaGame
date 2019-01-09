package managers;

import gameplay.GameSettings;
import interfaces.ICommandController;
import interfaces.IGameWindowPrint;
import interfaces.INewGame;
import interfaces.IPlayer;
import models.ActionCommand;
import models.Item;
import models.Scenario;
import org.apache.commons.lang3.text.WordUtils;

public class ActionCommandControl implements ICommandController {

	private INewGame INewGame;
	private IPlayer IPlayer;
	private IGameWindowPrint IGameWindowPrint;

	public void ControlCommand(Scenario activeScenario, String playerCommand, GameSettings gameSettings, INewGame INewGame, IGameWindowPrint IGameWindowPrint, IPlayer IPlayer) {

		this.INewGame = INewGame;
		this.IGameWindowPrint = IGameWindowPrint;
		this.IPlayer = IPlayer;

		// Check if player asks for inventory
		String actionResult = null;
		if (playerCommand.equals("inventory") || playerCommand.equals("i")) {
			// Display current inventory
			IGameWindowPrint.printInventoryToGameArea(IPlayer.getInventory());
			return;
		}

		// TODO move 'use'-commands to its own class
		if (StringUtilities.commandIsUse(playerCommand)) {
			String itemName = playerCommand.substring(playerCommand.indexOf(' ') + 1);
			itemName = WordUtils.capitalizeFully(itemName);
				for (Item aItem: IPlayer.getInventory()) {
					if (aItem.getItemName().equals(itemName)) {
						if (aItem.getItemType().equals("weapon")) {

						}
						IGameWindowPrint.printToGameArea("Effect to apply: " + aItem.getEffect(), false);
						return;
					}
				}
				// TODO check towards players inventory
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

	private void equipWeapon(Item item) {
		int statChange;
		String stat = item.getEffect().substring(0,3);
		String statChangeString = item.getEffect().substring(4,5);
		try{
				statChange = Integer.parseInt(statChangeString);
				IPlayer.setEquippedItem(item);

		}
		catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return;
		}


	}
}
