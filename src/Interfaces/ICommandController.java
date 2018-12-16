package Interfaces;

import Gameplay.GameSettings;
import Models.Scenario;

public interface ICommandController {

	void ControlCommand(Scenario activeScenario, String playerCommand, GameSettings gameSettings, INewGame INewGame, IGameWindowPrint IGameWindowPrint, IPlayer IPlayer);

	}
