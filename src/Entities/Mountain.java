package Entities;

import Utilz.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.LoadConstants.MOUNTAIN;

public class Mountain extends Entity{

    public int startX;
    private BufferedImage[] texture;
    private int[][] dimension;
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

    public void loadTexture(){
        /*
        1. 30 23 219 94
        2. 259 24 221 91
        3. 485 4 168 113
        4. 19 140 285 110
        5. 314 157 141 92
        6. 457 142 203 107
         */
        BufferedImage img = Load.loadResources(MOUNTAIN);
        texture = new BufferedImage[6];
        for(int i = 0 ; i<texture.length ; i++){
            texture[i] = img.getSubimage(posX[i],posY[i], widthC[i] , heightC[i]);
        }
    }

    public void set(){
        x -= (float) (player.getXspeed()/7);
    }
    public void draw(Graphics2D gtd){
        gtd.drawImage( texture[numtexture], (int) x, (int) y,getWidth(),getHeight(),null);

    }
}
