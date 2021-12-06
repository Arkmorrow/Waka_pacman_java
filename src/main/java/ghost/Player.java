package ghost;

import processing.core.PImage;


public class Player extends GameElements{
    /** 
    * player speed
    */
    protected int speed;
    private int currentspeed;
    private int lives;
    private PImage playerC;
    private PImage playerD;
    private PImage playerL;
    private PImage playerR;
    private PImage playerU;

    /** 
    * Constructor for player , requires x, y and all player iamges
    * initial all player setting
    * @param x x position
    * @param y y position
    * @param playerC player closed mouth image
    * @param playerD player down mouth image
    * @param playerL player left mouth image
    * @param playerR player right mouth image
    * @param playerU player up mouth image
    * @param speed player speed
    * @param lives player lives
    */
    public Player(int x, int y,PImage playerC, PImage playerD, PImage playerL, 
                PImage playerR, PImage playerU, int speed, int lives){

        super(x, y, playerL);
        this.speed = speed;
        this.lives = lives;
        this.playerC = playerC;
        this.playerD = playerD;
        this.playerL = playerL;
        this.playerR = playerR;
        this.playerU = playerU;
        xBefore = x;
        yBefore = y;
        currentspeed = speed;
    }

    /** 
    * right move for player
    */
    public void rightMove(){
        direction = "right";
        xBefore = x - 1;
        x += speed;
        super.UpdateImage(playerR);
    }

    /** 
    * right move for player
    */
    public void leftMove(){
        direction = "left";
        xBefore = x + 1;
        x -= speed;
        super.UpdateImage(playerL);
    }

    /** 
    * up move for player
    */
    public void upMove(){
        direction = "top";
        yBefore = y + 2;
        y -= speed;
        super.UpdateImage(playerU);
    }

    /** 
    * down move for player
    */
    public void downMove(){
        direction = "down";
        yBefore = y - 2;
        y += speed;
        super.UpdateImage(playerD);
    }

    /** 
    * movement stop
    */
    public void stop(){
        x = xBefore;
        y = yBefore;
        speed = 0;
    }

    /** 
    * start movement when keyboard pressed
    */
    public void move(){
        speed = currentspeed;
    }

    /**
    * get player close mouth image
    * @return an player closed mouth image
    */
    public PImage getCloseImage(){
        return playerC;
    }

    /** 
    * @return player lives
    */
    public int getLives(){
        return lives;
    }

    /** 
    * Player lost lives by 1
    */
    public void lostLives(){
        lives-=1;
    }

    /** 
    * Reset the player position
    */
    public void reset(){
        this.setX(this.startX);
        this.setY(this.startY);
        this.xBefore = this.startX;
        this.yBefore = this.startY;
    }
}