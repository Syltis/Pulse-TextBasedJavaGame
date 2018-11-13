package Gameplay;

/*
Has edited this:
- Kristoffer
*/

import GUI.GameWindow;
import Interfaces.Choosable;
import Managers.JSONParsing;
import Models.ChoiceV2;
import Models.PlayerCommand;

public class NewGame implements Choosable {

    private GameWindow gameWindow;
    private ChoiceV2 activeChoice;
    private PlayerCommand activePlayerCommand;
    private JSONParsing jsonParser;

    public NewGame() {
        this.gameWindow = new GameWindow(NewGame.this);
        jsonParser = new JSONParsing();
        GameSettings gameSettings = GameSettings.getInstance();
        runTestSegment();
    }

    private void runTestSegment() {
        activeChoice = jsonParser.getChoiceFromJsonV2(0);
        gameWindow.printToGameArea(activeChoice.getDescription());
        feedSideBar(this.activeChoice);

    }

    public void outputNextChoice(int id) {
        activeChoice = jsonParser.getChoiceFromJsonV2(id);
        gameWindow.printToGameArea(activeChoice.getDescription());
    }

    public void feedSideBar(ChoiceV2 activeChoice) {
        gameWindow.printToSidebarArea(activeChoice.getAvailableMovementCommands().toString());
        gameWindow.printToSidebarArea(activeChoice.getAvailableActionCommands().toString());
        gameWindow.printToSidebarArea(activeChoice.getAvailableCombatCommands().toString());
    }

    @Override
    public ChoiceV2 getActiveChoice() { return activeChoice;
    }

    @Override
    public void setActiveChoice(ChoiceV2 choice)  { this.activeChoice = choice;
    }

    @Override
    public PlayerCommand getActivePlayerCommand() { return this.activePlayerCommand;
    }

    @Override
    public void setActivePlayerCommand(PlayerCommand command) { this.activePlayerCommand = command;
    }
}
