package ghost;

import processing.core.PImage;

public class Wall extends GameElements{

    /** 
    * Constructor for wall
    * @param x x position
    * @param y y position
    * @param image wall image
    */
    public Wall(int x, int y,PImage image){
        super(x, y, image);
    }
}