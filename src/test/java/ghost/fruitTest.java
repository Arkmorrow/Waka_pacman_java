package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class fruitTest {


    @Test
    /**
    * Test if the class working corroctly
    */
    public void ClassTest() {

        //check the basic class
        GameElements test = new Fruit (1,1, null, "fruit");
        assertTrue(test.getX() == 1);
        assertTrue(test.getY() == 1);
        assertNull(test.getImage());
    }

    @Test
    /**
    * Test if two checking fruit type is working
    */
    public void checkSuperTest() {

        //check the basic fruit type
        Fruit test = new Fruit (1,1, null, "fruit");
        assertFalse(test.checkSoda());
        assertFalse(test.checkSuper());

        //check the super fruit
        Fruit testSuper = new Fruit (1,1, null, "superFruit");
        assertTrue(testSuper.checkSuper());

        //check the soda
        Fruit testSoda = new Fruit (1,1, null, "soda");
        assertTrue(testSoda.checkSoda());
    }


}