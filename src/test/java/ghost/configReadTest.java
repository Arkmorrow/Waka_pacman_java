package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class configReadTest {

    @Test
    /**
    * Test the readTson method in configRead.java
    * Given a element input and it will return string value
    * in config.json, return empty string will not find
    */
    public void readJsonTest() {
        
        //check comman case for map
        assertTrue(configRead.readJson("config.json","map").equals("map.txt"));

        //check edge case when element can't find
        assertTrue(configRead.readJson("config.json","test").equals(""));

        //check edge case when input null
        assertTrue(configRead.readJson("config.json",null).equals(""));

        //check edge case when input null and if file not exit
        assertTrue(configRead.readJson("config",null).equals(""));
    }

    @Test
    /**
    * Fiven the input of map fille
    * Return every in the map file as array of string
    * if file not find, return null
    */
    public void readMapTest() {
        
        //check comman case for map that has total 1008 elements
        assertTrue(configRead.readMap("map.txt").length == 1008);

        //check edge case when file not find
        assertNull(configRead.readMap("test"));

    } 
}