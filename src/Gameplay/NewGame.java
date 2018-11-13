package Gameplay;

import GUI.GameWindow;
import Interfaces.Choosable;
import Managers.JSONParsing;
import Models.ChoiceV2;

/*
Has edited this:
- Kristoffer
*/

public class NewGame implements Choosable {

    private GameWindow gameWindow;
    private ChoiceV2 activeChoice;

    public NewGame() {
        this.gameWindow = new GameWindow(NewGame.this);
        runTestSegment();
    }

    private void runTestSegment() {
        JSONParsing jsonParser = new JSONParsing();
        activeChoice = jsonParser.getChoiceFromJsonV2(0);
        gameWindow.printToGameArea(activeChoice.getDescription());
    }

    @Override
    public ChoiceV2 getActiveChoice() {
        return activeChoice;
    }

    @Override
    public void setActiveChoice(ChoiceV2 choice)  {
        this.activeChoice = choice;
    }
}
