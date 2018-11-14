package Gameplay;

/*
Has edited this:
- Kristoffer
*/

import GUI.GameWindow;
import Interfaces.Choosable;
import Managers.JSONParsing;
import Models.Choice;

public class NewGame implements Choosable {

    private GameWindow gameWindow;
    private Choice activeChoice;
    private JSONParsing jsonParser;

    public NewGame() {
        this.gameWindow = new GameWindow(NewGame.this);
        this.jsonParser = new JSONParsing();
        runStartChoice();
    }

    private void runStartChoice() {
        this.activeChoice = jsonParser.getChoiceFromJson(0);
        gameWindow.printResponseToGameArea(this.activeChoice.getTitle(), this.activeChoice.getDescription());
        gameWindow.feedSideBar(this.activeChoice);
    }

    // Give this the id of the movementCommand
    public void nextChoice(int id) {
        Choice newActiveChoice = jsonParser.getChoiceFromJson(id);
        gameWindow.printResponseToGameArea(newActiveChoice.getTitle(), newActiveChoice.getDescription());
        this.activeChoice = newActiveChoice;
        gameWindow.feedSideBar(newActiveChoice);
    }

    @Override
    public Choice getActiveChoice() { return activeChoice;
    }
}
