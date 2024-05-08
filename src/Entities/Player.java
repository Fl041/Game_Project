package Entities;

import Game.Game;
import gamestates.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static Entities.Constants.PlayerConstants.*;

public class Player extends Entity{

    private BufferedImage[][] animations;
    private int aniTick ,aniIndex;
    private final int aniSpeed = 25 ;
    private int playerAction = IDLE;
    private boolean moving = false , attacking = false , inAir = false;
    private boolean left , up , down , right ;

    static Playing game ;
    public double xspeed;
    public double yspeed;

    public int score;
    public int nbleft;
    public Player(int x , int y , int width, int height , Playing game){
        super(x,y,width,height);
        this.game = game;
        this.score = 0;
        this.nbleft = 0 ;
        loadAnimations();
       initHitbox(x,y,width,height);
    }

    public void update() {
        set();
        updateAnimationTick();
        setAnimation();
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/ressources/player_sprites.png");
        int[] heigth  = {0,70,146,212,282,354,440,510,581,652,724,794};
        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[14][6];
            for (int j = 0; j < animations.length-2; j++)
                for (int i = 0; i < animations[j].length; i++){
                    animations[j][i] = img.getSubimage(i * 72, heigth[j], 72, 58);
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

        animations[12][0] = animations[4][3];
        animations[13][0] = animations[4][4];
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }

        }

    }

    private void setAnimation() {
        int startAni = playerAction;

        if (moving)
            playerAction = RUN;
        else
            playerAction = IDLE;
        if(inAir){
            if(yspeed < 0) playerAction = JUMP_2;
            else playerAction = FALL;
        }

        if (attacking)
            playerAction = ATTACK_1;

        if (startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void set(){
        moving = false;
        if(left && right || !left && !right) xspeed *= 0.8 ;
        else if(left && !right) {
            xspeed --;
            moving  = true ;
            nbleft--;
        }
        else if(right && !left){
            xspeed ++;
            moving  = true ;
            if(nbleft >= 0) score++;
            else nbleft++;
        }

        if(xspeed > 0 && xspeed < 0.75) xspeed = 0 ;
        if(xspeed < 0 && xspeed > -0.75) xspeed = 0 ;

        if(xspeed > 4) xspeed = 4 ;
        if(xspeed < -4) xspeed = -4 ;

        if(up){
            inAir = true;
            hitbox.y ++ ;
            for(Wall wall: game.getWalls()){
                if(wall.hitbox.intersects(hitbox)){
                    yspeed = -7.5 ;
                }
            }
            hitbox.y--;
        }

        yspeed += 0.25;
        //Horizontal Collision
        hitbox.x += xspeed;
        for(Wall wall : game.getWalls()){
            if(hitbox.intersects(wall.getHitbox())){
                hitbox.x -= xspeed;
                while ( !wall.getHitbox().intersects(hitbox)){
                    hitbox.x += Math.signum(xspeed);
                }
                hitbox.x -= Math.signum(xspeed);
                game.CameraX += x- hitbox.x;
                xspeed = 0 ;
                hitbox.x = x ;
                nbleft = -1;
            }
        }
        //Vertical Collision
        hitbox.y += yspeed;
        for(Wall wall : game.getWalls()){
            if(hitbox.intersects(wall.getHitbox())){
                hitbox.y -= yspeed;
                while ( !wall.getHitbox().intersects(hitbox)){
                    hitbox.y += Math.signum(yspeed);
                }
                hitbox.y -= Math.signum(yspeed);
                inAir = false;
                yspeed = 0 ;
                y= (int) hitbox.y;
            }
        }

        game.CameraX-= xspeed;
        y += yspeed;

        hitbox.x = x;
        hitbox.y = y;

        //Death Code
        if(y > 800) game.reset();

    }

    public void draw(Graphics2D gtd){
        gtd.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, width, height, null);
        gtd.setColor(Color.BLACK);

    }
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

}