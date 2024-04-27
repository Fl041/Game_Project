package gamestates;

import Entities.ListWall;
import Entities.Player;
import Entities.Wall;
import Game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Playing extends State implements Statemethods {
	private Player player;
	private ListWall<Wall> walls = new ListWall<>(400);
	public int CameraX;
	private int offset;

	public Playing(Game game) {
		super(game);
		initClasses();
	}


	private void initClasses() {
		player = new Player(400,300 , 72,58,this);
		reset();

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
				walls.add(new Wall(offset + i*50,500,s,s,0));
				walls.add(new Wall(offset + i*50,550,s,s,1));
				walls.add(new Wall(offset + i*50,600,s,s,1));
				walls.add(new Wall(offset + i*50,650,s,s,1));
			}
		}
		else if(index == 1 ){
			for(int i = 0 ; i < 4 ; i++){
				walls.add(new Wall(offset + i*50,500,s,s,0));
			}
			for(int i = 6 ; i < 10 ; i++){
				walls.add(new Wall(offset + i*50,450,s,s,0));
			}
			for(int i = 12 ; i < 16 ; i++){
				walls.add(new Wall(offset + i*50,500,s,s,0));
			}

		}
		else if(index == 2 ){
			for(int i = 0 ; i < 4 ; i++){
				walls.add(new Wall(offset + i*50,500,s,s,0));
			}
			for(int i = 6 ; i < 10 ; i++){
				walls.add(new Wall(offset + i*50,450,s,s,0));
			}
			for(int i = 12 ; i < 16 ; i++){
				walls.add(new Wall(offset + i*50,400,s,s,0));
			}

		}
		else if(index == 3 ){
			for(int i = 0 ; i < 4 ; i++){
				walls.add(new Wall(offset + i*50,500,s,s,0));
			}
			for(int i = 6 ; i < 10 ; i++){
				walls.add(new Wall(offset + i*50,550,s,s,0));
			}
			for(int i = 12 ; i < 16 ; i++){
				walls.add(new Wall(offset + i*50,500,s,s,0));
			}
		}
		else if(index == 4 ){
			for(int i = 0 ; i < 12 ; i++){
				walls.add(new Wall(offset + i*50,650,s,s,1));
				walls.add(new Wall(offset + i*50,600,s,s,1));
				walls.add(new Wall(offset + i*50,550,s,s,1));
				if(i<4) walls.add(new Wall(offset + i*50,500,s,s,0));
				else walls.add(new Wall(offset + i*50,500,s,s,1));

			}
			for(int i = 4 ; i < 12 ; i++){
				if(i<8)walls.add(new Wall(offset + i*50,450,s,s,0));
				else walls.add(new Wall(offset + i*50,450,s,s,1));
			}
			for(int i = 8 ; i < 12 ; i++){
				walls.add(new Wall(offset + i*50,400,s,s,0));
			}
		}
		else if(index == 5 ){
			for(int i = 0 ; i < 12 ; i++){
				walls.add(new Wall(offset + i*50,650,s,s,1));
				walls.add(new Wall(offset + i*50,600,s,s,1));
				walls.add(new Wall(offset + i*50,550,s,s,1));
				walls.add(new Wall(offset + i*50,500,s,s,0));
				if(i>=8) walls.add(new Wall(offset + i*50,500,s,s,0));
				else walls.add(new Wall(offset + i*50,500,s,s,1));
			}
			for(int i = 0 ; i < 8 ; i++){
				if(i>=4)walls.add(new Wall(offset + i*50,450,s,s,0));
				else walls.add(new Wall(offset + i*50,450,s,s,1));
			}
			for(int i = 0; i < 4 ; i++){
				walls.add(new Wall(offset + i*50,400,s,s,0));
			}
		}
		else if(index == 6 ){
			for(int i = 0 ; i < 16 ; i++){
				walls.add(new Wall(offset + i*50,650,s,s,1));
				walls.add(new Wall(offset + i*50,600,s,s,1));
				walls.add(new Wall(offset + i*50,550,s,s,1));
				if(i<4 || i >11d)walls.add(new Wall(offset + i*50,500,s,s,1));
				else walls.add(new Wall(offset + i*50,500,s,s,0));

			}
			for(int i = 0 ; i < 4 ; i++){
				if(i<2)walls.add(new Wall(offset + i*50,450,s,s,1));
				else walls.add(new Wall(offset + i*50,450,s,s,0));
			}
			for(int i = 12 ; i < 16 ; i++){
				if(i>13)walls.add(new Wall(offset + i*50,450,s,s,1));
				else walls.add(new Wall(offset + i*50,450,s,s,0));
			}
			for(int i = 0 ; i < 2 ; i++){
				walls.add(new Wall(offset + i*50,400,s,s,0));
			}
			for(int i = 14 ; i < 16 ; i++){
				walls.add(new Wall(offset + i*50,400,s,s,0));
			}

		}
		else {
			for(int i = 0 ; i < 4 ; i++){
				walls.add(new Wall(offset + i*50,500,s,s,0));
				walls.add(new Wall(offset + i*50,550,s,s,1));
				walls.add(new Wall(offset + i*50,600,s,s,1));
				walls.add(new Wall(offset + i*50,650,s,s,1));
			}
			for(int i = 6 ; i < 10 ; i++){
				walls.add(new Wall(offset + i*50,500,s,s,0));
				walls.add(new Wall(offset + i*50,550,s,s,1));
				walls.add(new Wall(offset + i*50,600,s,s,1));
				walls.add(new Wall(offset + i*50,650,s,s,1));
			}
			for(int i = 12 ; i <16 ; i++){
				walls.add(new Wall(offset + i*50,500,s,s,0));
				walls.add(new Wall(offset + i*50,550,s,s,1));
				walls.add(new Wall(offset + i*50,600,s,s,1));
				walls.add(new Wall(offset + i*50,650,s,s,1));
			}
		}

	}
	@Override
	public void update() {
		if(walls.get(walls.size() -1).getX() <800){
			offset += 800 ;
			makeWall(offset);

		}
		player.update();
		for (Wall wall : walls) wall.set(CameraX);
	}

	@Override
	public void draw(Graphics g) {
		player.draw((Graphics2D) g);
		for(Wall wall : walls){
			wall.draw((Graphics2D) g);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_Q:
				player.setLeft(true);
				break;
			case KeyEvent.VK_D:
				player.setRight(true);
				break;
			case KeyEvent.VK_SPACE:
				player.setUp(true);
				break;
			case KeyEvent.VK_BACK_SPACE:
				Gamestate.state = Gamestate.MENU;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_Q:
				player.setLeft(false);
				break;
			case KeyEvent.VK_D:
				player.setRight(false);
				break;
			case KeyEvent.VK_SPACE:
				player.setUp(false);
				break;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}


	public ListWall<Wall> getWalls() {
		return walls;
	}
}


