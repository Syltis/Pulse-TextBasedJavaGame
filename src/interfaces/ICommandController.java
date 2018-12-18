package interfaces;

import gameplay.GameSettings;
import models.Scenario;

public interface ICommandController {

	void ControlCommand(
					Scenario activeScenario,
					String playerCommand,
					GameSettings gameSettings,
					INewGame INewGame,
					IGameWindowPrint IGameWindowPrint,
					IPlayer IPlayer
			);
}
