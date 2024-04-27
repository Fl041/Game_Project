package Game;

import Entities.ListWall;
import Entities.Player;
import Entities.Wall;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Menu.*;
import gamestates.Playing;

import java.awt.*;



public class Game implements  Runnable{
    private Window window;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS = 100;
    private final int UPS = 200;

    private Player player ;

    private Playing playing;
    private Menu menu;




    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        window = new Window(gamePanel,playing);
        gamePanel.requestFocus();
        startGameloop();

    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }



    public void update(){
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;

        }

    }

    public void render(Graphics g){
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
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

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}
