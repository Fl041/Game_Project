package Utilz;

import Game.Game;
import gamestates.Gamestate;

import java.awt.*;
import java.awt.image.BufferedImage;
import static Utilz.Constants.LoadConstants.BUTTON;

/**
 * Class which allows you to create the buttons
 */
public class Buttons {
    private int xPos, yPos, rowIndex , num;
    private int width, height ;
    private int xOffsetCenter = width/2;
    private Gamestate actualState,newState;
    private BufferedImage[][] imgs;
    private boolean mouseOver, mousePressed;
    private Game game;
    private Rectangle bounds;

    public Buttons(int xPos, int yPos, int width, int height, int rowIndex, int  num , Gamestate actualState,Gamestate newState,Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.rowIndex = rowIndex;
        this.num = num;
        this.actualState = actualState;
        this.newState = newState;
        this.game = game;
        loadImgs();
        initBounds();
    }

    /**
     * initializes the button area
     */
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, width, height);
    }

    /**
     * load the different button types
     */
    private void loadImgs() {
        imgs = new BufferedImage[10][3];
        BufferedImage img = Load.loadResources(BUTTON);
        int x = 56 ;
        int y1 = 39;
        int y2 = 296;
        for (int i = 0; i < imgs.length/2; i++) {
            for (int j = 0; j < imgs[i].length; j++) {
                    imgs[i][j] =img.getSubimage(x + i * (200 + 44), y1 + j * (54 + 18), 200, 54);
            }
        }
        for (int i = 5; i < imgs.length; i++) {
            for (int j = 0; j < imgs[i].length; j++) {
                imgs[i][j] =img.getSubimage(x + (i-5) * (200 + 44), y2 + j * (54 + 18), 200, 54);
            }
        }
    }

    /**
     * display the button
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(imgs[rowIndex][num], xPos - xOffsetCenter, yPos, width, height, null);
    }

    public void update() {
        num = 0;
        if (mouseOver)
            num = 1;
        if (mousePressed)
            num = 2;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * change the state of the game based on the state specified when it was created
     */
    public void applyGamestate() {
        if(newState == Gamestate.QUIT){
            System.exit(0);
        }
        else if(actualState == Gamestate.DEATH){
                game.initClasses();
                Gamestate.state = newState;
            }
        else if(actualState == Gamestate.PLAYING){
            game.setRestart(false);
            game.initClasses();
            Gamestate.state = newState;
        }
        else  Gamestate.state = newState;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}
