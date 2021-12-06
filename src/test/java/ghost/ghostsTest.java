package ghost;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;

class ghostsTest {


    @Test
    /**
    * Test if the ghost movement
    */
    public void movementTest() {

        //set up new ghost
        List<Wall> testWall = new ArrayList<Wall>();
        testWall.add(new Wall(10, 1, null));
        Ghosts test = new Ambusher(1, 1, null, null , "Ambusher");

        //test top left move
        test.saveDirection();
        test.movement("topLeft", testWall);
        test.move(1);
        assertTrue(test.getX() == 2);

        //test right top move
        test.saveDirection();
        test.movement("rightTop", testWall);
        test.move(1);
        assertTrue(test.getX() == 3);

        //test left top move
        test.saveDirection();
        test.movement("leftTop", testWall);
        test.move(1);
        assertTrue(test.getY() == 0);

        //test bottom right move
        test.saveDirection();
        test.movement("bottomRight", testWall);
        test.move(1);
        assertTrue(test.getY() == -1);

        //test left bottom move
        test.saveDirection();
        test.movement("leftBottom", testWall);
        test.move(1);
        assertTrue(test.getX() == 2);

        //test bottom left move
        test.saveDirection();
        test.movement("bottomLeft", testWall);
        test.move(1);
        assertTrue(test.getY() == 0);

        //test top right move
        test.saveDirection();
        test.movement("topRight", testWall);
        test.move(1);
        assertTrue(test.getX() == 3);

        // test right bottom move
        test.saveDirection();
        test.movement("rightBottom", testWall);
        test.move(1);
        assertTrue(test.getY() == 1);

        //test if no direction
        test.movement("", testWall);
        assertTrue(test.getY() == 1 && test.getX() == 3);

        //test reset
        test.reset();
        assertTrue(test.getY() == 1 && test.getX() == 1);
    }

    @Test
    /**
    * Test if check distance between player and ghost correctly
    */
    public void checkPlayerPositionTest() {

        //create a new ghost object
        List<Wall> testWall = new ArrayList<Wall>();
        List<Ghosts> testGhosts = new ArrayList<Ghosts>();
        testWall.add(new Wall(10, 1, null));
        Ghosts test = new Ambusher(1, 2, null, null , "Ambusher");
        testGhosts.add(test);
        Player player = new Player(1, 1, null, null, null, null, null, 1, 3);

        //check the target is at topright move vertical position first
        String testString = test.checkPlayerPosition(player, testGhosts);
        assertTrue(testString.equals("topRight"));

        //check if at same position
        player = new Player(1, 2, null, null, null, null, null, 1, 3);
        testString = test.checkPlayerPosition(player, testGhosts);
        assertTrue(testString.equals(""));

        //check the target is at top left move horizontal position first
        test = new Ambusher(10, 4, null, null , "Ambusher");
        testString = test.checkPlayerPosition(player, testGhosts);
        assertTrue(testString.equals("leftTop"));

        //check the target is at right bottom move horizontal position first
        test = new Ambusher(-1, 1, null, null , "Ambusher");
        testString = test.checkPlayerPosition(player, testGhosts);
        assertTrue(testString.equals("rightBottom"));

        //check the target is at topleft move vertical position first
        test = new Ambusher(10, 13, null, null , "Ambusher");
        testString = test.checkPlayerPosition(player, testGhosts);
        assertTrue(testString.equals("topLeft"));

        //check the target is at bottom right move vertical position first
        test = new Ambusher(-2, -5, null, null , "Ambusher");
        testString = test.checkPlayerPosition(player, testGhosts);
        assertTrue(testString.equals("bottomRight"));

        //check the target is at left bottom move horizontal position first
        test = new Ambusher(5, 1, null, null , "Ambusher");
        testString = test.checkPlayerPosition(player, testGhosts);
        assertTrue(testString.equals("leftBottom"));

        //check the target is at bottom left move vertical position first
        test = new Ambusher(5, -10, null, null , "Ambusher");
        testString = test.checkPlayerPosition(player, testGhosts);
        assertTrue(testString.equals("bottomLeft"));
    }

    @Test
    /**
    * Test the choice for the ghost movement
    */
    public void checkchoiceTest() {


        //set up a app for testing
        App app = new App();
        PApplet.runSketch(new String[] {""}, app);
        app.noLoop(); // This stops draw() being called automatically

        //create a new ghost object
        PImage ghostImage = app.loadImage("src/main/resources/chaser.png");
        PImage wallImage = app.loadImage("src/main/resources/vertical.png");
        List<Wall> testWall = new ArrayList<Wall>();
        Ghosts test = new Ambusher(10, 10, ghostImage, null , "Ambusher");

        // check left choices one
        test.setghostposition();
        test.choicesLeftone(testWall);
        assertTrue(test.getChanging() == 3);

        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.choicesLeftone(testWall);
        assertTrue(test.getChanging() == 2);

        //check when add left wall
        testWall.add(new Wall(1, 16, wallImage));
        testWall.add(new Wall(1, 32, wallImage));
        test.setghostposition();
        test.choicesLeftone(testWall);
        assertTrue(test.getChanging() == 4);

        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.choicesLeftone(testWall);
        assertTrue(test.getChanging() == 1);

        // check left choices two
        testWall.removeAll(testWall);
        test.setghostposition();
        test.choicesLefttwo(testWall);
        assertTrue(test.getChanging() == 4);

        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.choicesLefttwo(testWall);
        assertTrue(test.getChanging() == 2);

        //check when add left wall
        testWall.add(new Wall(1, 16, wallImage));
        testWall.add(new Wall(1, 32, wallImage));
        test.setghostposition();
        test.choicesLefttwo(testWall);
        assertTrue(test.getChanging() == 3);

        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.choicesLefttwo(testWall);
        assertTrue(test.getChanging() == 1);

        // check left choices three
        testWall.removeAll(testWall);
        test.setghostposition();
        test.choicesLeftthree(testWall);
        assertTrue(test.getChanging() == 2);

        //check when add left wall
        testWall.add(new Wall(1, 16, wallImage));
        testWall.add(new Wall(1, 32, wallImage));
        test.setghostposition();
        test.choicesLeftthree(testWall);
        assertTrue(test.getChanging() == 4);

        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.choicesLeftthree(testWall);
        assertTrue(test.getChanging() == 3);

        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.choicesLeftthree(testWall);
        assertTrue(test.getChanging() == 1);
        
        // check right choices one
        testWall.removeAll(testWall);
        test.setghostposition();
        test.choicesRightone(testWall);
        assertTrue(test.getChanging() == 1);

        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.choicesRightone(testWall);
        assertTrue(test.getChanging() == 4);

        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.choicesRightone(testWall);
        assertTrue(test.getChanging() == 3);

        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.choicesRightone(testWall);
        assertTrue(test.getChanging() == 2);

        // check right choices two
        testWall.removeAll(testWall);
        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.choicesRighttwo(testWall);
        assertTrue(test.getChanging() == 1);

        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.choicesRighttwo(testWall);
        assertTrue(test.getChanging() == 3);

        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.choicesRighttwo(testWall);
        assertTrue(test.getChanging() == 2);

        // check top choices one
        testWall.removeAll(testWall);
        //check when add left wall
        testWall.add(new Wall(1, 16, wallImage));
        testWall.add(new Wall(1, 32, wallImage));
        test.setghostposition();
        test.choicesTopone(testWall);
        assertTrue(test.getChanging() == 3);

        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.choicesTopone(testWall);
        assertTrue(test.getChanging() == 1);

        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.choicesTopone(testWall);
        assertTrue(test.getChanging() == 4);

        // check top choices two
        testWall.removeAll(testWall);
        test.setghostposition();
        test.choicesToptwo(testWall);
        assertTrue(test.getChanging() == 3);

        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.choicesToptwo(testWall);
        assertTrue(test.getChanging() == 1);

        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.choicesToptwo(testWall);
        assertTrue(test.getChanging() == 2);

        //check when add left wall
        testWall.add(new Wall(1, 16, wallImage));
        testWall.add(new Wall(1, 32, wallImage));
        test.setghostposition();
        test.choicesToptwo(testWall);
        assertTrue(test.getChanging() == 4);

        // check top choices three
        testWall.removeAll(testWall);
        test.setghostposition();
        test.choicesTopthree(testWall);
        assertTrue(test.getChanging() == 1);

        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.choicesTopthree(testWall);
        assertTrue(test.getChanging() == 3);

        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.choicesTopthree(testWall);
        assertTrue(test.getChanging() == 2);

        //check when add left wall
        testWall.add(new Wall(1, 16, wallImage));
        testWall.add(new Wall(1, 32, wallImage));
        test.setghostposition();
        test.choicesTopthree(testWall);
        assertTrue(test.getChanging() == 4);

        // check down choices one
        testWall.removeAll(testWall);
        //check when add left wall
        testWall.add(new Wall(1, 16, wallImage));
        testWall.add(new Wall(1, 32, wallImage));
        test.setghostposition();
        test.choicesDownone(testWall);
        assertTrue(test.getChanging() == 4);

        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.choicesDownone(testWall);
        assertTrue(test.getChanging() == 1);

        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.choicesDownone(testWall);
        assertTrue(test.getChanging() == 3);

        // check down choices two
        testWall.removeAll(testWall);
        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.choicesDowntwo(testWall);
        assertTrue(test.getChanging() == 4);

        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.choicesDowntwo(testWall);
        assertTrue(test.getChanging() == 2);

        //check when add left wall
        testWall.add(new Wall(1, 16, wallImage));
        testWall.add(new Wall(1, 32, wallImage));
        test.setghostposition();
        test.choicesDowntwo(testWall);
        assertTrue(test.getChanging() == 3);

        // check down choices three
        testWall.removeAll(testWall);
        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.choicesDownthree(testWall);
        assertTrue(test.getChanging() == 2);

        //check when add bleft wall
        testWall.add(new Wall(1, 16, wallImage));
        testWall.add(new Wall(1, 32, wallImage));
        test.setghostposition();
        test.choicesDownthree(testWall);
        assertTrue(test.getChanging() == 1);

        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.choicesDownthree(testWall);
        assertTrue(test.getChanging() == 3);

        //check move if direction is top
        test.setDirection("top");
        test.topLeftmove(testWall);
        test.bottomLeftmove(testWall);
        test.leftTopmove(testWall);
        test.rightTopmove(testWall);
        test.leftBottommove(testWall);
        test.rightBottommove(testWall);

        
        //check move if direction is left
        test.setDirection("left");
        test.topRightmove(testWall);
        test.bottomRightmove(testWall);
        test.rightTopmove(testWall);
        test.leftBottommove(testWall);
        test.rightBottommove(testWall);

        //check move if direction is top
        test.setDirection("top");
        test.topRightmove(testWall);

        //check move if direction is right
        test.setDirection("right");
        testWall.removeAll(testWall);
        test.setghostposition();

        //test top right move when direction is right
        test.topRightmove(testWall);
        assertTrue(test.getChanging() == 3);
        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.topRightmove(testWall);
        assertTrue(test.getChanging() == 1);
        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.topRightmove(testWall);
        assertTrue(test.getChanging() == 4);
        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.topRightmove(testWall);
        assertTrue(test.getChanging() == 2);

        //test left top move when direction is right
        testWall.removeAll(testWall);
        test.leftTopmove(testWall);
        assertTrue(test.getChanging() == 3);
        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.leftTopmove(testWall);
        assertTrue(test.getChanging() == 1);
        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.leftTopmove(testWall);
        assertTrue(test.getChanging() == 4);
        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.leftTopmove(testWall);
        assertTrue(test.getChanging() == 2);

        //test right top move when direction is right
        testWall.removeAll(testWall);
        test.rightTopmove(testWall);
        assertTrue(test.getChanging() == 1);
        //check when add right wall
        testWall.add(new Wall(31, 1, wallImage));
        testWall.add(new Wall(31, 17, wallImage));
        test.setghostposition();
        test.rightTopmove(testWall);
        assertTrue(test.getChanging() == 3);
        //check when add top wall
        testWall.add(new Wall(0, 0, wallImage));
        testWall.add(new Wall(16, 0, wallImage));
        test.setghostposition();
        test.rightTopmove(testWall);
        assertTrue(test.getChanging() == 4);
        //check when add bottom wall
        testWall.add(new Wall(1, 32, wallImage));
        testWall.add(new Wall(16, 32, wallImage));
        test.setghostposition();
        test.rightTopmove(testWall);
        assertTrue(test.getChanging() == 2);

        test.leftBottommove(testWall);
    }

        
}