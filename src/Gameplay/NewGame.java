package Gameplay;

/*
Has edited this:
- Kristoffer
*/

import GUI.GameWindow;
import GUI.Printer;
import Interfaces.Choosable;
import Interfaces.Printable;
import Managers.JSONParsing;
import Models.PlayerCharacter;
import Models.Scenario;

public class NewGame implements Choosable {

    private Printable printable;
    private final GameWindow gameWindow;
    private GameSettings gameSettings = GameSettings.getInstance();
    private Scenario activeScenario;
    private JSONParsing jsonParser;

    public NewGame()
    {
        PlayerCharacter playerCharacter = new PlayerCharacter();
        this.gameWindow = new GameWindow(NewGame.this, playerCharacter);
        this.jsonParser = new JSONParsing();
        Printer printer = new Printer(gameWindow);
        this.printable = printer.getPrintable();
        startFirstScenario();
    }

    private void startFirstScenario() {
        this.activeScenario = jsonParser.getScenarioFromJson("introRoom0");
        gameWindow.printScenarioToGameArea(this.activeScenario.getTitle(), this.activeScenario.getDescription());
        gameWindow.feedSideBar(this.activeScenario);
    }

    // Give this the id of the movementCommand
    public void nextScenario(String nextScenarioId) {
        Scenario newActiveScenario = jsonParser.getScenarioFromJson(nextScenarioId);
        gameSettings.upTurnCount();
        gameWindow.printScenarioToGameArea(newActiveScenario.getTitle(), newActiveScenario.getDescription());
        this.activeScenario = newActiveScenario;
        gameWindow.feedSideBar(newActiveScenario);
    }

    @Override
    public Scenario getActiveScenario() { return activeScenario; }
}
