package gamestates;

import Entities.Cloud;
import Game.Game;
import Utilz.ListUpdate;
import Utilz.Load;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static Utilz.Constants.LoadConstants.BACKGROUND;

public class Menu extends State implements Statemethods {

	private Buttons[] buttons = new Buttons[2];
	private BufferedImage background ;

	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackground();
	}

	public void loadBackground(){
		background = Load.loadResources(BACKGROUND);
	}

	public void loadButtons(){
		buttons[0] = new Buttons(200,250,300,81,0,0,Gamestate.MENU,Gamestate.PLAYING,this.game);
		buttons[1] = new Buttons(200,400,300,81,7,0,Gamestate.MENU,Gamestate.QUIT,this.game);
	}
	@Override
	public void update() {
		for (Buttons b : buttons)
			b.update();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(background,0,0,750,750,null);
		for (Buttons b : buttons)
			b.draw(g);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		for (Buttons b : buttons) {
			if (isIn(e, b)) {
				b.setMousePressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			Gamestate.state = Gamestate.PLAYING;



	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetButtons() {
		for (Buttons b : buttons)
			b.resetBools();
	}


}
