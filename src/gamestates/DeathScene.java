package gamestates;

import Entities.Player;
import Game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class DeathScene extends State implements Statemethods {
    private int score,best_score ;
    private Buttons[] buttons = new Buttons[2];
    public DeathScene(Game game) {
        super(game);
        initScene();
    }

    public void initScene(){
        buttons[0] = new Buttons(250,250,300,81,9,0,Gamestate.DEATH,Gamestate.PLAYING,this.game);
        buttons[1] = new Buttons(250,400,300,81,7,0,Gamestate.DEATH,Gamestate.MENU,this.game);
    }

    @Override
    public void update() {
        best_score = game.getBest_score();
        score = game.getPlaying().getPlayer().score;
        if(best_score == 0 ) game.setBest_score(score);
        else if(best_score < score) game.setBest_score(score);
        for (Buttons b : buttons)
            b.update();
    }

    @Override
    public void draw(Graphics g) {
        for (Buttons b : buttons)
            b.draw(g);
        Font f =new Font(null , Font.BOLD , 20);
        g.setFont(f);
        g.drawString("Your score : " + score,250,100);
        g.drawString("Best score of the session : " + best_score,250,200);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Buttons b : buttons) {
            if (isIn(e, b)) {
                b.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (Buttons b : buttons) {
            if (isIn(e, b)) {
                if (b.isMousePressed())
                    b.applyGamestate();
                break;
            }
        }

        resetButtons();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Buttons b : buttons)
            b.setMouseOver(false);

        for (Buttons b : buttons)
            if (isIn(e, b)) {
                b.setMouseOver(true);
                break;
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void resetButtons() {
        for (Buttons b : buttons)
            b.resetBools();
    }
}
