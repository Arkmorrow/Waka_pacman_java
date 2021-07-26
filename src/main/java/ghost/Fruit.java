package ghost;


import processing.core.PImage;
import java.util.*;

public class Fruit extends GameElements{

    private String fruitType;

    /** 
    * Constructor for a fruit which set up
    * x, y, image and fruit type input
    * initial the fruit sttate
    * @param x x position
    * @param y y position
    * @param image element image
    * @param name fruit type
    */
    public Fruit(int x, int y,PImage image, String name){
        super(x, y, image);
        fruitType = name;
    }

    /**
    * check if the fruit type is a super fruit
    * @return a boolean statement of true or false
    */
    public boolean checkSuper(){

        if (this.fruitType.equals("superFruit")){
            return true;
        }

        return false;
    }

    /**
    * check if the fruit type is a soda
    * @return a boolean statement of true or false
    */
    public boolean checkSoda(){

        if (this.fruitType.equals("soda")){
            return true;
        }

        return false;
    }
}