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
import Models.Player;

public class NewGame implements Choosable {

    Printable printable;
    private final GameWindow gameWindow;
    private GameSettings gameSettings = GameSettings.getInstance();
    private Scenario activeScenario;
    private JSONParsing jsonParser;
    private Player player;
    Printer printer;

    public NewGame()
    {
        this.player = new Player("Kris");
        this.gameWindow = new GameWindow(NewGame.this, printable, this.player);
        this.jsonParser = new JSONParsing();
        this.printer = new Printer(gameWindow);
        this.printable = printer.getPrintable();
        player = new Player("Kris");
        runStartChoice();
    }

    private void runStartChoice() {
        this.activeScenario = jsonParser.getChoiceFromJsonV2("introRoom0");
        gameWindow.printResponseToGameArea(this.activeScenario.getTitle(), this.activeScenario.getDescription());
        gameWindow.feedSideBar(this.activeScenario);
    }

    // Give this the id of the movementCommand
    public void nextChoice(String id) {
        Scenario newActiveScenario = jsonParser.getChoiceFromJsonV2(id);
        gameWindow.printResponseToGameArea(newActiveScenario.getTitle(), newActiveScenario.getDescription());
        this.activeScenario = newActiveScenario;
        gameSettings.upTurnCount();
        gameWindow.feedSideBar(newActiveScenario);
    }

    @Override
    public Scenario getActiveScenario() { return activeScenario; }
}
