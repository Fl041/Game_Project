package gamestates;

import Game.Game;

import java.awt.event.MouseEvent;

public abstract class State {

	protected Game game;

	public State(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}
	public boolean isIn(MouseEvent e, Buttons b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}
