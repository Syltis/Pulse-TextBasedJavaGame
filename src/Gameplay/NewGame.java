package Gameplay;

import GUI.GameWindow;
import Helpers.PlayerInput;
import Helpers.Print;

public class NewGame {

    Print print;
    Player player;
    PlayerInput playerInput;
    GameWindow gameWindow;
    private int i = 0;

    public void runNewGame() {
        gameWindow = new GameWindow();
        gameWindow.openGameWindow();
        runTestSegment();
    }

    // TODO: How can we pass the game segments as objects? - kris
    public void runTestSegment() {
        gameWindow.printToGameArea("Wake up");
        gameWindow.printToGameArea("Whats your name?");
    }

    /* Not finished timers/sleep

    private Timer tmr = new Timer(2000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.printToGameArea(" .");
            ++i;
            if(i >= 2)
                tmr.stop();
        }
    })

    public void sleepThread(int millis) throws InterruptedException {
        try {
            Thread.sleep(millis) ;
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }
    */
}
