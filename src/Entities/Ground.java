package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import static Utilz.Constants.LoadConstants.WALL;
import static Utilz.Load.loadResources;

/**
 * Class which allows you to create the grounds
 */
public class Ground extends Entity{

    public int startX;
    private BufferedImage texture;

    private int numtexture ;

    public Ground(int x , int y , int numtexture){
        super(x,y,50,50);
        startX = x ;
        this.numtexture = numtexture;
        loadTexture(numtexture);
        initHitbox(x,y,width,height);
    }

    /**
     * updates the position of the wall
     * @param CameraX
     */
    public void set(int CameraX){
        x = startX + CameraX;
        getHitbox().x = (int) x;
    }

    /**
     * function to recover different types of grounds
     * @param numtexture
     */
    private void loadTexture(int numtexture) {
        BufferedImage img = loadResources(WALL);
        if(numtexture == 0 )
            texture = img.getSubimage(27,0 , 46, 46);
            else texture = img.getSubimage(26,22 , 47, 47);
    }

    /**
     * function to display the ground
     * @param gtd
     */
    public void draw(Graphics2D gtd){
        gtd.drawImage( texture, (int) x, (int) y,getWidth(),getHeight(),null);

    }
}
