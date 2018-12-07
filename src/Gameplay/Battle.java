package Gameplay;

import Models.PlayerCharacter;

import java.io.IOException;

public class Battle {
	private PlayerCharacter playerCombatant;
	private PlayerCharacter CombatantB;
	
	Battle(PlayerCharacter playerCombatant, PlayerCharacter CombatantB) throws IOException{
		this.playerCombatant = playerCombatant;
		this.CombatantB = CombatantB;
		executeBattle(this.playerCombatant, this.CombatantB);
	}
    public void executeBattle(PlayerCharacter CombatantA, PlayerCharacter CombatantB) throws IOException {
    	//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	while(CombatantA.isAlive() && CombatantB.isAlive()) {
    		int battleChoice = CombatantA.choose("Select an action!", new String[] {"Attack","Defend"});
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

	public void attack(PlayerCharacter Enemy) {
		int damage = (int)((playerCombatant.getStat("STR") - Enemy.getStat("CON") /2) * Math.random());
		if(Math.signum(damage)!=-1.0) {
			//Enemy.health-=damage;
		}
		else {}
	}

	public void rest(PlayerCharacter Enemy) {
		int recovery = (int)((playerCombatant.getStat("CON")) * Math.random());
		if(Math.signum(recovery)!=-1.0) {
			System.out.println(playerCombatant.getName() + " rested for " + recovery + " HP.");
			//this.health+=recovery;
		}
		else {System.out.println(playerCombatant.getName() + " was blocked from resting.");}
	}

	public void startBattle(PlayerCharacter Enemy) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(playerCombatant.isAlive() && Enemy.isAlive()) {
			int battleChoice = playerCombatant.choose("Select an action!", new String[] {"Attack","Rest"});
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
	

}
