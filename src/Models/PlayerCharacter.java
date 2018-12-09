package Models;

/*
Has edited this:
- Matt
- Kristoffer
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerCharacter extends Being {

    private String name;
    private String description;
    private int health;
    private int[] skills;
    private int money;
    private List<Item> inventory = new ArrayList<>();

    public PlayerCharacter(String name, String description, int health, int[] skills, int money)
    {
        this.name = name;
        this.description = description;
        this.health = health;
        this.skills = skills;
        this.money = money;
        addToInventory(new Item("Excalibur", "Sword", true));
        addToInventory(new Item("Bread", "Food", false));
    }
    
    public PlayerCharacter()
    {
        this.name = "Unnamed";
        this.description = "No description";
        this.health = 100;
        this.money = 0;
        this.skills = new int[] {0,0,0};
        addToInventory(new Item("Excalibur", "Sword", true));
        addToInventory(new Item("Bread", "Food", false));
    }

    public boolean isAlive() {
        return health >= 0;
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
}