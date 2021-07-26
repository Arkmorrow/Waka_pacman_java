package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;

public class Ambusher extends Ghosts{

    public Ambusher(int x, int y,PImage image, PImage frightened, String ghostsType){
        super(x, y, image, frightened, ghostsType);
        xBefore = x;
        yBefore = y;
        changing = 1;
    }

    public void checkcollision(List<Wall> wallPosition){
        this.setghostposition();
        this.saveDirection();
        this.rightTopmove(wallPosition);
    }

    public void setChasePosition(Player player, List<Ghosts> ghostPosition){

        Hdeistance = this.x - player.x;
        Vdeistance = this.y - player.y;

        if (player.direction.equals("left")){
            Hdeistance -= 4*16;
        } else if (player.direction.equals("right")){
            Hdeistance += 4*16;
        } else if (player.direction.equals("top")){
            Vdeistance -= 4*16;
        } else if (player.direction.equals("down")){
            Vdeistance += 4*16;
        }
    }

    public void drawline(PApplet app){
        app.line(x + this.getWidth()/2, y + this.getHeight()/2,448,0);
        app.stroke(255, 255, 255);
    }

    public void drawPlayerline(PApplet app, Player player){

        int chaseX = player.x + player.getWidth()/2;
        int chaseY = player.y + player.getHeight()/2;

        if (player.direction.equals("left")){
            chaseX -= 4*16;
        } else if (player.direction.equals("right")){
            chaseX += 4*16;
        } else if (player.direction.equals("top")){
            chaseY -= 4*16;
        } else if (player.direction.equals("down")){
            chaseY += 4*16;
        }

        if (chaseX < 0){
            chaseX = 0;
        }

        if (chaseX >448){
            chaseX = 448;
        }

        if (chaseY < 0){
            chaseY = 0;
        }

        if (chaseY > 576){
            chaseX = 576;
        }

        app.line(x + this.getWidth()/2, y + this.getHeight()/2, chaseX, chaseY);
        app.stroke(255, 255, 255);
    }
}