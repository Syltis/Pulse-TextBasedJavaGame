package Models;

/*
Has edited this:
- Matt
*/

//import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PlayerBeingTest {

    @Test
    void test() {
        PlayerBeing Player = new PlayerBeing();
        Player.setName("Test Player:");
        Player.setStat("STR",20);
        System.out.println(Player.getName() + ": " + Player.getStat("STR") + " STR");
        
        PlayerBeing TestEnemy = new PlayerBeing();
        //DumbLoser.setName("Test Enemy:");
        //DumbLoser.setStat("STR", 20);

        //Player.startBattle(DumbLoser);
        
    }

}
