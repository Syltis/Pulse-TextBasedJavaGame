package managers;

import gameplay.GameSettings;
import interfaces.ICommandController;
import interfaces.IGameWindowPrint;
import interfaces.INewGame;
import interfaces.IPlayer;
import models.MovementCommand;
import models.Scenario;

public class MovementCommandControl implements ICommandController {

	public void ControlCommand(Scenario activeScenario, String playerCommand, GameSettings gameSettings, INewGame INewGame, IGameWindowPrint IGameWindowPrint, IPlayer IPlayer) {
		String result = null;
		for (MovementCommand aMovementCommand: activeScenario.getAvailableMovementCommands()) {
			if (aMovementCommand.getCommand().equals(playerCommand)) {
				for (MovementCommand anotherMovementCommand: gameSettings.getMovementCommandBank()) {
					if (aMovementCommand.getResult().equals(anotherMovementCommand.getResult())) {
						result = aMovementCommand.getResult();
					}
				}
			}
		}
		if (result == null) {
			IGameWindowPrint.printResponseToLog("What does movementCommand '" + playerCommand + "' even mean?");
			return;
		}
		// Print command to GameArea
		IGameWindowPrint.printToGameArea(playerCommand, true);
		IGameWindowPrint.printToGameArea("", false);
		// Clearing of sidebar must be done here and not in nextScenario(), though the new values are sent from there
		IGameWindowPrint.clearSideBarArea();
		// nextScenario() takes the new Scenario ID and displays the new Scenario
		INewGame.nextScenario(result);

	}
}
