package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.LoadConstants.CLOUD;
import static Utilz.Load.loadResources;

public class Cloud extends Entity{

    public int startX;
    private BufferedImage[] texture;
    private int[][] dimension;
    private int numtexture , type;
    private final int[] posX = {155,446,587,20,337} ;
    private final int[] posY = {108,134,124,163,179} ;
    private final int[] widthC = {70,32,74,315,300} ;
    private final int[] heightC = {28,17,29,92,112} ;
    Player player ;

    public Cloud(float x, float y, int width, int height,int numCloud, int type  , Player player) {
        super(x, y, width, height);
        startX = (int) x;
        this.numtexture = numCloud;
        this.type = type;
        this.player = player;
        loadTexture();
        setWidth(widthC[numtexture]);
        setHeight(heightC[numtexture]);
        initHitbox(x,y,width,height);
    }

    public void set(int CameraX){
        if(type == 0 ){
            x -= (float) (player.getXspeed()/7);
        }
        else{
            x = startX + CameraX;
            getHitbox().x = (int) x;
        }
    }

    private void loadTexture() {
        BufferedImage img = loadResources(CLOUD);
        texture = new BufferedImage[5];
        for(int i = 0 ; i<texture.length ; i++){
            texture[i] = img.getSubimage(posX[i],posY[i], widthC[i] , heightC[i]);
        }


    }

    public void draw(Graphics2D gtd){
        gtd.drawImage( texture[numtexture], (int) x, (int) y,getWidth(),getHeight(),null);

    }
}
