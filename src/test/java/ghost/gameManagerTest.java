package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class gameManagerTest {

    @Test
    /**
    * Test if the class working corroctly
    */
    public void loadImageTest() {

        //set up a app for testing
        App app = new App();
        PApplet.runSketch(new String[] {""}, app);
        app.noLoop(); // This stops draw() being called automatically

        //create a new gameManager object
        GameManager game = new GameManager();

        //check if load image mathod working
        assertTrue(game.loadimage(app));
    }

    @Test
    /**
    * Test if game store position correctly
    */
    public void storePositionTest() {

        //set up a app for testing
        App app = new App();
        PApplet.runSketch(new String[] {""}, app);
        app.noLoop(); // This stops draw() being called automatically

        //create a new gameManager object
        GameManager game = new GameManager();
        game.storePosition(app);

        //check if store wall position correctly
        assertTrue(game.wallPosition.size() > 0);

        //check if store fruit position correctly
        assertTrue(game.fruitPosition.size() > 0);

        //check if store ghost position correctly
        assertTrue(game.ghostsPosition.size() > 0);
    }

    @Test
    /**
    * Test main running part of the game
    */
    public void runTest(){

        //set up a app for testing
        App app = new App();
        PApplet.runSketch(new String[] {""}, app);
        app.noLoop(); // This stops draw() being called automatically

        //check when game first start
        GameManager game = new GameManager();
        game.sodaMode = true;
        game.run(app);
        assertTrue(game.gamestart == false);

        //check when game lost
        game.player.lostLives();
        game.player.lostLives();
        game.player.lostLives();
        game.debugMode = 1;
        game.superMode = true;
        game.run(app);
        assertTrue(game.gameOver);
        
        //check when game win
        game.gamestart = true;
        game.run(app);
        game.fruitPosition.removeAll(game.fruitPosition);
        game.run(app);
        assertTrue(game.win);
        assertTrue(game.gameOver);

        //check if game restart
        game.restartTime = 599;
        game.run(app);
        assertTrue(game.win == false);
        assertTrue(game.gameOver == false);
        assertTrue(game.mode == 0);
        assertTrue(game.modeCount == 0);
        assertTrue(game.superMode == false);
        assertTrue(game.sodaMode == false);
        assertTrue(game.frightenedCount == 0);
        assertTrue(game.sodaCount == 0);

        // check frame aniamtion
        game.framecount = 18;
        game.run(app);
        game.framecount = 16;
        game.run(app);
        assertTrue(game.framecount == 1);

        //cehck if game can run multiple times with no error
        for (int i = 0; i < 200; i++){
            game.run(app);
        }


    }

    @Test
    /**
    * Test if check cosslision is working correctly
    */
    public void checkCollisionTest() {

        //set up a app for testing
        App app = new App();
        PApplet.runSketch(new String[] {""}, app);
        app.noLoop(); // This stops draw() being called automatically

        //create a new gameManager object
        GameManager game = new GameManager();
        game.storePosition(app);
        
        //check  when no collision between wall and player
        assertTrue(game.player.speed == 1);
    }

    @Test
    /**
    * Test the keymovement for the player
    */
    public void keyMovementTest() {

        //set up a app for testing
        App app = new App();
        PApplet.runSketch(new String[] {""}, app);
        app.noLoop(); // This stops draw() being called automatically

        //create a new gameManager object
        GameManager game = new GameManager();
        game.player = new Player(1, 1, null, null, null, null, null, 1, 3);

        //check if keypressed
        app.keyPressed = true;
        game.keyMovement(app);
        assertTrue(game.player.speed == 1);

        //check keycode for reight move
        app.keyCode = 39;
        game.keyMovement(app);
        assertTrue(game.player.x == 2);

        //check keycode for left move
        app.keyCode = 37;
        game.keyMovement(app);
        assertTrue(game.player.x == 1);

        //check keycode for up move
        app.keyCode = 38;
        game.keyMovement(app);
        assertTrue(game.player.y == 0);

        //check keycode for down move
        app.keyCode = 40;
        game.keyMovement(app);
        assertTrue(game.player.y == 1);

        //check the debug mode and store the last keycode before start debug
        app.keyCode = 0;
        game.debugMode = 0;
        game.keyMovement(app);
        assertTrue(game.debugMode == 1);
        assertTrue(game.beforePressed == 40);

        //check if debug mode stop
        app.keyCode = 0;
        game.keyMovement(app);
        assertTrue(game.debugMode == 0);

    }

    @Test
    /**
    * Test the ghost move in differnet mode
    */
    public void ghostmoveTest() {

        //set up a app for testing
        App app = new App();
        PApplet.runSketch(new String[] {""}, app);
        app.noLoop(); // This stops draw() being called automatically

        //create a new gameManager object
        GameManager game = new GameManager();
        PImage ghostImage = app.loadImage("src/main/resources/chaser.png");
        game.player = new Player(1, 1, ghostImage, ghostImage, ghostImage, ghostImage, ghostImage, 1, 3);


        //check if game.mode is 3
        game.superMode = false;
        game.ghostsPosition.add(new Ambusher(5, 1, ghostImage, ghostImage , "Ambusher"));
        game.ghostsPosition.add(new Chaser(5, 1, ghostImage, ghostImage , "Chaser"));
        game.ghostsPosition.add(new Ignorant(5, 1, ghostImage, ghostImage , "Ignorant"));
        game.ghostsPosition.add(new Whim(5, 1, ghostImage, ghostImage , "Whim"));
        game.wallPosition.add(new Wall(10, 1, null));
        game.speed = 1;
        game.mode = 3;
        game.ghostmove(app);
        assertTrue(game.ghostsPosition.get(0).x == 5);
        assertTrue(game.ghostsPosition.get(1).x == 5);
        assertTrue(game.ghostsPosition.get(2).x == 5);
        assertTrue(game.ghostsPosition.get(3).x == 5);


        //check if on scatter mode for four different ghost type
        game.mode = 1;
        game.debugMode = 1;
        game.ghostmove(app);
        assertTrue(game.ghostsPosition.get(0).x == 6);
        assertTrue(game.ghostsPosition.get(1).x == 4);
        assertTrue(game.ghostsPosition.get(2).y == 2);
        assertTrue(game.ghostsPosition.get(3).y == 2);

        //check if on chase mode for four different ghost type
        game.mode = 2;
        game.debugMode = 1;
        game.ghostmove(app);
        assertTrue(game.ghostsPosition.get(0).x == 7);
        assertTrue(game.ghostsPosition.get(1).x == 3);
        assertTrue(game.ghostsPosition.get(2).y == 3);
        assertTrue(game.ghostsPosition.get(3).y == 2);

        //check if super mode on, ghost move random for four different ghost type
        game.superMode = true;
        game.mode = 0;
        game.ghostmove(app);
        assertTrue(game.ghostsPosition.get(0).x != 5);
        assertTrue(game.ghostsPosition.get(1).y != 5);
        assertTrue(game.ghostsPosition.get(2).y != 5);
        assertTrue(game.ghostsPosition.get(3).y != 5);
        
    }

    @Test
    /**
    * Test check game mode method
    */
    public void checkmodeTest() {

        //set up a app for testing
        App app = new App();
        PApplet.runSketch(new String[] {""}, app);
        app.noLoop(); // This stops draw() being called automatically

        //create a new gameManager object
        GameManager game = new GameManager();
        game.storePosition(app);

        //check super mode when count time is 0 and not
        game.superMode = true;
        game.frightenedCount = 0;
        game.checkmode();
        assertTrue(game.superMode == false);
        
        //check super mode time counter
        game.superMode = true;
        game.frightenedCount = 1;
        game.checkmode();
        assertTrue(game.superMode == true);
        game.checkmode();
        assertTrue(game.superMode == false);

        //check soda mode when count time is 0 and not
        game.sodaMode = true;
        game.sodaCount = 0;
        game.checkmode();
        assertTrue(game.sodaMode == false);
        
        //check soda mode time counter
        game.sodaMode = true;
        game.sodaCount = 1;
        game.checkmode();
        assertTrue(game.sodaMode == true);
        game.checkmode();
        assertTrue(game.sodaMode == false);

        //check if both mode are on, do super mode
        game.sodaMode = true;
        game.superMode = true;
        game.frightenedCount = 1;
        game.sodaCount = 1;
        game.checkmode();
        assertTrue(game.frightenedCount == 0 && game.sodaCount == 1);

        //check mode for ghost
        game.modeLength = new int[2];
        game.modeTime = 0;
        game.mode = 0;
        game.modeLength[0] = 1;
        game.modeLength[1] = 1;
        game.checkmode();
        assertTrue(game.mode == 1);

        //check if mode is 3
        game.mode = 2;
        game.modeTime = 0;
        game.checkmode();
        assertTrue(game.mode == 1);
    }
}