package Gameplay;

/*
Has edited this:
- Matt
- Kristoffer
*/

/*
TODO Split model (Character) from battle-logic
 */

import Interfaces.Playable;
import Models.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Character implements Playable {

    private String name;
    private String description;
    private int health;
    private int[] skills;
    private int money;
    private String location;
    private List<Item> inventory = new ArrayList<>();

    Character(String name, String description, int health, int[] skills, int money)
    {
        this.name = name;
        this.description = description;
        this.health = health;
        this.skills = skills;
        this.money = money;
        addToInventory(new Item("Excalibur", "Sword"));
        addToInventory(new Item("Bread", "Food"));
    }
    
    Character()
    {
        this.name = "Unnamed";
        this.description = "No description";
        this.health = 100;
        this.money = 0;
        this.location = "tutorial";
        this.skills = new int[] {0,0,0};
        addToInventory(new Item("Excalibur", "Sword"));
        addToInventory(new Item("Bread", "Food"));
    }

    public Character getCharacter() {
        return Character.this;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public boolean isAlive() {
        return health >= 0;
    }
    
    public int getStat(String stat) {
        if(stat.equals("STR")) return this.skills[0];
        if(stat.equals("LCK")) return this.skills[1];
        if(stat.equals("CON")) return this.skills[2];

        return -1;
    }

    public void setStat(String stat, int value) {
        if(stat.equals("STR")) {
            this.skills[0] = value;
        }
        if(stat.equals("LCK")) this.skills[0] = value;
        if(stat.equals("CHR")) this.skills[1] = value;
        if(stat.equals("CON")) this.skills[2] = value;

    }
    
    public int getMoney() {
        return money;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getName() {
        return name;
    }

    public int getHealth() { return health; }

    public void setHealth(int health) { this.health = health; }

    public String getLocation() {
        return location;
    }

    public void setMoney(int value) {
        this.money = value;
    }
    
    public void setDescription(String value) {
        this.description = value;
    }
    
    public void setName(String value) {
        this.name = value;
    }
    
    public String setLocation() {
        return location;
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