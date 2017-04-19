package enemypck;

import java.awt.Graphics;

import graphics.loadImage;
import motor.motor;

public class enemyMotor {

	private int x,y;
	private motor motor;
	public enemyMotor(motor motor,int x,int y){
		this.x=x;
		this.y=y;
		this.motor=motor;
	}
	public void tick(){
		y++;
	}
	public void render(Graphics g){
		g.drawImage(loadImage.enemyMotor, x, y-motor.getOfset(),40,70, null);
	}
	
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	
}
