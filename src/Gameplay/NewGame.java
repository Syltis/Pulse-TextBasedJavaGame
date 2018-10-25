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
        gameWindow.printToGameArea("Wake up");
        gameWindow.printToGameArea("Whats your name?");

        //Situation: 0
        //Yellow House
        JSONParsing jsonParser = new JSONParsing();
        Situation situation0;
        situation0 = jsonParser.getSituationFromJson(0);
        gameWindow.printToGameArea(situation0.getDescription());


    }
}
