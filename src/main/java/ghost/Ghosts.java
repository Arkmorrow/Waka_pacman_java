package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;
import java.lang.Math; 

public class Ghosts extends GameElements{
    /**
    * ghost movement direction changing
    */
    protected int changing;

    /**
    * ghost left position
    */
    protected int ghostsLeft;

    /**
    * ghost right position
    */
    protected int ghostsRight;

    /**
    * ghost top position
    */
    protected int ghostsTop;

    /**
    * ghost down position
    */
    protected int ghostsBottom;

    /**
    * ghost chase direction when in chase mode
    */
    protected String chaseDirection;

    /**
    * Horizontal distance between ghost and player
    */
    protected int Hdeistance;

    /**
    * Vertical distance between ghost and player
    */
    protected int Vdeistance;

    /**
    * Boolean value if Ignorant need to chase player or not
    */
    protected boolean iChase;

    /**
    * Ghost type
    */
    protected String ghostsType;

    /**
    * Image for frightened ghost
    */
    protected PImage frightenedImage;

    /** 
    * Constructor for ghosts
    * @param x x position
    * @param y y position
    * @param image wall image
    * @param frightened Image for frightened ghost
    * @param ghostsType Ghost type
    */
    public Ghosts(int x, int y,PImage image, PImage frightened, String ghostsType){
        super(x, y, image);
        startX = x;
        startY = y;
        frightenedImage = frightened;
        this.ghostsType = ghostsType;
        iChase = true;
        chaseDirection = "";
    }

    /** 
    * Scatter mode for ghost
    * @param speed ghost move speed
    * @param wallPosition list of wall positions
    */
    public void scatterMode(int speed, List<Wall> wallPosition){
        this.checkcollision(wallPosition);
        this.move(speed);
    }

    /** 
    * Chase mode for ghost
    * @param speed ghost move speed
    * @param wallPosition list of wall positions
    * @param player read in teh player
    * @param ghostPosition list of ghost position
    */
    public void chaseMode(int speed, List<Wall> wallPosition, Player player, List<Ghosts> ghostPosition){

        this.chasePlayer(wallPosition, player, ghostPosition);
        this.move(speed);
        
    }

    /** 
    * Ghost setting for chase the player
    * @param wallPosition list of wall positions
    * @param player read in teh player
    * @param ghostPosition list of ghost position
    */
    public void chasePlayer(List<Wall> wallPosition, Player player, List<Ghosts> ghostPosition){

        this.saveDirection();
        chaseDirection = this.checkPlayerPosition(player, ghostPosition);

        if (iChase == false){
            this.bottomLeftmove(wallPosition);
            return;
        }
        this.movement(chaseDirection, wallPosition);
    }

    /** 
    * Ghost movement
    * @param Direction ghost current move direction
    * @param wallPosition list of wall positions
    */
    public void movement(String Direction, List<Wall> wallPosition){

        if (Direction.equals("topLeft")){
            this.topLeftmove(wallPosition);
        } else if (Direction.equals("topRight")){
            this.topRightmove(wallPosition);
        }else if (Direction.equals("leftTop")){
            this.leftTopmove(wallPosition);
        } else if (Direction.equals("leftBottom")){
            this.leftBottommove(wallPosition);
        } else if (Direction.equals("rightTop")){
            this.rightTopmove(wallPosition);
        } else if (Direction.equals("rightBottom")){
            this.rightBottommove(wallPosition);
        } else if (Direction.equals("bottomLeft")){
            this.bottomLeftmove(wallPosition);
        } else if (Direction.equals("bottomRight")){
            this.bottomRightmove(wallPosition);
        }
    }

    /** 
    * Ghost random movement
    * @param speed ghost move speed
    * @param wallPosition list of wall positions
    */
    public void randomMove(int speed, List<Wall> wallPosition){
        this.saveDirection();
        this.movement("topLeft", wallPosition);
        this.move(speed);
    }

    /** 
    * get ghost x position
    * @return x position
    */
    public int getX(){
        return x;
    }

    /** 
    * get ghost y position
    * @return y position
    */
    public int getY(){
        return y;
    }

    /** 
    * check collision and move ghost
    * @param wallPosition list of wall positions
    */
    public void checkcollision(List<Wall> wallPosition){}

    /** 
    * reset ghost position
    */
    public void reset(){
        this.setX(this.startX);
        this.setY(this.startY);
        this.xBefore = this.startX;
        this.yBefore = this.startY;
    }

    /** 
    * check ghost collision between ghost and wall
    * @param ghostsRight ghost right position
    * @param ghostsLeft ghost left position
    * @param ghostsBottom ghost bottom position
    * @param ghostsTop ghost top position
    * @param wallPosition list of wall positions
    * @return a boolean if is collison or not
    */
    public boolean ghostcheck(int ghostsRight, int ghostsLeft, int ghostsBottom, int ghostsTop, List<Wall> wallPosition){

        for (Wall wall : wallPosition){
            
            int wallLeft = wall.getX();
            int wallRight = wall.getX() + wall.getWidth()/2;
            int wallTop = wall.getY();
            int wallBottom = wall.getY() + wall.getHeight()/2 ;

            if (ghostsRight > wallLeft && ghostsLeft < wallRight && ghostsBottom  > wallTop && ghostsTop < wallBottom){
                return true;
            }
        }
        return false;
    }

    /** 
    * check the horizontal and verical distance between ghost and player
    * @param player player object
    * @param ghostPosition list of ghost position
    */
    public void setChasePosition(Player player, List<Ghosts> ghostPosition){}

    /** 
    * check the horizontal and verical distance between ghost and player
    * @param player player object
    * @param ghostPosition list of ghost position
    * @return String of what ghost should move
    */
    public String checkPlayerPosition(Player player, List<Ghosts> ghostPosition){

        this.setChasePosition(player, ghostPosition);
        
        if (Hdeistance <= 0 && Vdeistance >= 0 && Math.abs(Hdeistance) > Math.abs(Vdeistance)){
            return "rightTop";
        } else if (Hdeistance <= 0 && Vdeistance <= 0 && Math.abs(Hdeistance) > Math.abs(Vdeistance)){
            return "rightBottom";
        } else if (Hdeistance <= 0 && Vdeistance >= 0 && Math.abs(Hdeistance) < Math.abs(Vdeistance)){
            return "topRight";
        } else if (Hdeistance > 0 && Vdeistance > 0 && Math.abs(Hdeistance) > Math.abs(Vdeistance)){
            return "leftTop";
        } else if (Hdeistance > 0 && Vdeistance > 0 && Math.abs(Hdeistance) < Math.abs(Vdeistance)){
            return "topLeft";
        } else if (Hdeistance < 0 && Vdeistance < 0 && Math.abs(Hdeistance) < Math.abs(Vdeistance)){
            return "bottomRight";
        } else if (Hdeistance > 0 && Vdeistance < 0 && Math.abs(Hdeistance) > Math.abs(Vdeistance)){
            return "leftBottom";
        } else if (Hdeistance > 0 && Vdeistance < 0 && Math.abs(Hdeistance) < Math.abs(Vdeistance)){
            return "bottomLeft";
        }

        return "";
    }
    
    /** 
    * save the direction everytime ghost move
    */
    public void saveDirection(){

        if (changing == 1){
            direction = "right";
        } else if (changing == 2){
            direction = "left";
        } else if (changing == 3){
            direction = "top";
        }else if (changing == 4){
            direction = "down";
        }
    }

    /** 
    * draw line between ghost and coroner when debug mode
    * @param app app
    */
    public void drawline(PApplet app){}

    /** 
    * draw line between ghost and player when debug mode
    * @param app app
    * @param player player object
    */
    public void drawPlayerline(PApplet app, Player player){}

    /** 
    * ghost move chosed
    * @param speed ghost move speed
    */
    public void move(int speed){
        if (changing == 1){
            //System.out.println("right");
            xBefore = x -1;
            x+=speed;

        } else if (changing ==2){
            //System.out.println("left");
            xBefore = x +1;
            x-=speed;
        } else if (changing ==3){
            //System.out.println("top");
            yBefore = y + 1;
            y-=speed;
        } else if (changing ==4){
            //System.out.println("down");
            yBefore = y - 1;
            y+=speed;
        }
    }

    /** 
    * set up four coroner position of the ghost
    */
    public void setghostposition(){
        ghostsLeft =x;
        ghostsRight = x + this.getWidth()/2 + 6;
        ghostsTop = y;
        ghostsBottom = y + this.getHeight()/2 + 6;
    }

    /** 
    * get changing
    * @return a int chaning
    */
    public int getChanging(){
        return this.changing;
    }

    /** 
    * set new direction
    * @param newDirection new direction string
    */
    public void setDirection(String newDirection){
        this.direction = newDirection;
    }

    /** 
    * the choice one that ghost can made when moving left
    * @param wallPosition list of wall positions
    */
    public void choicesLeftone(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight, ghostsLeft, ghostsBottom-3, ghostsTop-3, wallPosition) == false){
            changing = 3;
            return;
        }

        if (ghostcheck(ghostsRight -2, ghostsLeft -2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 2;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft, ghostsBottom +3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }

        if (ghostcheck(ghostsRight +2, ghostsLeft + 2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }
    }

    /** 
    * the choice two that ghost can made when moving left
    * @param wallPosition list of wall positions
    */
    public void choicesLefttwo(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight, ghostsLeft, ghostsBottom +3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }

        if (ghostcheck(ghostsRight -2, ghostsLeft -2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 2;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft, ghostsBottom-3, ghostsTop-3, wallPosition) == false){
            changing = 3;
            return;
        }

        if (ghostcheck(ghostsRight +2, ghostsLeft + 2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }
    }

    /** 
    * the choice two that ghost can made when moving left
    * @param wallPosition list of wall positions
    */
    public void choicesLeftthree(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight -2, ghostsLeft -2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 2;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft, ghostsBottom +3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }
        
        if (ghostcheck(ghostsRight, ghostsLeft, ghostsBottom-3, ghostsTop-3, wallPosition) == false){
            changing = 3;
            return;
        }

        if (ghostcheck(ghostsRight +2, ghostsLeft + 2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }
    }

    /** 
    * the choice one that ghost can made when moving down
    * @param wallPosition list of wall positions
    */
    public void choicesDownone(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight -2, ghostsLeft -2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 2;
            return;
        }

        if (ghostcheck(ghostsRight , ghostsLeft , ghostsBottom +3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }

        if (ghostcheck(ghostsRight + 2, ghostsLeft +2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom -3, ghostsTop -3, wallPosition) == false){
            changing = 3;
            return;
        }
    }

    /** 
    * the choice two that ghost can made when moving down
    * @param wallPosition list of wall positions
    */
    public void choicesDowntwo(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight +2, ghostsLeft +2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }

        if (ghostcheck(ghostsRight , ghostsLeft , ghostsBottom +3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }

        if (ghostcheck(ghostsRight - 2, ghostsLeft - 2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 2;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom -3, ghostsTop -3, wallPosition) == false){
            changing = 3;
            return;
        }
    }

    /** 
    * the choice three that ghost can made when moving down
    * @param wallPosition list of wall positions
    */
    public void choicesDownthree(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight , ghostsLeft , ghostsBottom +3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }

        if (ghostcheck(ghostsRight -2, ghostsLeft -2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 2;
            return;
        }

        if (ghostcheck(ghostsRight + 2, ghostsLeft +2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom -3, ghostsTop -3, wallPosition) == false){
            changing = 3;
            return;
        }
    }

    /** 
    * the choice one that ghost can made when moving top
    * @param wallPosition list of wall positions
    */
    public void choicesTopone(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight - 2, ghostsLeft -2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 2;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom-3, ghostsTop-3, wallPosition) == false){
            changing = 3;
            return;
        }
        
        if (ghostcheck(ghostsRight +2, ghostsLeft +2 , ghostsBottom , ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom +3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }
    }

    /** 
    * the choice two that ghost can made when moving top
    * @param wallPosition list of wall positions
    */
    public void choicesToptwo(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom-3, ghostsTop-3, wallPosition) == false){
            changing = 3;
            return;
        }

        if (ghostcheck(ghostsRight +2, ghostsLeft +2 , ghostsBottom , ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }

        if (ghostcheck(ghostsRight - 2, ghostsLeft -2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 2;
            return;
        }
        
        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom +3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }
    }

    /** 
    * the choice three that ghost can made when moving top
    * @param wallPosition list of wall positions
    */
    public void choicesTopthree(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight +2, ghostsLeft +2 , ghostsBottom , ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom-3, ghostsTop-3, wallPosition) == false){
            changing = 3;
            return;
        }

        if (ghostcheck(ghostsRight - 2, ghostsLeft -2 , ghostsBottom, ghostsTop, wallPosition) == false){
            changing = 2;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom +3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }
    }

    /** 
    * the choice one that ghost can made when moving right
    * @param wallPosition list of wall positions
    */
    public void choicesRightone(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight +2, ghostsLeft +2 , ghostsBottom , ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }

        if (ghostcheck(ghostsRight , ghostsLeft , ghostsBottom+3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }
        
        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom-3, ghostsTop-3, wallPosition) == false){
            changing = 3;
            return;
        }

        if (ghostcheck(ghostsRight -2, ghostsLeft -2 , ghostsBottom , ghostsTop , wallPosition) == false){
            changing = 2;
            return;
        }
    }

    /** 
    * the choice two that ghost can made when moving right
    * @param wallPosition list of wall positions
    */
    public void choicesRighttwo(List<Wall> wallPosition){
        if (ghostcheck(ghostsRight , ghostsLeft , ghostsBottom+3, ghostsTop +3, wallPosition) == false){
            changing = 4;
            return;
        }

        if (ghostcheck(ghostsRight +2, ghostsLeft +2 , ghostsBottom , ghostsTop, wallPosition) == false){
            changing = 1;
            return;
        }

        if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom-3, ghostsTop-3, wallPosition) == false){
            changing = 3;
            return;
        }

        if (ghostcheck(ghostsRight -2, ghostsLeft -2 , ghostsBottom , ghostsTop , wallPosition) == false){
            changing = 2;
            return;
        }
    }

    /** 
    * choose to top left move
    * @param wallPosition list of wall positions
    */
    public void topLeftmove(List<Wall> wallPosition){
        this.setghostposition();

        if (direction.equals("left")){
            this.choicesLeftone(wallPosition);
        }

        if (direction.equals("down")){
            this.choicesDownone(wallPosition);
        }
        
        if (direction.equals("top")){
            this.choicesTopone(wallPosition);
        }

        if (direction.equals("right")){
            this.choicesRightone(wallPosition);
        }
    }

    public void topRightmove(List<Wall> wallPosition){
        this.setghostposition();

        if (direction.equals("left")){
            this.choicesLeftone(wallPosition);
        }

        if (direction.equals("down")){
            this.choicesDowntwo(wallPosition);
        }
        
        if (direction.equals("top")){
            this.choicesToptwo(wallPosition);
        }

        if (direction.equals("right")){

            if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom-3, ghostsTop-3, wallPosition) == false){
                changing = 3;
                return;
            }

            if (ghostcheck(ghostsRight +2, ghostsLeft +2 , ghostsBottom , ghostsTop, wallPosition) == false){
                changing = 1;
                return;
            }

            if (ghostcheck(ghostsRight , ghostsLeft , ghostsBottom+3, ghostsTop +3, wallPosition) == false){
                changing = 4;
                return;
            }

            if (ghostcheck(ghostsRight -2, ghostsLeft -2 , ghostsBottom , ghostsTop , wallPosition) == false){
                changing = 2;
                return;
            }
        }
    }

    public void bottomLeftmove(List<Wall> wallPosition){

        this.setghostposition();

        if (direction.equals("left")){
            this.choicesLefttwo(wallPosition);
        }

        if (direction.equals("down")){
            this.choicesDownthree(wallPosition);
        }
        
        if (direction.equals("top")){
            this.choicesTopone(wallPosition);
        }

        if (direction.equals("right")){
            this.choicesRightone(wallPosition);
        }
    }

    public void bottomRightmove(List<Wall> wallPosition){

        this.setghostposition();

        if (direction.equals("left")){
            this.choicesLeftthree(wallPosition);
        }

        if (direction.equals("down")){
            this.choicesDownthree(wallPosition);
        }
        
        if (direction.equals("top")){
            this.choicesTopthree(wallPosition);
        }

        if (direction.equals("right")){
            this.choicesRighttwo(wallPosition);
        }
    }

    public void leftTopmove(List<Wall> wallPosition){

        this.setghostposition();

        if (direction.equals("left")){
            this.choicesLeftthree(wallPosition);
        }

        if (direction.equals("down")){
            this.choicesDownone(wallPosition);
        }
        
        if (direction.equals("top")){
            this.choicesTopone(wallPosition);
        }

        if (direction.equals("right")){

            if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom-3, ghostsTop-3, wallPosition) == false){
                changing = 3;
                return;
            }

            if (ghostcheck(ghostsRight +2, ghostsLeft +2 , ghostsBottom , ghostsTop, wallPosition) == false){
                changing = 1;
                return;
            }

            if (ghostcheck(ghostsRight , ghostsLeft , ghostsBottom+3, ghostsTop +3, wallPosition) == false){
                changing = 4;
                return;
            }

            if (ghostcheck(ghostsRight -2, ghostsLeft -2 , ghostsBottom , ghostsTop , wallPosition) == false){
                changing = 2;
                return;
            }
        }
    }

    public void rightTopmove(List<Wall> wallPosition){

        this.setghostposition();

        if (direction.equals("left")){
            this.choicesLeftone(wallPosition);
        }

        if (direction.equals("down")){
            this.choicesDowntwo(wallPosition);
        }
        
        if (direction.equals("top")){
            this.choicesTopthree(wallPosition);
        }

        if (direction.equals("right")){

            if (ghostcheck(ghostsRight +2, ghostsLeft +2 , ghostsBottom , ghostsTop, wallPosition) == false){
                changing = 1;
                return;
            }

            if (ghostcheck(ghostsRight, ghostsLeft , ghostsBottom-3, ghostsTop-3, wallPosition) == false){
                changing = 3;
                return;
            }

            if (ghostcheck(ghostsRight , ghostsLeft , ghostsBottom+3, ghostsTop +3, wallPosition) == false){
                changing = 4;
                return;
            }

            if (ghostcheck(ghostsRight -2, ghostsLeft -2 , ghostsBottom , ghostsTop , wallPosition) == false){
                changing = 2;
                return;
            }
        }
    }

    public void leftBottommove(List<Wall> wallPosition){
        
        this.setghostposition();

        if (direction.equals("left")){
            this.choicesLeftthree(wallPosition);
        }

        if (direction.equals("down")){
            this.choicesDownone(wallPosition);
        }
        
        if (direction.equals("top")){
            this.choicesTopone(wallPosition);
        }

        if (direction.equals("right")){
            this.choicesRighttwo(wallPosition);
        }
    }

    public void rightBottommove(List<Wall> wallPosition){
        
        this.setghostposition();

        if (direction.equals("left")){
           this.choicesLefttwo(wallPosition);
        }

        if (direction.equals("down")){
            this.choicesDowntwo(wallPosition);
        }
        
        if (direction.equals("top")){
            this.choicesTopthree(wallPosition);
        }

        if (direction.equals("right")){
            this.choicesRightone(wallPosition);
        }
    }
}