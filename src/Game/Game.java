package Game;

import Entities.ListWall;
import Entities.Player;
import Entities.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

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

    public ListWall<Wall> walls = new ListWall<>(150);
    public int CameraX;
    public int offset;


    public Game(){
        player = new Player(400,300 , 72,58,this);
        gamePanel = new GamePanel(this);
        window = new Window(gamePanel);
        gamePanel.requestFocus();
        reset();
        startGameloop();

    }

    public void reset(){
        player.setX(200);
        player.setY(150);
        CameraX = 150;
        player.xspeed=0;
        player.yspeed=0;
        walls.clear();
        offset = -150;
        makeWall(offset);;
    }

    public  void makeWall(int offset){
        int s  = 50;
        Random rand = new Random();
        int index = rand.nextInt(8);
        if(index == 0 ){
            for(int i = 0 ; i<14 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
        }
        else if(index == 1 ){
            for(int i = 0 ; i < 4 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
            for(int i = 6 ; i < 10 ; i++){
                walls.add(new Wall(offset + i*50,550,s,s));
            }
            for(int i = 12 ; i < 16 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }

        }
        else if(index == 2 ){
            for(int i = 0 ; i < 4 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
            for(int i = 6 ; i < 10 ; i++){
                walls.add(new Wall(offset + i*50,550,s,s));
            }
            for(int i = 12 ; i < 16 ; i++){
                walls.add(new Wall(offset + i*50,500,s,s));
            }

        }
        else if(index == 3 ){
            for(int i = 0 ; i < 4 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
            for(int i = 6 ; i < 10 ; i++){
                walls.add(new Wall(offset + i*50,650,s,s));
            }
            for(int i = 12 ; i < 16 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
        }
        else if(index == 4 ){
            for(int i = 0 ; i < 12 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
            for(int i = 4 ; i < 12 ; i++){
                walls.add(new Wall(offset + i*50,550,s,s));
            }
            for(int i = 8 ; i < 12 ; i++){
                walls.add(new Wall(offset + i*50,500,s,s));
            }
        }
        else if(index == 5 ){
            for(int i = 0 ; i < 12 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
            for(int i = 0 ; i < 8 ; i++){
                walls.add(new Wall(offset + i*50,550,s,s));
            }
            for(int i = 0; i < 4 ; i++){
                walls.add(new Wall(offset + i*50,500,s,s));
            }
        }
        else if(index == 6 ){
            for(int i = 0 ; i < 16 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
            for(int i = 0 ; i < 4 ; i++){
                walls.add(new Wall(offset + i*50,550,s,s));
            }
            for(int i = 12 ; i < 16 ; i++){
                walls.add(new Wall(offset + i*50,550,s,s));
            }
            for(int i = 0 ; i < 2 ; i++){
                walls.add(new Wall(offset + i*50,500,s,s));
            }
            for(int i = 14 ; i < 16 ; i++){
                walls.add(new Wall(offset + i*50,500,s,s));
            }

        }
        else {
            for(int i = 0 ; i < 4 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
            for(int i = 6 ; i < 10 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
            for(int i = 12 ; i <16 ; i++){
                walls.add(new Wall(offset + i*50,600,s,s));
            }
        }

    }


    public void update(){
        player.update();
    }

    public void render(Graphics g){
        player.draw((Graphics2D) g);
        for(Wall wall : walls){
            wall.draw((Graphics2D) g);
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
                if(walls.get(walls.size() -1).getX() <800){
                    offset += 800 ;
                    makeWall(offset);

                }
                update();
                for (Wall wall : walls) wall.set(CameraX);

               /* for (int i = 0; i < walls.size(); i++) {
                    if (walls.get(i).getX() < -800) walls.remove(i);
                }*/
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
