package Game;

import Entities.Player;
import Entities.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements  Runnable{
    private Window window;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS = 120;
    private final int UPS = 200;

    private Player player ;

    public final static int TILES_DEFAULT_SIZE = 32 ;
    public final static float SCALE = 1.5f ;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static  int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final  static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH ;
    public final  static int GAME_HEIGHT = TILES_SIZE  * TILES_IN_HEIGHT;

    private ArrayList<Wall> walls = new ArrayList<>();


    public Game(){
        player = new Player(200,200 , (int)SCALE*72 , (int)SCALE*58,walls);
        gamePanel = new GamePanel(this);
        window = new Window(gamePanel);
        gamePanel.requestFocus();
        startGameloop();
        for(int i = 0 ; i<14 ; i++) walls.add(new Wall(i*TILES_IN_WIDTH,600,TILES_IN_WIDTH,TILES_IN_WIDTH));

    }

    public void update(){
        player.update();
    }

    public void render(Graphics g){
        player.render(g);
        for(Wall wall : walls){
            wall.render(g);
        }
    }

    private void startGameloop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }
    }

    public void windowFocusLost(){
        player.resetDirBooleans();
    }

    public Player getPlayer(){
        return  player;
    }

}
