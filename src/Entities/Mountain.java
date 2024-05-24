package Entities;

import Utilz.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.LoadConstants.MOUNTAIN;

/**
 * Class which allows you to create the mountains in the background
 */
public class Mountain extends Entity{

    public int startX;
    private BufferedImage[] texture;
    private int numtexture;
    private final int[] posX = {30,259,485,19,314,457} ;
    private final int[] posY = {23,24,4,140,157,142} ;
    private final int[] widthC = {219,221,168,285,141,203} ;
    private final int[] heightC = {94,91,113,110,62,107} ;
    Player player ;

    public Mountain(float x, float y, int width, int height, int numMountain,Player player) {
        super(x, y, width, height);
        startX = (int) x;
        this.numtexture = numMountain;
        this.player = player;
        loadTexture();
        initHitbox(x,y,width,height);
    }
    /**
     * function to recover different types of mountains
     */
    public void loadTexture(){
        BufferedImage img = Load.loadResources(MOUNTAIN);
        texture = new BufferedImage[6];
        for(int i = 0 ; i<texture.length ; i++){
            texture[i] = img.getSubimage(posX[i],posY[i], widthC[i] , heightC[i]);
        }
    }

    /**
     * update the position
     */
    public void set(){
        x -= (float) (player.getXspeed()/7);
    }

    /**
     * function to display the mountain
     * @param gtd
     */
    public void draw(Graphics2D gtd){
        gtd.drawImage( texture[numtexture], (int) x, (int) y,getWidth(),getHeight(),null);

    }
}
