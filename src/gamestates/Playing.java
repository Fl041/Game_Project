package gamestates;

import Entities.*;
import Game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Playing extends State implements Statemethods {
	private Player player;
	private Buttons buttons;
	private ListUpdate<Wall> walls = new ListUpdate<>(400);
	private ListUpdate<Enemy> ennemies = new ListUpdate<>(2);
	public int CameraX;
	private int offset;
	private int indiceWall;

	public Playing(Game game) {
		super(game);
		initClasses();
	}


	private void initClasses() {
		player = new Player(400,300 , 72,58,this);
		buttons = new Buttons(50,23,100,27,7,0,Gamestate.PLAYING,Gamestate.MENU,this.game);
		reset();

	}

	public void reset(){
		player.setWidth(72);
		player.setHeight(58);
		player.setX(200);
		player.setY(300);
		player.setIsdead(false);
		CameraX = 150;
		player.xspeed=0;
		player.yspeed=0;
		player.nbleft=0;
		player.score = 0;
		walls.clear();
		offset = -150;
		indiceWall = 1 ;
		makeWall(offset,indiceWall);
	}
	public void deathScene(){
		player.setWidth(144);
		player.setHeight(116);
		player.setX(250);
		player.setY(300);
		player.xspeed=0;
		player.yspeed=0;
	}

	public  void makeWall(int offset,int indiceWall){
		Random rand = new Random();
		int index = rand.nextInt(indiceWall);
		if(index == 0 ){
			make_straight_line(offset);
			setIndiceWall(6);
		}
		else if(index == 1 ){
			make_platform2(offset);
			setIndiceWall(6);
		}
		else if(index == 2 ){
			make_platform1(offset);
			setIndiceWall(8);
		}
		else if(index == 3 ){
			make_platform3(offset);
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
		if(player.isalive()){
			if(walls.get(walls.size() -1).getX() <800){
				offset += 800 ;
				makeWall(offset,indiceWall);
			}
			player.update();
			buttons.update();
			for (Wall wall : walls) {
				wall.set(CameraX);
			}
			for(Enemy enemy : ennemies){
				enemy.update(CameraX);
			}
			}
		else {
			player.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		if(player.isalive()) {
			player.draw((Graphics2D) g);
			buttons.draw(g);
			for(Wall wall : walls){
				wall.draw((Graphics2D) g);
			}
			for(Enemy enemy : ennemies){
				enemy.draw((Graphics2D) g);
			}

			Font f =new Font(null , Font.BOLD , 20);
			g.setFont(f);
			g.drawString("Score : " + player.score,550,50);
		}
		else{
			Font f =new Font(null , Font.BOLD , 20);
			g.setFont(f);
			g.drawString("You died",250,200);
			player.draw((Graphics2D) g);
			buttons.draw(g);
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
	public void resetButtons() {
			buttons.resetBools();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isIn(e, buttons)) {
			buttons.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isIn(e, buttons)) {
			if (buttons.isMousePressed())
				buttons.applyGamestate();
		}
		buttons.resetBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		buttons.setMouseOver(false);
			if (isIn(e, buttons)) {
				buttons.setMouseOver(true);
			}
	}

	public Player getPlayer() {
		return player;
	}

	public void setGameState(Gamestate state){
		Gamestate.state = state;
	}
	public ListUpdate<Wall> getWalls() {
		return walls;
	}
	public ListUpdate<Enemy> getEnnemy() {
		return ennemies;
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
		ennemies.add(new Enemy(offset+600 , 400,100,100,player));
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
	public void make_platform1(int offset){
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
	public void make_platform2(int offset){
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
	public void make_platform3(int offset){
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


