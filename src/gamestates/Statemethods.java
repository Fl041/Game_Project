package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * interface which brings together all the functions necessary for different states of the game
 */
public interface Statemethods {

	/**
	 * updates the state's elements
	 */
	public void update();

	/**
	 * display the state's elements
	 * @param g
	 */
	public void draw(Graphics g);

	/**
	 * defined mouse interactions
	 * @param e
	 */
	public void mouseClicked(MouseEvent e);

	public void mousePressed(MouseEvent e);

	public void mouseReleased(MouseEvent e);

	public void mouseMoved(MouseEvent e);

	/**
	 * defined keyboard interactions
	 * @param e
	 */
	public void keyPressed(KeyEvent e);

	public void keyReleased(KeyEvent e);

	public void resetButtons();

}
