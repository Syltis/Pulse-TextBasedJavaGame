package Gameplay;

/*
Has edited this:
- Matt
- Kristoffer
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Character {

    private String name;
    private String description;
    private int health;
    private int[] skills;
    private int money;
    private String location;
    private

    Character(String name, String description, int health, int[] skills, int money, String location) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.skills = skills;
        this.money = money;
        this.location = location;
    }
    
    Character() {
        this.name = "Unnamed";
        this.description = "No description";
        this.health = 100;
        this.money = 0;
        this.location = "tutorial";
        this.skills = new int[] {0,0,0,0,0,0};  
    }

    public boolean isAlive() {
        return health >= 0;
    }
    
    public int getStat(String stat) {
        if(stat.equals("STR")) return this.skills[0];
        if(stat.equals("LCK")) return this.skills[1];
        if(stat.equals("CHR")) return this.skills[2];
        if(stat.equals("CON")) return this.skills[3];
        if(stat.equals("FTH")) return this.skills[4];
        if(stat.equals("INT")) return this.skills[5];
        if(stat.equals("SPD")) return this.skills[6];
        return -1;
    }

    public void setStat(String stat, int value) {
        if(stat.equals("STR")) {
            this.skills[0] = value;
        }
        if(stat.equals("LCK")) this.skills[1] = value;
        if(stat.equals("CHR")) this.skills[2] = value;
        if(stat.equals("CON")) this.skills[3] = value;
        if(stat.equals("FTH")) this.skills[4] = value;
        if(stat.equals("INT")) this.skills[5] = value;
        if(stat.equals("SPD")) this.skills[6] = value;
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

    public void attack(Character Enemy) {
        int damage = (int)((this.getStat("STR") - Enemy.getStat("CON") /2) * Math.random());
        if(Math.signum(damage)!=-1.0) { 
            System.out.println("\n"+this.name + " dealt " + damage + " HP to " + Enemy.getName() + ".");
            Enemy.health-=damage;
            System.out.println(Enemy.name + ": " + Enemy.health + "remaining.");
            }
            else {System.out.println(this.name + " missed.");}
    }

    public void rest(Character Enemy) {
        int recovery = (int)((this.getStat("CON")) * Math.random());
        if(Math.signum(recovery)!=-1.0) { 
        System.out.println(this.name + " rested for " + recovery + " HP.");
        this.health+=recovery;
        }
        else {System.out.println(this.name + " was blocked from resting.");}
    }

    public void startBattle(Character Enemy) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(this.isAlive() && Enemy.isAlive()) {
            int battleChoice = this.choose("Select an action!", new String[] {"Attack","Defend"});
            if(battleChoice == 1) this.rest(Enemy);
            if(battleChoice == 0){
            if(this.isAlive()) this.attack(Enemy);
            if(Enemy.isAlive() && this.isAlive()) Enemy.attack(this);
            }
        }
        if(this.health<0) {
            System.out.println("You are Dead.");
        }
        if(Enemy.health<0) {
            System.out.println("Damn, you killed that guy.");
        }
    }
}