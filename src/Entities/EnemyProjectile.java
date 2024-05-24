package Entities;


import java.awt.*;
import java.awt.image.BufferedImage;
import static Utilz.Constants.EnnemyConstants.*;
import static Utilz.Constants.LoadConstants.ENEMY_PROJECTILE;
import static Utilz.Load.loadResources;

/**
 * class which allows you to create enemy's projectile
 */
public class EnemyProjectile extends Entity{
    private BufferedImage[] animations;
    private int aniTick ,aniIndex;
    private int startX;
    private int ennemyAction = Projectile;
    private final int aniSpeed = 25 ;
    public EnemyProjectile(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.startX = (int) x;
        loadAnimations();
        initHitbox(x,y,width,height);
    }

    /**
     * Function that allows you to update the enemy projectiles position and animation
     * @param playerSpeed
     */
    public void update(int playerSpeed) {
        set(playerSpeed);
        updateAnimationTick();
    }
    /**
     * updates the animation
     */
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

    /**
     * update projectile position
     * @param playerSpeed
     */
    public void set(int playerSpeed){
        x = startX ;
        startX -= (2 + playerSpeed);
        getHitbox().x = (int) x;

    }

    /**
     * function to display the projectile
     * @param gtd
     */
    public void draw(Graphics2D gtd){
        gtd.drawImage(animations[aniIndex], (int) x, (int) y, width, height, null);
    }

    /**
     * function to recover the projectile animation
     */
    private void loadAnimations() {
        BufferedImage img = loadResources(ENEMY_PROJECTILE);
        int [] height = {122,122,122,121,122,122,121};
            int [] width = {514,481,452,420,386,352,317};
            animations = new BufferedImage[7];
            for (int j = 0; j < animations.length; j++){
                        animations[j] = img.getSubimage(width[j] ,height[j] , 21, 21);
                }
    }
}
