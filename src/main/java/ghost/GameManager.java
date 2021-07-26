package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

import java.lang.Math; 

import java.util.*;

public class GameManager{
    
    //PImage object
    private PImage hwall;
    private PImage vwall;
    private PImage dlwall;
    private PImage drwall;
    private PImage ulwall;
    private PImage urwall;
    private PImage fruit;
    private PImage superFruit;
    private PImage soda;
    private PImage sodaGhost;
    private PImage frightened;
    private PImage playerC;
    private PImage playerD;
    private PImage playerL;
    private PImage playerR;
    private PImage playerU;
    private PImage Ambusher;
    private PImage Chaser;
    private PImage Ignorant;
    private PImage Whim;

    /** 
    * player
    */
    protected Player player;

    /** 
    * player speed
    */
    protected int speed;

    /** 
    * framecount for player animation
    */
    protected int framecount;
    private PFont myfont;

    /** 
    * boolean statement if game over
    */
    protected boolean gameOver;

    /** 
    * boolean statement if game start
    */
    protected boolean gamestart;

    /** 
    * boolean statement if game win
    */
    protected boolean win;

    /** 
    * boolean statement if game need restart
    */
    protected boolean restart;
    /** 
    * Time for game to restart
    */
    protected int restartTime;

    /** 
    * debug mode state
    */
    protected int debugMode;

    /** 
    * the key before the debug mode
    */
    protected int beforePressed;

    /** 
    * boolean statement if game is in super mode
    */
    protected boolean superMode;

    /** 
    * super mode time counter
    */
    protected int frightenedCount;

    /** 
    * super time time
    */
    protected int frightenedTime;

    /** 
    * boolean statement if game is in soda mode
    */
    protected boolean sodaMode;

    /** 
    * soda mode time counter
    */
    protected int sodaCount;

    /** 
    * soda mode time
    */
    protected int sodaTime;

    /** 
    * array of the mode time
    */
    protected int[] modeLength;

    /** 
    * mode time
    */
    protected int modeTime;
    /** 
    * mode state
    */
    protected int mode;

    /** 
    * mode time counter
    */
    protected int modeCount;

    /** 
    * List of wall position
    */
    protected List<Wall> wallPosition;

    /** 
    * List of fruit position
    */
    protected List<Fruit> fruitPosition;

    /** 
    * List of ghost position
    */
    protected List<Ghosts> ghostsPosition;

    private List<Ghosts> savedGhostsPosition;

    private String configName;

    /** 
    * Constructor for a gameManager, no requires
    * initial all game state
    */
    public GameManager(){
        wallPosition = new ArrayList<Wall>();
        fruitPosition = new ArrayList<Fruit>();
        ghostsPosition = new ArrayList<Ghosts>();
        savedGhostsPosition = new ArrayList<Ghosts>();
        framecount = 1;
        gameOver = false;
        gamestart = true;
        win = false;
        restart = false;
        restartTime = 0;
        debugMode = 0;
        mode = 0;
        modeCount = 0;
        modeTime = 0;
        frightenedCount = 0;
        superMode = false;
        sodaMode = false;
        sodaCount = 0;
        configName = "config.json";
    }

    /**
    * load all image from local file to app
    * @param app app
    * @return success
    */
    public boolean loadimage(PApplet app){
        
        // Load images
        this.hwall = app.loadImage("src/main/resources/horizontal.png");
        this.vwall = app.loadImage("src/main/resources/vertical.png");
        this.ulwall = app.loadImage("src/main/resources/upLeft.png");
        this.urwall = app.loadImage("src/main/resources/upRight.png");
        this.dlwall = app.loadImage("src/main/resources/downLeft.png");
        this.drwall = app.loadImage("src/main/resources/downRight.png");
        this.fruit = app.loadImage("src/main/resources/fruit.png");
        this.playerC = app.loadImage("src/main/resources/playerClosed.png");
        this.playerD = app.loadImage("src/main/resources/playerDown.png");
        this.playerL = app.loadImage("src/main/resources/playerLeft.png");
        this.playerR = app.loadImage("src/main/resources/playerRight.png");
        this.playerU = app.loadImage("src/main/resources/playerUp.png");
        this.Ambusher = app.loadImage("src/main/resources/ambusher.png");
        this.Chaser = app.loadImage("src/main/resources/chaser.png");
        this.Ignorant = app.loadImage("src/main/resources/ignorant.png");
        this.Whim = app.loadImage("src/main/resources/whim.png");
        this.myfont = app.createFont("src/main/resources/PressStart2P-Regular.ttf",32);
        this.superFruit = app.loadImage("src/main/resources/superFruit.png");
        this.frightened = app.loadImage("src/main/resources/frightened.png");
        this.soda = app.loadImage("src/main/resources/soda.png");
        this.sodaGhost = app.loadImage("src/main/resources/sodaGhost.png");

        return true;
    }

    /**
    * load all image from local file to app
    * and store all the element position to the list
    * @param app app
    */
    public void storePosition(PApplet app){

        // load images
        this.loadimage(app);

        // get map values
        String[] content = configRead.readMap(configRead.readJson(configName, "map"));

        // get speed
        speed = Integer.parseInt(configRead.readJson(configName, "speed"));

        // get lives
        int lives = Integer.parseInt(configRead.readJson(configName, "lives"));

        //clean up the modelength and store the modelength
        String modelength = configRead.readJson(configName, "modeLengths");
        char[] clean = modelength.toCharArray();
        char[] new_clean = new char[clean.length-2];
        int a = 0;
        for (int i=1;i<clean.length -1;i++) { 
            new_clean[a] = clean[i];
            a+=1;
        } 
        modelength = new String(new_clean);
        String[] splited = modelength.split(",");
        modeLength = new int[splited.length];
        for (int i=0;i<splited.length;i++){
            modeLength[i] = Integer.parseInt(splited[i]);
        }

        // get super mode time
        if (configRead.readJson(configName, "frightenedLength").equals("")){
            frightenedTime = 0;
        } else {
            frightenedTime = Integer.parseInt(configRead.readJson(configName, "frightenedLength"));
        }

        // get soda mode time
        if (configRead.readJson(configName, "sodaLength").equals("")){
            sodaTime = 0;
        } else {
            sodaTime = Integer.parseInt(configRead.readJson(configName, "frightenedLength"));
        }
        
        //initial start move
        app.keyCode = 37;

        int row = 0; //y position
        int column = 0; //x position

        //loop through content and store position
        for (String i : content){

            if (i.equals("1")){
                wallPosition.add(new Wall(column, row, hwall));
            } else if (i.equals("2")){
                wallPosition.add(new Wall(column, row, vwall));
            } else if (i.equals("3")){
                wallPosition.add(new Wall(column, row, ulwall));
            } else if (i.equals("4")){
                wallPosition.add(new Wall(column, row, urwall));
            } else if (i.equals("5")){
                wallPosition.add(new Wall(column, row, dlwall));
            } else if (i.equals("6")){
                wallPosition.add(new Wall(column, row, drwall));
            } else if (i.equals("7")){
                fruitPosition.add(new Fruit(column, row, fruit, "fruit"));
            }  else if (i.equals("8")){
                fruitPosition.add(new Fruit(column, row, superFruit, "superFruit"));
            } else if (i.equals("9")){
                fruitPosition.add(new Fruit(column, row, soda, "soda"));
            } else if (i.equals("a")){
                ghostsPosition.add(new Ambusher(column - 6, row -6, Ambusher, frightened , "Ambusher"));
                savedGhostsPosition.add(new Ambusher(column - 6, row -6, Ambusher, frightened , "Ambusher"));
            } else if (i.equals("c")){
                ghostsPosition.add(new Chaser(column - 6, row -6, Chaser, frightened, "Chaser"));
                savedGhostsPosition.add(new Chaser(column - 6, row -6, Chaser, frightened, "Chaser"));
            } else if (i.equals("i")){
                ghostsPosition.add(new Ignorant(column - 6, row -6, Ignorant, frightened, "Ignorant"));
                savedGhostsPosition.add(new Ignorant(column - 6, row -6, Ignorant, frightened, "Ignorant"));
            } else if (i.equals("w")){
                ghostsPosition.add(new Whim(column - 6, row -6, Whim, frightened, "Whim"));
                savedGhostsPosition.add(new Whim(column - 6, row -6, Whim, frightened, "Whim"));
            } else if (i.equals("p")){
                player = new Player(column, row - 6, playerC, playerD, playerL, 
                playerR, playerU, speed, lives);
            }

            column += 16;

            if (column == 448){
                row+= 16;
                column = 0;
            }
        } 
    }
    
    /**
    * run the main logic of the game, 
    * check if game need restart
    * check if game start
    * check the mode of the game
    * @param app app
    */
    public void run(PApplet app){

        //restart
        if (gameOver){
            app.textFont(myfont);
            app.textAlign(app.CENTER, app.CENTER);
            if (win){
                app.text("YOU WIN", app.width/2, app.height/2-100);
            } else{
                app.text("GAME OVER", app.width/2, app.height/2-100);
            }

            restartTime+=1;

            if (restartTime == 600){
                gameOver = false;
                win = false;
                gamestart = true;
                restartTime = 0;
                mode = 0;
                modeCount = 0;
                modeTime = 0;
                superMode = false;
                sodaMode =false;
                frightenedCount = 0;
                sodaCount = 0;
                return;
            }
        }

        //game start
        if (gamestart){
            this.storePosition(app);
            gamestart = false;
        }

        //move
        this.keyMovement(app);
        
        if (fruitPosition.size() == 0){
            win = true;
            gameOver = true;
            return;
        }

        if (player.getLives() == 0){
            gameOver = true;
            return;
        }

        
        for (Wall wall : wallPosition){
            app.image(wall.getImage(), wall.getX(), wall.getY());
        }

        for (Fruit fruit : fruitPosition){
            app.image(fruit.getImage(), fruit.getX(), fruit.getY());
        }

        for (Ghosts ghost : ghostsPosition){

            if (superMode){
                app.image(ghost.frightenedImage, ghost.getX(), ghost.getY());
            } else if (sodaMode){
                app.image(sodaGhost, ghost.getX(), ghost.getY());
            } else {
                app.image(ghost.getImage(), ghost.getX(), ghost.getY());
            }
            
        }

        //animation
        if (framecount <= 8){
            
            app.image(playerC, player.getX(), player.getY());
        }
        if (framecount <= 16){
            app.image(player.getImage(), player.getX(), player.getY());
        }
        framecount+=1;
        if (framecount == 17){
            framecount = 1;
        }

        //lives
        for (int i = 0;i<player.getLives();i++){
            app.image(playerR, 6 + i * 26, 545);
        }

        this.checkmode();
        this.ghostmove(app);
        this.checkCollision();
        
    }

    /**
    * check the collision between two different gam elements
    */
    public void checkCollision(){

        int playerRight = player.getX() + player.getWidth()/2 + 6;
        int playerBottom = player.getY() + player.getHeight()/2 + 6;
        int playerx = player.getX();
        int playery = player.getY();

        //check collision between player and wall
        for (Wall wall : wallPosition){
            
            int wallLeft = wall.getX();
            int wallRight = wall.getX() + wall.getWidth()/2;
            int wallTop = wall.getY();
            int wallBottom = wall.getY() + wall.getHeight()/2 ;

            if (playerRight > wallLeft && playerx  < wallRight && playerBottom  > wallTop && playery < wallBottom){
                player.stop();
                return;
            }
        }

        //check collision between fruits and other elements
        for (Fruit fruits : fruitPosition){
            int fruitsLeft = fruits.getX();
            int fruitsRight = fruits.getX() + fruits.getWidth()/2;
            int fruitsTop = fruits.getY();
            int fruitsBottom = fruits.getY() + fruits.getHeight()/2 ;

            if (playerRight > fruitsLeft && playerx < fruitsRight && playerBottom > fruitsTop && playery < fruitsBottom){

                if (fruits.checkSuper()){
                    superMode = true;
                    frightenedCount = frightenedTime*60;
                }

                if (fruits.checkSoda()){
                    sodaMode = true;
                    sodaCount = sodaTime*60;
                }
                fruitPosition.remove(fruits);
                return;
            }
        }

        //check collision between ghost and player
        for (Ghosts ghosts : ghostsPosition){
            int ghostsLeft = ghosts.getX();
            int ghostsRight = ghosts.getX() + ghosts.getWidth()/2 + 6;
            int ghostsTop = ghosts.getY() ;
            int ghostsBottom = ghosts.getY() + ghosts.getHeight()/2 + 6;

            if (playerRight > ghostsLeft && playerx < ghostsRight && playerBottom > ghostsTop && playery < ghostsBottom){

                if (superMode){
                    ghostsPosition.remove(ghosts);
                    return;
                }

                for (Ghosts ghost : ghostsPosition){
                    ghost.reset();
                }
                
                player.lostLives();
                player.reset();
                ghostsPosition = savedGhostsPosition;
                return;
            }
        }
    }

    /**
    * check the key movement
    * @param app app
    */
    public void keyMovement(PApplet app){
        //move
        if (app.keyPressed){
            player.move();
        }

        if (app.keyCode == 39){
            player.rightMove();
            beforePressed = app.keyCode;
        } else if (app.keyCode == 37){
            player.leftMove();
            beforePressed = app.keyCode;
        } else if (app.keyCode == 38){
            player.upMove();
            beforePressed = app.keyCode;
        } else if (app.keyCode == 40){
            player.downMove();
            beforePressed = app.keyCode;
        }

        if (app.keyCode == 0){
            debugMode+=1;
            
        }
        app.keyCode = beforePressed;

        if (debugMode >1){
            debugMode = 0;
        }
        
    }

    /**
    * ghost different movement in different mode
    * @param app app
    */
    public void ghostmove(PApplet app){

        //if in super mode, move random
        if (superMode){

            for (Ghosts ghost : ghostsPosition){
                ghost.randomMove(speed, wallPosition);
            }
            return;
        }

        for (Ghosts ghost : ghostsPosition){

            if (mode == 1){
                ghost.scatterMode(speed, wallPosition);
            } else if (mode == 2){
                ghost.chaseMode(speed, wallPosition, player, ghostsPosition);
            }

            if (debugMode == 1){
                if (mode ==1){
                    ghost.drawline(app);
                } else if (mode == 2){
                    ghost.drawPlayerline(app, player);
                }
            } 
        }
    }

    /**
    * check the game mode
    */
    public void checkmode(){

        //check super mode counter
        if (frightenedCount == 0){
            superMode = false;
        }

        //check soda mode counter
        if (sodaCount == 0){
            sodaMode = false;
        }

        //if in super mode, decrease counter
        if (superMode){
            frightenedCount-=1;
            return;
        }

        //if in soda mode, decrease counter
        if (sodaMode){
            sodaCount-=1;
        }

        if (modeTime == 0){
            modeTime = modeLength[modeCount] * 60;
            modeCount+=1;
            mode +=1;

            if (modeCount == modeLength.length){
                modeCount = 0;
            }

            if (mode == 3){
                mode = 1;
            }
        }

        modeTime -= 1;
    }
}