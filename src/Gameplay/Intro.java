package Gameplay;

import GUI.GameWindow;
import Helpers.Print;

public class Intro {

    Print print;
    Player player;
    GameWindow gameWindow;

    public Intro()
    {
        player = new Player();
        print = new Print();
        player = charCreation();
    }

    private Player charCreation() {
        gameWindow = new GameWindow();
        gameWindow.openGameWindow();
        gameWindow.printToGameArea("Wake up");
        player.setAge(21);
        player.setName("Kris");
        player.setGender("m");
        return player;
    }
}
