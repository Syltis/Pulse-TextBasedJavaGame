package Managers;

import Gameplay.GameSettings;
import Interfaces.ICommandController;
import Interfaces.IGameWindowPrint;
import Interfaces.INewGame;
import Interfaces.IPlayer;
import Models.CombatCommand;
import Models.Enemy;
import Models.Scenario;

public class CombatCommandCtlr implements ICommandController {

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
