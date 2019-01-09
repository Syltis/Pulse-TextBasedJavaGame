package gameplay;

import gui.GameWindow;
import interfaces.INewGame;
import json.JSONParsing;
import models.Player;
import models.Scenario;

/*
Class for running a new game.
- Holds the activescenario which accessed through the INewGame-interface in CommandControlHub
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
        try {
            this.activeScenario = JSONParsing.getScenarioFromJson("intro_Room");
        }
        catch(NullPointerException nPE) {
            System.out.println("Scenario not Found");
            nPE.printStackTrace();
        }
        gameWindow.printScenarioToGameArea(this.activeScenario.getTitle(), this.activeScenario.getDescription());
        gameWindow.feedSideBar(this.activeScenario);
    }

    // Receives the ID of the next scenario to be displayed, ups the turnCount,
    //     displays the scenario, sets activeScenario to the new one and updates the sidebar.
    public void nextScenario(String result) {
        Scenario newActiveScenario = JSONParsing.getScenarioFromJson(result);
        gameSettings.upTurnCount();
        gameWindow.printScenarioToGameArea(newActiveScenario.getTitle(), newActiveScenario.getDescription());
        this.activeScenario = newActiveScenario;
        gameWindow.feedSideBar(newActiveScenario);
    }

    public Scenario getActiveScenario() { return activeScenario; }
}
