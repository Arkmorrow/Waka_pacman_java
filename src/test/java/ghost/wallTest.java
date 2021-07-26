package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class wallTest {


    @Test
    /**
    * Test if the class working corroctly
    */
    public void ClassTest() {

        //check the basic class
        GameElements test = new Wall (1,1, null);
        assertTrue(test.getX() == 1);
        assertTrue(test.getY() == 1);
        assertNull(test.getImage());

        //check update image method
        test.UpdateImage(null);
        assertNull(test.getImage());

        //check set x and y method
        test.setX(2);
        test.setY(2);
        assertTrue(test.getX() == 2);
        assertTrue(test.getY() == 2);

        //return 0 if null image
        assertTrue(test.getHeight() == 0);
        assertTrue(test.getWidth() == 0);
    }

}