package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;


public class GameElements {
    /** 
    * Horizontal position
    */
    protected int x;

    /** 
    * Vertical position
    */
    protected int y;

    /**
     * initial Horizontal position
     */
    protected int startX;

    /**
     * initial Vertical position
     */
    protected int startY;

    /**
     * Hertical position before move
     */
    protected int xBefore;

    /**
     * Vertical position before move
     */
    protected int yBefore;

    /**
     * The movement direction
     */
    protected String direction;

    /**
     * The image of the game element
     */
    protected PImage image;

    /** 
    * Constructor for a gameElements, initial
    * it x, y and image, and set the initial
    * direction to a empty string
    * @param x x position
    * @param y y position
    * @param image element image
    */
    public GameElements(int x, int y, PImage image){
        this.x = x;
        this.y = y;
        startX = x;
        startY = y;
        this.image = image;
        direction = "";
    }

    /**
    * return the hortical position of the game element
    * @return a int position
    */
    protected int getX(){
        return x;
    }

    /**
    * return the vertical position of the game element
    * @return a int position
    */
    protected int getY(){
        return y;
    }

    /**
    * return the image of the game element
    * @return a Pimage
    */
    protected PImage getImage(){
        return image;
    }

    /**
    * update the current image
    * @param image a new image
    */
    protected void UpdateImage(PImage image){
        this.image = image;
    }

    /**
    * return width of the element image
    * @return a int width of the element image
    */
    protected int getWidth(){

        if (image == null){
            return 0;
        }
        return this.image.width;
    }

    /**
    * return height of the element image
    * @return a int height of the element image
    */
    protected int getHeight(){

        if (image == null){
            return 0;
        }
        return this.image.height;
    }

    /**
    * update the vertical position
    * @param y new vertical position
    */
    protected void setY(int y){
        this.y = y;
    }

    /**
    * update the hortical position
    * @param x new hortical position
    */
    protected void setX(int x){
        this.x = x;
    } 
}