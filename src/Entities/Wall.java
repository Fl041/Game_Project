package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Wall extends Entity{

    public int startX;
    private BufferedImage texture;

    public Wall(int x ,int y ,int width ,int height){
        super(x,y,width,height);
        startX = x ;
        loadTexture();
        initHitbox(x,y,width,height);
    }

    public int set(int CameraX){
        x = startX + CameraX;
        getHitbox().x = (int) x;

        return (int) x;
    }

    private void loadTexture() {
        InputStream is = getClass().getResourceAsStream("/ressources/outside_sprites.png");
        try {
            BufferedImage img = ImageIO.read(is);
            texture = img.getSubimage(27,0 , 46, 46);


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
}
