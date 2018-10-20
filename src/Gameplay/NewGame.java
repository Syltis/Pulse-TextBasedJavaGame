package Gameplay;

import GUI.GameWindow;
import Helpers.Print;

public class NewGame {

    Print print;
    Player player;
    GameWindow gameWindow;

    public NewGame()
    {
        player = new Player();
        print = new Print();
    }

    public void runNewGame() {
        gameWindow = new GameWindow();
        gameWindow.openGameWindow();
        gameWindow.printToGameArea("Wake up");
        // TODO: Delay, sleep?
        runTestSegment();
    }

    // TODO: How can we pass the game segments as objects?
    public void runTestSegment() {
        gameWindow.printToGameArea("Whats your name?");
    }
}
