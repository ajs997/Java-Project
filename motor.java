package motor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import graphics.loadImage;
import motorcargame_display.Display;
import tile.tile;

public class motor{

	public int x,y,p1x,p1y;
	public int ofset,gear,health,speed1;
	public double speed;
	public boolean left,right,up,down;
	public motor(){
		
		ofset=0;
		speed=0.3f;
		health=3;
		gear=0;
		
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
	public int getHealth(){
		return this.health;
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
	
	public void drawBoard(Graphics g,int p1x,int p1y){
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
		g.fillRect(p1x, p1y, 120, 80);
		g.setColor(Color.BLACK);
		String sgear=Integer.toString(gear);
		g.setFont(new Font("arial",Font.BOLD,24));
		g.drawString("gear : "+sgear, p1x+20, p1y+40);
		g.drawString("health : "+health, p1x+20, p1y+80);
	}
	
	
	public void render(Graphics g,int im,int p1x,int p1y){
		
		if(health>0){
		g.setColor(Color.red);
		if(im==1)
		g.drawImage(loadImage.playerMotor,x, y-ofset, 40, 70,null);
		else
			g.drawImage(loadImage.playerMotor2,x, y-ofset, 40, 70,null);
		}
	
		drawBoard(g,p1x,p1y);
	
	}
	
	
}
