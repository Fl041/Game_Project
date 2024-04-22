package Entities;

import java.awt.*;

public class Wall {

    public int x;
    int y;
    int width;
    int height ;
    Rectangle hitbox ;

    int startX;

    public Wall(int x ,int y ,int width ,int height){
        this.x = x ;
        startX = x ;
        this.y = y ;
        this.width = width;
        this.height = height;

        hitbox = new Rectangle(x,y,width,height);
    }

    public int set(int CameraX){
        x = startX + CameraX;
        hitbox.x = x ;

        return x;
    }
    public void draw(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.drawRect(x,y,width,height);
        gtd.setColor(Color.WHITE);
        gtd.fillRect(x+1,y+1,width-2,height-2);
    }
}
