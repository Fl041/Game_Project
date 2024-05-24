package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import static Utilz.Constants.LoadConstants.CLOUD;
import static Utilz.Load.loadResources;

/**
 * class which allows you to create clouds
 */
public class Cloud extends Entity{

    public int startX;
    private BufferedImage[] texture;
    private int[][] dimension;
    private int numtexture , type;
    private final int[] posX = {155,446,576,20,337} ;
    private final int[] posY = {108,134,123,163,179} ;
    private final int[] widthC = {76,43,85,315,300} ;
    private final int[] heightC = {36,25,39,92,112} ;
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

    /**
     * function to update its position
     * @param CameraX
     */
    public void set(int CameraX){
        if(type == 0 ){
            x -= (float) (player.getXspeed()/7);
        }
        else{
            x = startX + CameraX;
            getHitbox().x = (int) x;
        }
    }

    /**
     * function to recover different types of clouds
     */
    private void loadTexture() {
        BufferedImage img = loadResources(CLOUD);
        texture = new BufferedImage[5];
        for(int i = 0 ; i<texture.length ; i++){
            texture[i] = img.getSubimage(posX[i],posY[i], widthC[i] , heightC[i]);
        }


    }

    /**
     * function to display the cloud
     * @param gtd
     */
    public void draw(Graphics2D gtd){
        gtd.drawImage( texture[numtexture], (int) x, (int) y,getWidth(),getHeight(),null);

    }
}
