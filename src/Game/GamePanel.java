package Game;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

/**
 * The game panel class which allows you to display the game and interact with it
 */
public class GamePanel extends JPanel {

    private final Game game ;

    private MouseInputs mouseInputs ;
    private KeyboardInputs keyboardInputs;

    /**
     * The constructor which allows you to define the size of the panel as well as associate all the listeners necessary for the game
     * @param game
     */
    public GamePanel(Game game){
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);
        this.game = game ;

        setSize();

        addKeyListener(keyboardInputs);
        this.setFocusable(true);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Function to define the panel size
     */
    public void setSize(){
        Dimension size = new Dimension(700,700);
        this.setPreferredSize(size);
    }

    /**
     * Function that displays game elements on the panel
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame(){
        return game;
    }
}
