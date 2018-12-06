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
import Models.Scenario;

public class NewGame implements Choosable {

    Printable printable;
    private final GameWindow gameWindow;
    private Character playerCharacter;
    private GameSettings gameSettings = GameSettings.getInstance();
    private Scenario activeScenario;
    private JSONParsing jsonParser;

    private Printer printer;

    public NewGame()
    {
        this.playerCharacter = new Character();
        this.gameWindow = new GameWindow(NewGame.this, this.playerCharacter);
        this.jsonParser = new JSONParsing();
        this.printer = new Printer(gameWindow);
        this.printable = printer.getPrintable();
        runStartChoice();
    }

    private void runStartChoice() {
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
