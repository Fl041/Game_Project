package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Entities.Constants.EnnemyConstants.*;

public class EnemyProjectile extends Entity{
    private BufferedImage[] animations;
    private int aniTick ,aniIndex;
    private int startX;
    private int ennemyAction = Projectile;
    private final int aniSpeed = 25 ;
    public EnemyProjectile(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.startX = (int) x;
        System.out.println("X création :" + x);
        loadAnimations();
        initHitbox(x,y,width,height);
    }

    public void update(int cameraX) {
        set(cameraX);
        updateAnimationTick();
    }
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteEnnemyAmount(ennemyAction)) {
                    aniIndex = 0 ;
            }
        }

    }

    public void set(int CameraX){
        //trouver une solution pour le camera x
        x = startX ;
        startX -= 2 ;
        getHitbox().x = (int) x;

    }

    public void draw(Graphics2D gtd){
        gtd.drawImage(animations[aniIndex], (int) x, (int) y, width, height, null);
        System.out.println(x);
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/ressources/ennemy-sprites.png");
        try {
            BufferedImage img = ImageIO.read(is);

            int [] height = {122,122,122,121,122,122,121};
            int [] width = {514,481,452,420,386,352,317};
            animations = new BufferedImage[7];
            for (int j = 0; j < animations.length; j++){
                        animations[j] = img.getSubimage(width[j] ,height[j] , 21, 21);
                }

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
}
