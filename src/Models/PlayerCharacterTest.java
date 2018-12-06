package Models;

/*
Has edited this:
- Matt
*/

//import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PlayerCharacterTest {

    @Test
    void test() {
        PlayerCharacter Player = new PlayerCharacter();
        Player.setName("Test Player:");
        Player.setStat("STR",20);
        System.out.println(Player.getName() + ": " + Player.getStat("STR") + " STR");
        
        PlayerCharacter TestEnemy = new PlayerCharacter();
        //DumbLoser.setName("Test Enemy:");
        //DumbLoser.setStat("STR", 20);

        //Player.startBattle(DumbLoser);
        
    }

}
