package Gameplay;

/*
Has edited this:
- Kristoffer
*/

import GUI.GameWindow;
import GUI.Printer;
import Interfaces.Choosable;
import Interfaces.Printable;
import Managers.JSONParsing;
import Models.Choice;
import Models.Player;

public class NewGame implements Choosable {

    Printable printable;
    private final GameWindow gameWindow;
    private GameSettings gameSettings = GameSettings.getInstance();
    private Choice activeChoice;
    private JSONParsing jsonParser;
    private Player player;
    Printer printer;

    public NewGame()
    {
        this.player = new Player("Kris");
        this.gameWindow = new GameWindow(NewGame.this, printable, this.player);
        this.jsonParser = new JSONParsing();
        this.printer = new Printer(gameWindow);
        this.printable = printer.getPrintable();
        player = new Player("Kris");
        runStartChoice();
    }

    private void runStartChoice() {
        this.activeChoice = jsonParser.getChoiceFromJsonV2("introRoom0");
        gameWindow.printResponseToGameArea(this.activeChoice.getTitle(), this.activeChoice.getDescription());
        gameWindow.feedSideBar(this.activeChoice);
    }

    // Give this the id of the movementCommand
    public void nextChoice(String id) {
        Choice newActiveChoice = jsonParser.getChoiceFromJsonV2(id);
        gameWindow.printResponseToGameArea(newActiveChoice.getTitle(), newActiveChoice.getDescription());
        this.activeChoice = newActiveChoice;
        gameSettings.upTurnCount();
        gameWindow.feedSideBar(newActiveChoice);
    }

    @Override
    public Choice getActiveChoice() { return activeChoice; }
}
