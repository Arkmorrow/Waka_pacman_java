package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class playerTest {


    @Test
    /**
    * Test if the class working corroctly
    */
    public void ClassTest() {

        //check the basic class
        Player player = new Player(1, 1, null, 
                                    null, null, null, null, 1, 3);

        //check if return player closed mouth image
        assertTrue(player.getCloseImage() == null);
    }

    @Test
    /**
    * Test if player lost live correctly
    */
    public void livesTest() {

        //check the basic class
        Player player = new Player(1, 1, null, 
                                    null, null, null, null, 1, 3);

        //check if return correct lives
        assertTrue(player.getLives() == 3);

        //check if player lost live correctly
        player.lostLives();
        assertTrue(player.getLives() == 2);
    }

    @Test
    /**
    * Test player movement
    */
    public void movementTest() {

        //check the basic class
        Player player = new Player(1, 1, null, 
                                    null, null, null, null, 1, 3);

        //check if player corrently move left
        player.leftMove();
        assertTrue(player.x == 0);

        //check if player corrently move right
        player.rightMove();
        assertTrue(player.x == 1);

        //check if player corrently move up
        player.upMove();
        assertTrue(player.y == 0);

        //check if player corrently move down
        player.downMove();
        assertTrue(player.y == 1);

        //check if speed is 0 weh stop
        player.stop();
        assertTrue(player.speed == 0);

        //check if speed is 1 weh move
        player.move();
        assertTrue(player.speed == 1);

        //check if player reset the position
        player.leftMove();
        player.reset();
        assertTrue(player.x == 1 && player.y == 1);
    }

}