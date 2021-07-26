package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;

public class Ignorant extends Ghosts{

    public Ignorant(int x, int y,PImage image, PImage frightened, String ghostsType){
        super(x, y, image, frightened, ghostsType);
        xBefore = x;
        yBefore = y;
        changing = 2;
    }

    public void checkcollision(List<Wall> wallPosition){
        this.setghostposition();
        this.saveDirection();
        this.bottomLeftmove(wallPosition);
    }

    public void setChasePosition(Player player, List<Ghosts> ghostPosition){

        int distance = Math.round(PApplet.dist(this.x, this. y, player.x, player.y) / 16) ;
        System.out.println(distance);

        if (distance <= 8){
            iChase = false;
            return;
        } else {
            iChase = true;
        }
        
        Hdeistance = this.x - player.x;
        Vdeistance = this.y - player.y;
    }

    public void drawline(PApplet app){
        app.line(x + this.getWidth()/2, y + this.getHeight()/2,0,576);
        app.stroke(255, 255, 255);
    }

    public void drawPlayerline(PApplet app, Player player){

        if (iChase == true){
            app.line(x + this.getWidth()/2, y + this.getHeight()/2, player.x + player.getWidth()/2,player.y + player.getHeight()/2);
        } else {
            app.line(x + this.getWidth()/2, y + this.getHeight()/2, 0, 576);
        }
        
        app.stroke(255, 255, 255);
    }
}