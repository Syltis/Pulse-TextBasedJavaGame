package Gameplay;

import GUI.GameWindow;
import Managers.JSONParsing;

public class NewGame {

    GameWindow gameWindow;

    public void runNewGame() {
        gameWindow = new GameWindow();
        gameWindow.openGameWindow();
        runTestSegment();
    }

    private void runTestSegment() {
        gameWindow.printToGameArea("Wake up");
        gameWindow.printToGameArea("Whats your name?");
        JSONParsing jsonParser = new JSONParsing();
        gameWindow.printToGameArea(jsonParser.getSituationFromJson(0).getDescription());

    }
}
