package models;

// Test-class for the battle system

//import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void test() {
        Player Player = new Player();
        Player.setName("Test Player:");
        Player.setStat("STR",20);
        System.out.println(Player.getName() + ": " + Player.getStat("STR") + " STR");
        
        models.Player TestEnemy = new Player();
        //DumbLoser.setName("Test Enemy:");
        //DumbLoser.setStat("STR", 20);

        //Player.startBattle(DumbLoser);
        
    }

}
