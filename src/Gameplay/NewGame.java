package Gameplay;

import GUI.GameWindow;
import Managers.JSONParsing;
import Models.ChoiceV2;

/*
Has edited this:
- Kristoffer
*/

public class NewGame {

    private GameWindow gameWindow;
    private ChoiceV2 activeChoice;

    public NewGame() {
        this.gameWindow = new GameWindow();
        runTestSegment();
    }

    private void runTestSegment() {
        JSONParsing jsonParser = new JSONParsing();
        activeChoice = jsonParser.getChoiceFromJsonV2(0);
        gameWindow.printToGameArea(activeChoice.getDescription());
    }
}
