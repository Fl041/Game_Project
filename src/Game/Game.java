package Game;

import gamestates.DeathScene;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

import java.awt.*;

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




    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        window = new Window(gamePanel);
        gamePanel.requestFocus();
        startGameloop();

    }

    public void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
        deathScene = new DeathScene(this);
    }



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
