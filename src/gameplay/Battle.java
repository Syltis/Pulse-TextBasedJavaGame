package gameplay;

import interfaces.IGameWindowPrint;
import models.Being;
import models.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
Class for handling the battles between the Player and various Enemies
Was previously written for using the console for I/O, so these methods needs to be changed.
TODO: Reconstruct class to work with the current idea of a battle-system
 */

public class Battle {

	private final Player playerCombatant;
	private final Player CombatantB;
	private final IGameWindowPrint IGameWindowPrint;
	
	public Battle(Player playerCombatant, Player CombatantB, IGameWindowPrint IGameWindowPrint) throws IOException
	{
		this.playerCombatant = playerCombatant;
		this.CombatantB = CombatantB;
		this.IGameWindowPrint = IGameWindowPrint;
		executeBattle(this.playerCombatant, this.CombatantB);
	}
    public void executeBattle(Player CombatantA, Player CombatantB) throws IOException {
    	//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	while(CombatantA.isAlive() && CombatantB.isAlive()) {
    		int battleChoice = choose("Select an action!", new String[] {"Attack","Defend"});
    		if(battleChoice == 1) rest(CombatantB);
    		if(battleChoice == 0){
    		if(CombatantA.isAlive()) attack(CombatantB);
    		if(CombatantB.isAlive() && CombatantA.isAlive()) attack(CombatantA);
    		}
    	}
    	if(CombatantA.getHealth()<0) {
    		System.out.println(CombatantA.getName()+" has defeated " + CombatantB.getName());
    	}
    	if(CombatantB.getHealth()<0) {
    		System.out.println(CombatantB.getName()+" has defeated " + CombatantA.getName());
    	}
    }

	public void attack(Being enemy) {
		int damage = (int)((playerCombatant.getStat("STR") - enemy.getStat("CON") /2) * Math.random());
		if(Math.signum(damage)!=-1.0) {
			int newHealth = enemy.getHealth()-damage;
			enemy.setHealth(newHealth-=damage);
		}
		else {}
	}

	public void rest(Player Enemy) {
		int recovery = (int)((playerCombatant.getStat("CON")) * Math.random());
		if(Math.signum(recovery)!=-1.0) {
			System.out.println(playerCombatant.getName() + " rested for " + recovery + " HP.");
			//this.health+=recovery;
		}
		else {System.out.println(playerCombatant.getName() + " was blocked from resting.");}
	}

	public void startBattle(Player Enemy) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(playerCombatant.isAlive() && Enemy.isAlive()) {
			int battleChoice = choose("Select an action!", new String[] {"Attack","Rest"});
			if(battleChoice == 1) this.rest(Enemy);
			if(battleChoice == 0){
				if(playerCombatant.isAlive()) this.attack(Enemy);
				if(Enemy.isAlive() && playerCombatant.isAlive()) {
					attack(CombatantB);
				}
			}
		}
		if(playerCombatant.getHealth()<0) {
			System.out.println("You are Dead.");
		}
		if(playerCombatant.getHealth()<0) {
			System.out.println("Damn, you killed that guy.");
		}
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
