package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Wall extends Entity{

    public int startX;
    private BufferedImage texture;

    private int numtexture ;

    public Wall(int x ,int y ,int numtexture){
        super(x,y,50,50);
        startX = x ;
        this.numtexture = numtexture;
        loadTexture(numtexture);
        initHitbox(x,y,width,height);
    }

    public int set(int CameraX){
        x = startX + CameraX;
        getHitbox().x = (int) x;

        return (int) x;
    }

    private void loadTexture(int numtexture) {
        InputStream is = getClass().getResourceAsStream("/ressources/outside_sprites.png");
        try {
            BufferedImage img = ImageIO.read(is);
            if(numtexture == 0 )
            texture = img.getSubimage(27,0 , 46, 46);
            else texture = img.getSubimage(26,22 , 47, 47);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void draw(Graphics2D gtd){
        gtd.drawImage( texture, (int) x, (int) y,getWidth(),getHeight(),null);
        /*gtd.setColor(Color.BLACK);
        gtd.drawRect((int) x, (int) y,width,height);
        gtd.setColor(Color.WHITE);*/
    }

    public int getNumtexture(){
        return numtexture;
    }
}
