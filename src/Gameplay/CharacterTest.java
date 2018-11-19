package Gameplay;

/*
Has edited this:
- Matt
*/

//import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class CharacterTest {

    @Test
    void test() {
        Character Player = new Character();
        Player.setName("Test Player:");
        Player.setStat("STR",20);
        System.out.println(Player.getName() + ": " + Player.getStat("STR") + " STR");
        
        Character TestEnemy = new Character();
        //DumbLoser.setName("Test Enemy:");
        //DumbLoser.setStat("STR", 20);

        //Player.startBattle(DumbLoser);
        
    }

}
