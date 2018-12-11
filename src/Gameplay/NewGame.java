package Gameplay;

import GUI.GameWindow;
import Interfaces.INewGame;
import Managers.JSONParsing;
import Models.Player;
import Models.Scenario;

public class NewGame implements INewGame {

    private final GameWindow gameWindow;
    private final GameSettings gameSettings = GameSettings.getInstance();
    private Scenario activeScenario;

    public NewGame()
    {
        Player player = new Player();
        this.gameWindow = new GameWindow(NewGame.this, player);
        startFirstScenario();
    }

    private void startFirstScenario() {
        this.activeScenario = JSONParsing.getScenarioFromJson("introRoom0");
        gameWindow.printScenarioToGameArea(this.activeScenario.getTitle(), this.activeScenario.getDescription());
        gameWindow.feedSideBar(this.activeScenario);
    }

    // Give this the id of the movementCommand
    public void nextScenario(String result) {
        Scenario newActiveScenario = JSONParsing.getScenarioFromJson(result);
        gameSettings.upTurnCount();
        gameWindow.printScenarioToGameArea(newActiveScenario.getTitle(), newActiveScenario.getDescription());
        this.activeScenario = newActiveScenario;
        gameWindow.feedSideBar(newActiveScenario);
    }

    public Scenario getActiveScenario() { return activeScenario; }
}
