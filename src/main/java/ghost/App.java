package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

public class App extends PApplet {
    
    /** 
    * Game window width
    */
    public static final int WIDTH = 448;

    /** 
    * Game window Height
    */
    public static final int HEIGHT = 576;

    /** 
    * Game object
    */
    private GameManager game;

    /** 
    * Constructor for app
    */
    public App() {
        //Set up your objects
    }

    /** 
    * set up of the game
    */
    public void setup() {
        frameRate(60);

        //create a new game object
        game = new GameManager();
    }

    /** 
    * game setting
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /** 
    * game draw
    */
    public void draw() {
        background(0, 0, 0);

        //start the game
        game.run(this);
    }

    /** 
    * main run
    * @param args array of string
    */
    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
