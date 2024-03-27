package Entities;

import java.awt.*;

public class Wall extends Entity{

    public Wall(float x, float y, int width, int height) {
        super(x, y, width, height);
        initHitbox((int)x,(int)y,width,height);
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect((int)x,(int)y,width,height);
        g.setColor(Color.WHITE);
        g.fillRect((int)x+1,(int) y+1,width-2,height-2);

    }
}
