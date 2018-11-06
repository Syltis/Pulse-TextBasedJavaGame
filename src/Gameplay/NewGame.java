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

    }

    public void runNewGame() {
        gameWindow = new GameWindow();
        gameWindow.openGameWindow();
        runTestSegment();
    }

    /*
    TODO: Have the game set off by the first player input, then call new Sit when player input matches.
     */

    private void runTestSegment() {
        //Choice: 0
        //Dark room
        JSONParsing jsonParser = new JSONParsing();
        Choice choice0;
        // Get the situation
        choice0 = jsonParser.getChoiceFromJson(0);
        gameWindow.printToGameArea(choice0.getDescription());
    }
}
