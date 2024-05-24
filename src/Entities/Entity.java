package Entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Abstract class which will be the basis of all entities present in the game
 */
public abstract class Entity {
    protected float x, y ;
    protected  int width , height ;

    protected Rectangle2D.Float hitbox;


    public Entity(float x , float y , int width , int height){
        this.x = x ;
        this.y = y ;
        this.width = width ;
        this.height = height;
    }

    /**
     * show hitbox (use to debug collisions)
     * @param g
     */
    protected void drawHitbox(Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);

    }

    /**
     * initializes the entity's hitbox
     * @param x
     * @param y
     * @param width
     * @param height
     */
    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width-20, height);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
