package motor2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import graphics.loadImage;
import motorcargame_display.Display;
import tile.tile;

public class motor2 implements KeyListener {

	private int x,y;
	private int ofset,gear,health,speed1;
	private double speed;
	private boolean left,right,up,down;
	public motor2(){
		x=Display.width/2;
		y=tile.tileHeight*120;
		ofset=0;
		speed=0.3f;
		health=3;
		gear=0;
		
	}
	public void init(){
		Display.controls.addKeyListener(this);
	
	}
	public int getx()
	{
		return x;
	}
	public int gety(){
		return y;
	}
	
	public void setSpeed(double s){
		speed=s;
	}
	public void setHealth(int health){
		this.health=health;
	}
	
	public void tick(){
		
		if(health>0){
		ofset=y-(Display.height-100);
	if(right){
		x++;
	}
	if(left){
		x--;
	}
	if(up){
		speed+=0.03f;
		if(speed>=7){
			speed=7;
		}
	}
	y-=speed;

	if(down)
	{
	speed-=0.03f;
	if(speed<=0){
		speed=0;
	}
	}
	}
	}
	public int getOfset(){
		return ofset;
	}
	
	public double getSpeed(){
		return speed;
	}
	
	public void drawBoard(Graphics g){
		speed1=(int)speed;
		switch(speed1){
		case 0:gear=0;
		break;
		case 2:gear=1;
		break;
		case 4:gear=2;
		break;
		case 6:gear=3;
		break;
		}
		
		g.setColor(Color.WHITE);
		g.fillRect(10, 10, 150, 80);
		g.setColor(Color.BLACK);
		String sgear=Integer.toString(gear);
		g.setFont(new Font("arial",Font.BOLD,28));
		g.drawString("gear : "+sgear, 20, 40);
		g.drawString("health : "+health, 20, 80);
	}
	
	public void gameOver(Graphics g){
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,33));
		g.drawString("Game Over", Display.width/3, Display.height/2);
	}
	
	public void render(Graphics g){
		
		if(health>0){
		g.setColor(Color.red);
		g.drawImage(loadImage.playerMotor,x, y-ofset, 40, 70,null);
		}
		else{
			gameOver(g);		}
		drawBoard(g);
	
	}
	
	public void keyTyped(KeyEvent e) {
		char source=e.getKeyChar();
		if(source=='d'){
			right=true;
		}
		if(source=='a'){
			left=true;
		}
		if(source=='w'){
			up=true;
		}
		if(source=='s'){
			down=true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		int source=e.getKeyChar();
		if(source=='d'){
			right=false;
		}
		if(source=='a'){
			left=false;
		}
		if(source=='w'){
			up=false;
		}
		if(source=='s'){
			down=false;
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		
	}
}
