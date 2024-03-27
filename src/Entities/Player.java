package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static Entities.Constants.PlayerConstants.*;
import static Game.Game.GAME_WIDTH;
import static Game.Game.SCALE;

public class Player extends Entity{

    private BufferedImage[][] animations;
    private int aniTick ,aniIndex;
    private final int aniSpeed = 25 ;
    private int playerAction = IDLE;
    private boolean moving = false , attacking = false;
    private boolean left , up , down , right ;
    private float playerSpeed = 2.0f ;

    private List<Wall> walls;
    public Player(float x, float y , int width , int height, List<Wall> walls) {
        super(x, y,width,height);
        this.walls = walls;
        loadAnimations();
        initHitbox(x+15, y+4, 36, 54);
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, width, height, null);
        drawHitbox(g);
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

        if (attacking)
            playerAction = ATTACK_1;

        if (startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving = false;

        if (left && !right) {
            x -= playerSpeed;
            hitbox.x =x+15 ;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            hitbox.x =x+15 ;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            hitbox.y =y+4 ;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            hitbox.y =y+4 ;
            moving = true;
        }
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
