package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Entities.Constants.EnnemyConstants.*;

public class Enemy extends Entity{
    private BufferedImage[][] animations;
    private int aniTick ,aniIndex;
    private int startX;
    private int ennemyAction = IDLE;
    private final int aniSpeed = 25 ;
    private boolean moving = false , attacking = false , isdead = false;

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.startX = (int) x;
        loadAnimations();
        initHitbox(x,y,width,height);
    }

    public void update(int cameraX) {
            set(cameraX);
            updateAnimationTick();
            setAnimation();
    }
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteEnnemyAmount(ennemyAction)) {
                if(ennemyAction == DEATH ){
                    aniIndex = 4 ;
                }
                else {
                    aniIndex = 0;
                }
                attacking = false;

            }

        }

    }

    private void setAnimation() {
        int startAni = ennemyAction;

        if(isdead){
            ennemyAction = DEATH;
        }
        else {
            ennemyAction = IDLE;
        }
        if (startAni != ennemyAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void set(int CameraX){
        x = startX + CameraX;
        getHitbox().x = (int) x;
    }

    public void draw(Graphics2D gtd){
        gtd.drawImage(animations[ennemyAction][aniIndex], (int) x, (int) y, width, height, null);
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/ressources/ennemy-sprites.png");
        int[] height  = {6,63,143,199};
        int[] walkX = {500,448,395,343,285,230,181,135,92,50,4};
        try {
            BufferedImage img = ImageIO.read(is);

            int x =496 ;
            animations = new BufferedImage[4][11];
            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++){
                    if(j != 3){
                        if(x - (i * 50)<0)x =500;
                        else if(j==0) x = 500;
                        else if(j==1) x = 490;
                        else x = 496;
                        animations[j][i] = img.getSubimage(x - (i * 50), height[j], 50, 50);
                    }
                    else{
                        animations[j][i] = img.getSubimage(walkX[i], height[j], 50, 50);
                    }
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

    public void setIsdead(boolean dead){
        isdead = dead;
    }
}
