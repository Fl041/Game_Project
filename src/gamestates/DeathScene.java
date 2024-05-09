package gamestates;

import Entities.Player;
import Game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class DeathScene extends State implements Statemethods {
    private int score ;
    public DeathScene(Game game) {
        super(game);
        score = game.getPlaying().getPlayer().score;
        initScene();
    }

    public void initScene(){

    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        Font f =new Font(null , Font.BOLD , 20);
        g.setFont(f);
        g.drawString("Votre score : " + score,300,400);
        g.drawString("You died",300,200);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
