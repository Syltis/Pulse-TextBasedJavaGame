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
        GameSettings gameSettings = GameSettings.getInstance();
        runTestSegment();
    }

    private void runTestSegment() {
        this.activeChoice = jsonParser.getChoiceFromJson(0);
        gameWindow.printToGameArea(this.activeChoice.getDescription());
        feedSideBar(this.activeChoice);

    }

    // Give this the id of the movementCommand
    public void nextChoice(int id) {
        Choice newActiveChoice = jsonParser.getChoiceFromJson(id);
        gameWindow.printToGameArea(newActiveChoice.getDescription());
        this.activeChoice = newActiveChoice;
        feedSideBar(newActiveChoice);
    }

    public void feedSideBar(Choice activeChoice) {
        gameWindow.printToSidebarArea(activeChoice.getAvailableMovementCommands().toString());
        gameWindow.printToSidebarArea(activeChoice.getAvailableActionCommands().toString());
        gameWindow.printToSidebarArea(activeChoice.getAvailableCombatCommands().toString());
    }

    @Override
    public Choice getActiveChoice() { return activeChoice;
    }
}
