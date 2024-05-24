package Game;

import gamestates.DeathScene;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import java.awt.*;

/**
 Main class of the game which creates the window and manages its different states
 */
public class Game implements  Runnable{

    private Window window;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS = 120;
    private final int UPS = 200;
    private int best_score = 0 ;
    private Playing playing;
    private Menu menu;
    private DeathScene deathScene;
    private boolean notRestart = true;


    /**
     * Class constructor, the different states as well as the fan and the panel are initialized and the game loop is launched
     */
    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        window = new Window(gamePanel);
        gamePanel.requestFocus();
        startGameloop();

    }

    /**
     * function to initialize game states
     */
    public void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
        deathScene = new DeathScene(this);
    }


    /**
     * function that updates the game based on its state
     */
    public void update(){
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case DEATH:
                deathScene.update();
                break;
            default:
                break;

        }

    }

    /**
     * function that displays the game according to its state
     */
    public void render(Graphics g){
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case DEATH:
                deathScene.draw(g);
            default:
                break;
        }
    }

    /**
     * starts the game loop
     */
    private void startGameloop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * the game loop
     */
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
                /**
                 * allows you to display fps and ups in real time on the console
                 */
                //System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }
    }

    /**
     * function that stops interaction with the window when it is no longer in the foreground
     */
    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING)
            playing.getPlayer().resetDirBooleans();
    }
    public Menu getMenu() {
        return menu;
    }
    public Playing getPlaying() {
        return playing;
    }
    public DeathScene getDeathScene(){
        return deathScene;
    }
    public int getBest_score() {
        return best_score;
    }
    public void setBest_score(int best_score) {
        this.best_score = best_score;
    }
    public boolean isNotRestart() {
        return notRestart;
    }
    public void setRestart(boolean isrestart){  notRestart = isrestart;}
}
