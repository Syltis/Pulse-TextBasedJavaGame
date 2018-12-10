package Gameplay;

/*
Has edited this:
- Kristoffer
*/

import GUI.GameWindow;
import Interfaces.IActiveScenario;
import Managers.JSONParsing;
import Models.PlayerBeing;
import Models.Scenario;

public class NewGame implements IActiveScenario {

    private final GameWindow gameWindow;
    private GameSettings gameSettings = GameSettings.getInstance();
    private Scenario activeScenario;
    private JSONParsing jsonParser;

    public NewGame()
    {
        PlayerBeing playerBeing = new PlayerBeing();
        this.gameWindow = new GameWindow(NewGame.this, playerBeing);
        this.jsonParser = new JSONParsing();
        startFirstScenario();
    }

    private void startFirstScenario() {
        this.activeScenario = jsonParser.getScenarioFromJson("introRoom0");
        gameWindow.printScenarioToGameArea(this.activeScenario.getTitle(), this.activeScenario.getDescription());
        gameWindow.feedSideBar(this.activeScenario);
    }

    // Give this the id of the movementCommand
    public void nextScenario(String result) {
        Scenario newActiveScenario = jsonParser.getScenarioFromJson(result);
        gameSettings.upTurnCount();
        gameWindow.printScenarioToGameArea(newActiveScenario.getTitle(), newActiveScenario.getDescription());
        this.activeScenario = newActiveScenario;
        gameWindow.feedSideBar(newActiveScenario);
    }

    public Scenario getActiveScenario() { return activeScenario; }
}
