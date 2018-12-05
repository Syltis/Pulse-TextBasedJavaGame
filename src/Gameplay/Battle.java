package untitledRPG;

import java.io.IOException;

public class Battle {
	private Character CombatantA;
	private Character CombatantB;
	
	Battle(Character CombatantA, Character CombatantB) throws IOException{
		this.CombatantA = CombatantA;
		this.CombatantB = CombatantB;
		executeBattle(this.CombatantA, this.CombatantB);
	}
    public void executeBattle(Character CombatantA, Character CombatantB) throws IOException {
    	//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	while(CombatantA.isAlive() && CombatantB.isAlive()) {
    		int battleChoice = CombatantA.choose("Select an action!", new String[] {"Attack","Defend"});
    		if(battleChoice == 1) CombatantA.rest(CombatantB);
    		if(battleChoice == 0){
    		if(CombatantA.isAlive()) CombatantA.attack(CombatantB);
    		if(CombatantB.isAlive() && CombatantA.isAlive()) CombatantB.attack(CombatantA);
    		}
    	}
    	if(CombatantA.getHealth()<0) {
    		System.out.println(CombatantA.getName()+" has defeated " + CombatantB.getName());
    	}
    	if(CombatantB.getHealth()<0) {
    		System.out.println(CombatantB.getName()+" has defeated " + CombatantA.getName());
    	}
    }
	

}
