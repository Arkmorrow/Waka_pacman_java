package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;

public class Whim extends Ghosts{

    private boolean chaserCheck;
    private int targetX;
    private int targetY;

    public Whim(int x, int y,PImage image, PImage frightened, String ghostsType){
        super(x, y, image, frightened, ghostsType);
        xBefore = x;
        yBefore = y;
        changing = 1;
        chaserCheck = false;
    }

    public void checkcollision(List<Wall> wallPosition){
        this.saveDirection();
        this.bottomRightmove(wallPosition);
    }

    public void setChasePosition(Player player, List<Ghosts> ghostPosition){

        for (Ghosts ghost : ghostPosition){

            if (ghost.ghostsType.equals("Chaser")){
                targetX = 2* (player.x + 2*16 - ghost.x) + ghost.x;
                targetY = 2* (player.y - ghost.y) + ghost.y;
                chaserCheck = true;
                Hdeistance = this.x - targetX;
                Vdeistance = this.y - targetY;
                return;
            }
        }

        chaserCheck = false;
        Hdeistance = this.x - player.x;
        Vdeistance = this.y - player.y;
    }

    public void drawline(PApplet app){
        app.line(x + this.getWidth()/2, y + this.getHeight()/2,448,576);
        app.stroke(255, 255, 255);
    }

    public void drawPlayerline(PApplet app, Player player){

        if (chaserCheck){
            app.line(x + this.getWidth()/2, y + this.getHeight()/2, targetX,targetY);
            return;
        }

        app.line(x + this.getWidth()/2, y + this.getHeight()/2, player.x + player.getWidth()/2,player.y + player.getHeight()/2);
        app.stroke(255, 255, 255);
    }
}