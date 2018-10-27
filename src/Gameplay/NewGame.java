package Gameplay;

import DataTransferObjects.Situation;
import GUI.GameWindow;
import Managers.JSONParsing;

public class NewGame {

    private GameWindow gameWindow;

    public void runNewGame() {
        gameWindow = new GameWindow();
        gameWindow.openGameWindow();
        runTestSegment();
    }

    private void runTestSegment() {
        //Situation: 0
        //Dark room
        JSONParsing jsonParser = new JSONParsing();
        Situation situation0;
        situation0 = jsonParser.getSituationFromJson(0);
        gameWindow.printToGameArea(situation0.getDescription());


    }
}
