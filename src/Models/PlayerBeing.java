package Models;

/*
Has edited this:
- Matt
- Kristoffer
*/

import Interfaces.IPlayerBeing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PlayerBeing extends Being implements IPlayerBeing {

    public PlayerBeing(String name, String description, int health, int[] skills, int money)
    {
        super(name, description, health, skills, money);
        addToInventory(new Item("Bread", "Food", "health+10", false));
    }
    
    public PlayerBeing()
    {
        super("Unnamed",
                "No description",
                100,
                new int[] {0,0,0},
                50
        );
        addToInventory(new Item("Bread", "Food", "health+10", false));
    }

    public int choose(String prompt, String[] choices) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(prompt);
        System.out.println(Arrays.toString(choices));
        String input = br.readLine();
        int choice = Arrays.asList(choices).indexOf(input);
        while(choice==-1) { input = br.readLine(); choice = Arrays.asList(choices).indexOf(input);}
        return choice;
    }

    public PlayerBeing getPlayerCharacter() {
        return PlayerBeing.this;
    }
}