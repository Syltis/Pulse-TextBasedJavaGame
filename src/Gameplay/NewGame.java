package Gameplay;

import GUI.GameWindow;
import Interfaces.Choosable;
import Managers.JSONParsing;
import Models.ChoiceV2;
import Models.PlayerCommand;

/*
Has edited this:
- Kristoffer
*/

public class NewGame implements Choosable {

    private GameWindow gameWindow;
    private ChoiceV2 activeChoice;
    private PlayerCommand activePlayerCommand;

    public NewGame() {
        this.gameWindow = new GameWindow(NewGame.this);
        runTestSegment();
    }

    private void runTestSegment() {
        JSONParsing jsonParser = new JSONParsing();
        activeChoice = jsonParser.getChoiceFromJsonV2(0);
        gameWindow.printToGameArea(activeChoice.getDescription());
        gameWindow.printToSidebarArea(activeChoice.getAvailableActionCommands().toString());
    }

    @Override
    public ChoiceV2 getActiveChoice() {

        return activeChoice;
    }

    @Override
    public void setActiveChoice(ChoiceV2 choice)  {

        this.activeChoice = choice;
    }

    @Override
    public PlayerCommand getActivePlayerCommand() {
        return this.activePlayerCommand;
    }

    @Override
    public void setActivePlayerCommand(PlayerCommand command) {
        this.activePlayerCommand = command;
    }
}
