package Gameplay;

import GUI.GameWindow;
import Interfaces.INewGame;
import Managers.JSONParsing;
import Models.Player;
import Models.Scenario;

/*
Class for running a new game.
 */
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

    // Displays the first scenario
    private void startFirstScenario() {
        this.activeScenario = JSONParsing.getScenarioFromJson("introRoom0");
        gameWindow.printScenarioToGameArea(this.activeScenario.getTitle(), this.activeScenario.getDescription());
        gameWindow.feedSideBar(this.activeScenario);
    }

    //      Receives the ID of the next scenario to be displayed, ups the turnCount,
    // displays the scenario, sets activeScenario to the new one and updates the sidebar.
    public void nextScenario(String result) {
        Scenario newActiveScenario = JSONParsing.getScenarioFromJson(result);
        gameSettings.upTurnCount();
        gameWindow.printScenarioToGameArea(newActiveScenario.getTitle(), newActiveScenario.getDescription());
        this.activeScenario = newActiveScenario;
        gameWindow.feedSideBar(newActiveScenario);
    }

    public Scenario getActiveScenario() { return activeScenario; }
}
