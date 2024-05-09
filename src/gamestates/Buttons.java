package gamestates;

import gamestates.Gamestate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Buttons {
    private int xPos, yPos, rowIndex , num;

    private int width, height ;
    private int xOffsetCenter = width/2;

    private Gamestate state;
    private BufferedImage[][] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public Buttons(int xPos, int yPos,int width,int height, int rowIndex, int  num , Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.rowIndex = rowIndex;
        this.num = num;
        this.state = state;
        loadImgs();
        initBounds();
    }
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, width, height);

    }

    private void loadImgs() {
        imgs = new BufferedImage[10][3];
        InputStream is = getClass().getResourceAsStream("/ressources/button.png");
        try {
            BufferedImage img = ImageIO.read(is);
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

    public void draw(Graphics g) {
        //200 54
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

    public void applyGamestate() {
        if(state == Gamestate.QUIT){
            System.exit(0);
        }
        else  Gamestate.state = state;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}
