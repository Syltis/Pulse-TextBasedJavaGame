package managers;

import gameplay.GameSettings;
import interfaces.ICommandController;
import interfaces.IGameWindowPrint;
import interfaces.INewGame;
import interfaces.IPlayer;
import models.CombatCommand;
import models.Enemy;
import models.Scenario;

public class CombatCommandControl implements ICommandController {

	public void ControlCommand(Scenario activeScenario, String playerCommand, GameSettings gameSettings, INewGame INewGame, IGameWindowPrint IGameWindowPrint, IPlayer IPLayer) {
		String combatResult = null;
		for (CombatCommand aCombatCommand:activeScenario.getAvailableCombatCommands()) {
			if (aCombatCommand.getCommand().equals(playerCommand)) {
				for (CombatCommand anotherCombatCommand:gameSettings.getCombatCommandBank()) {
					if (aCombatCommand.getResult().equals(anotherCombatCommand.getResult())) {
						combatResult = aCombatCommand.getResult();
						Enemy enemy = StringUtilities.generateEnemyFromCombatResult(combatResult);
					}
				}
			}
		}
		if (combatResult == null) {
			IGameWindowPrint.printResponseToLog("What does '" + playerCommand + "' even mean?");
			return;
		}
		// TODO Instantiate battle, e.g. new Battle(Player, Enemy);

	}
}
