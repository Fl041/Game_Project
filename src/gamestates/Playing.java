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
	private int indiceWall;
	private int score ;

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
		player.setY(300);
		CameraX = 150;
		player.xspeed=0;
		player.yspeed=0;
		walls.clear();
		offset = -150;
		indiceWall = 1 ;
		makeWall(offset,indiceWall);
		score = 0 ;
	}

	public  void makeWall(int offset,int indiceWall){
		Random rand = new Random();
		int index = rand.nextInt(indiceWall);
		if(index == 0 ){
			make_straight_line(offset);
			setIndiceWall(6);
		}
		else if(index == 1 ){
			make_platforme2(offset);
			setIndiceWall(6);
		}
		else if(index == 2 ){
			make_platforme1(offset);
			setIndiceWall(8);
		}
		else if(index == 3 ){
			make_platforme3(offset);
			setIndiceWall(6);
		}
		else if(index == 4 ){
			make_ascension(offset);
			setIndiceWall(6);
		}
		else if(index == 5 ){
			make_straight_line2(offset);
			setIndiceWall(6);
		}
		else if(index == 6 ){
			make_hollow(offset);
			setIndiceWall(8);
		}
		else {
			make_descent(offset);
			setIndiceWall(8);
		}

	}
	@Override
	public void update() {
		if(walls.get(walls.size() -1).getX() <800){
			offset += 800 ;
			makeWall(offset,indiceWall);
		}
		player.update();
		for (Wall wall : walls) {
			wall.set(CameraX);
		}
	}

	@Override
	public void draw(Graphics g) {
		player.draw((Graphics2D) g);
		for(Wall wall : walls){
			wall.draw((Graphics2D) g);
		}
		Font f =new Font(null , Font.BOLD , 20);
		g.setFont(f);
		g.drawString("Score : " + score,550,50);
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

	public void setIndiceWall(int indiceWall) {
		this.indiceWall = indiceWall;
	}

	public void make_straight_line(int offset){
		for(int i = 0 ; i<14 ; i++){
			walls.add(new Wall(offset + i*50,500,0));
			walls.add(new Wall(offset + i*50,550,1));
			walls.add(new Wall(offset + i*50,600,1));
			walls.add(new Wall(offset + i*50,650,1));
		}
	}
	public void make_straight_line2(int offset) {
		for(int i = 0 ; i < 4 ; i++){
			walls.add(new Wall(offset + i*50,500,0));
			walls.add(new Wall(offset + i*50,550,1));
			walls.add(new Wall(offset + i*50,600,1));
			walls.add(new Wall(offset + i*50,650,1));
		}
		for(int i = 6 ; i < 10 ; i++){
			walls.add(new Wall(offset + i*50,500,0));
			walls.add(new Wall(offset + i*50,550,1));
			walls.add(new Wall(offset + i*50,600,1));
			walls.add(new Wall(offset + i*50,650,1));
		}
		for(int i = 12 ; i <16 ; i++){
			walls.add(new Wall(offset + i*50,500,0));
			walls.add(new Wall(offset + i*50,550,1));
			walls.add(new Wall(offset + i*50,600,1));
			walls.add(new Wall(offset + i*50,650,1));
		}
	}
	public void make_platforme1(int offset){
		for(int i = 0 ; i < 4 ; i++){
			walls.add(new Wall(offset + i*50,500,0));
		}
		for(int i = 6 ; i < 10 ; i++){
			walls.add(new Wall(offset + i*50,450,0));
		}
		for(int i = 12 ; i < 16 ; i++){
			walls.add(new Wall(offset + i*50,400,0));
		}
	}
	public void make_platforme2(int offset){
		for(int i = 0 ; i < 4 ; i++){
			walls.add(new Wall(offset + i*50,500,0));
		}
		for(int i = 6 ; i < 10 ; i++){
			walls.add(new Wall(offset + i*50,450,0));
		}
		for(int i = 12 ; i < 16 ; i++){
			walls.add(new Wall(offset + i*50,500,0));
		}
	}

	public void make_platforme3(int offset){
		for(int i = 0 ; i < 4 ; i++){
			walls.add(new Wall(offset + i*50,500,0));
		}
		for(int i = 6 ; i < 10 ; i++){
			walls.add(new Wall(offset + i*50,550,0));
		}
		for(int i = 12 ; i < 16 ; i++){
			walls.add(new Wall(offset + i*50,500,0));
		}
	}

	public void make_ascension(int offset){
		for(int i = 0 ; i < 12 ; i++){
			walls.add(new Wall(offset + i*50,650,1));
			walls.add(new Wall(offset + i*50,600,1));
			walls.add(new Wall(offset + i*50,550,1));
			if(i<4) walls.add(new Wall(offset + i*50,500,0));
			else walls.add(new Wall(offset + i*50,500,1));

		}
		for(int i = 4 ; i < 12 ; i++){
			if(i<8)walls.add(new Wall(offset + i*50,450,0));
			else walls.add(new Wall(offset + i*50,450,1));
		}
		for(int i = 8 ; i < 12 ; i++){
			walls.add(new Wall(offset + i*50,400,0));
		}
	}

	public void make_descent(int offset){
		for(int i = 0 ; i < 12 ; i++){
			walls.add(new Wall(offset + i*50,650,1));
			walls.add(new Wall(offset + i*50,600,1));
			walls.add(new Wall(offset + i*50,550,1));
			walls.add(new Wall(offset + i*50,500,0));
			if(i>=8) walls.add(new Wall(offset + i*50,500,0));
			else walls.add(new Wall(offset + i*50,500,1));
		}
		for(int i = 0 ; i < 8 ; i++){
			if(i>=4)walls.add(new Wall(offset + i*50,450,0));
			else walls.add(new Wall(offset + i*50,450,1));
		}
		for(int i = 0; i < 4 ; i++){
			walls.add(new Wall(offset + i*50,400,0));
		}
	}

	public void make_hollow(int offset){
		for(int i = 0 ; i < 16 ; i++){
			walls.add(new Wall(offset + i*50,650,1));
			walls.add(new Wall(offset + i*50,600,1));
			walls.add(new Wall(offset + i*50,550,1));
			if(i<4 || i >11d)walls.add(new Wall(offset + i*50,500,1));
			else walls.add(new Wall(offset + i*50,500,0));

		}
		for(int i = 0 ; i < 4 ; i++){
			if(i<2)walls.add(new Wall(offset + i*50,450,1));
			else walls.add(new Wall(offset + i*50,450,0));
		}
		for(int i = 12 ; i < 16 ; i++){
			if(i>13)walls.add(new Wall(offset + i*50,450,1));
			else walls.add(new Wall(offset + i*50,450,0));
		}
		for(int i = 0 ; i < 2 ; i++){
			walls.add(new Wall(offset + i*50,400,0));
		}
		for(int i = 14 ; i < 16 ; i++){
			walls.add(new Wall(offset + i*50,400,0));
		}
	}

}


