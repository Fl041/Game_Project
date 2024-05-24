package gamestates;

import Game.Game;
import Utilz.Buttons;

import java.awt.event.MouseEvent;

/**
 * Abstract class on which all game states are based
 */
public abstract class State {

	protected Game game;

	public State(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	/**
	 * function which allows you to know if the mouse passes over a button
	 * @param e
	 * @param b
	 * @return
	 */
	public boolean isIn(MouseEvent e, Buttons b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}
