package Game;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static Game.Game.*;

public class GamePanel extends JPanel {

    private Game game ;

    private MouseInputs mouseInputs ;
    private KeyboardInputs keyboardInputs;

    public GamePanel(Game game){
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);
        this.game = game ;

        setSize();

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
    }

    public void setSize(){
        Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
        this.setPreferredSize(size);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame(){
        return game;
    }
}
