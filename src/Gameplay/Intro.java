package Gameplay;

import GUI.GameWindow;
import Helpers.Print;

import java.util.concurrent.TimeUnit;

public class Intro {

    Print print;
    Player player;


    public Intro()
    {
        player = new Player();
        print = new Print();
        start();
    }

    private Player charCreation() {
        player.setAge(21);
        player.setName("Kris");
        player.setGender("m");
        return player;
    }

    public void start() {
        Player player = charCreation();
    }

}
