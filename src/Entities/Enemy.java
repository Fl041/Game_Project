package Entities;

import Utilz.ListUpdate;

import java.awt.*;
import java.awt.image.BufferedImage;
import static Utilz.Constants.EnnemyConstants.*;
import static Utilz.Constants.LoadConstants.ENEMY;
import static Utilz.Load.loadResources;

public class Enemy extends Entity{
    private BufferedImage[][] animations;
    private int aniTick ,aniIndex,attackTick;
    private int startX;
    private int enemyAction = IDLE;
    private final int aniSpeed = 25 ;
    private boolean  attacking = false , isdead = false;
    private ListUpdate<EnemyProjectile> ennemiesprojectile = new ListUpdate<>(2);
    private Player player;

    public Enemy(float x, float y, int width, int height,Player player) {
        super(x, y, width, height);
        this.startX = (int) x;
        this.player = player;
        loadAnimations();
        initHitbox(x,y,width,height);
    }

    public void update(int cameraX) {
            set(cameraX);
            setAnimation();
            updateAnimationTick();

            for(EnemyProjectile enemyProjectile : ennemiesprojectile) {
                enemyProjectile.update((int) player.getXspeed());
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex == 5 ){
                if(enemyAction == ATTACK){
                    makeProjectile((int) x, (int) y);
            }
            }
            if (aniIndex >= GetSpriteEnnemyAmount(enemyAction)) {
                if(enemyAction == DEATH ){
                    aniIndex = 4 ;
                }
                else {
                    aniIndex = 0;
                    attacking = false;

                }

            }

        }

    }

    private void setAnimation() {
        int startAni = enemyAction;
        if(isdead){
            enemyAction = DEATH;
        }
        else {
            if(attacking){
                enemyAction = ATTACK;
            }
            else
                enemyAction = IDLE;
        }
        if (startAni != enemyAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void set(int CameraX){
        x = startX + CameraX;
        getHitbox().x = (int) x;
        if(!isdead){
            if((x<550)&&(x>0)&&(attackTick>=100)){
                attacking = true;
                attackTick = 0 ;
            }
            attackTick++;
        }

    }

    public void makeProjectile(int x , int y){
        ennemiesprojectile.add(new EnemyProjectile(x, y+70,30,30));
    }

    public void draw(Graphics2D gtd){
        gtd.drawImage(animations[enemyAction][aniIndex], (int) x, (int) y, width, height, null);
        for(EnemyProjectile enemyProjectile : ennemiesprojectile){
            enemyProjectile.draw(gtd);
        }
    }

    private void loadAnimations() {
        BufferedImage img = loadResources(ENEMY);
        int[] height  = {6,63,143,199};
        int[] walkX = {500,448,395,343,285,230,181,135,92,50,4};
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

    }

    public void setIsdead(boolean dead){
        isdead = dead;
    }

    public ListUpdate<EnemyProjectile> getEnnemiesprojectile() {
        return ennemiesprojectile;
    }

}
