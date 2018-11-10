package Gameplay;

import DataTransferObjects.Choice;
import GUI.GameWindow;
import Managers.JSONParsing;

/*
Has edited this:
- Kristoffer
*/

public class NewGame {

    private GameWindow gameWindow;

    public NewGame() {
        this.gameWindow = new GameWindow();
        runTestSegment();
    }

    private void runTestSegment() {
        JSONParsing jsonParser = new JSONParsing();
        Choice choice0 = jsonParser.getChoiceFromJson(0);
        gameWindow.printToGameArea(choice0.getDescription());
        GameSettings.setCurrentChoice(choice0);    }
}
