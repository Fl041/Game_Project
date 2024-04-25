package Game;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static Game.Game.*;

public class GamePanel extends JPanel {

    private final Game game ;

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
        Dimension size = new Dimension(700,700);
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
