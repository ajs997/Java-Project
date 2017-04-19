package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import enemypck.enemyMotor;
import graphics.loadImage;
import motor.motor;
import motorcargame_display.Display;
import tile.tile;
import world.world;

public class gameManager implements KeyListener {
	private world world;
	private motor motor;
	private motor motor2;
	private long time=System.nanoTime();
	private long delay;
	private int health,health2;
	
	private ArrayList<enemyMotor> eMotor;
	
	public gameManager(){
		motor=new motor();
		motor2=new motor();
		world=new world(motor);
		eMotor= new ArrayList<enemyMotor>();
		delay=2000; 
		health=3;
		health2=3;
		motor2.x=Display.width/3;
		motor.y=tile.tileHeight*120;
		motor.x=2*Display.width/3;
		motor2.y=tile.tileHeight*120;
	}
	public void init(){
		
		Display.frame.addKeyListener(this);
		 
		
		loadImage.init(); 
	}
	public void tick(Graphics g){
		
		Random rand=new Random();
		int randx=rand.nextInt(300);
		int randy=rand.nextInt(Display.height);
		
		while(randx<150){
			randx=rand.nextInt(300);
		}
		
		for(int i=0;i<eMotor.size();i++){
			int px=motor.getx();
			int py=motor.gety();
			int px2=motor2.getx();
			int py2=motor2.gety();
			
			int ex=eMotor.get(i).getx();
			int ey=eMotor.get(i).gety();
			
			
			if(px2<ex+40 && px2+40>ex && py2<ey+4 && py2+4>ey){
				eMotor.remove(i);
				i--;
				health2--;
				System.out.println("2:  "+health2);
				motor2.setSpeed(0);
				motor2.setHealth(health2);
			}
			if(px<ex+40 && px+40>ex && py<ey+40 && py+40>ey){
				eMotor.remove(i);
				i--;
				health--;
				System.out.println("1:  "+health);
				motor.setSpeed(0);
				motor.setHealth(health);
			}
			
		}
		

		if(health==0)
			gameOver(g,1);//g.drawString("gameover player 2 is winner",50,50);
		else if(health2==0)
			gameOver(g,2);
			//g.drawString("gameover player 1 is winner",50,50);
		
		
				long elapsed=(System.nanoTime()-time)/1000000;
		if(elapsed>delay)
		{
			if(motor.getSpeed()>=3 || motor2.getSpeed()>=3)
			eMotor.add(new enemyMotor(motor,randx,(-randy)+motor.getOfset()));
			time=System.nanoTime();
		}
		
		
		motor.tick();
		motor2.tick();
		for(int i=0;i<eMotor.size();i++){
			eMotor.get(i).tick();
		}
		
	} 
	public void render(Graphics g){
		world.render(g);
		motor2.render(g,2,10,10);
		motor.render(g,1,370,10);
		for(int i=0;i<eMotor.size();i++){
			eMotor.get(i).render(g);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int source=e.getKeyCode();
		if(source==KeyEvent.VK_RIGHT){
			motor.right=true;
		}
		if(source==KeyEvent.VK_LEFT){
			motor.left=true;
		}
		if(source==KeyEvent.VK_UP){
			motor.up=true;
		}
		if(source==KeyEvent.VK_DOWN){
			motor.down=true;
		}
		char source1=e.getKeyChar();
		if(source1=='d'){
			motor2.right=true;
		}
		if(source1=='a'){
			motor2.left=true;
		}
		if(source1=='w'){
			motor2.up=true;
		}
		if(source1=='s'){
			motor2.down=true;
		}
		
	}	
	public void keyReleased(KeyEvent e) {
	
		int source=e.getKeyCode();
		if(source==KeyEvent.VK_RIGHT){
			motor.right=false;
		}
		if(source==KeyEvent.VK_LEFT){
			motor.left=false;
		}
		if(source==KeyEvent.VK_UP){
			motor.up=false;
		}
		if(source==KeyEvent.VK_DOWN){
			motor.down=false;
		}
		char source1=e.getKeyChar();
		if(source1=='d'){
			motor2.right=false;
		}
		if(source1=='a'){
			motor2.left=false;
		}
		if(source1=='w'){
			motor2.up=false;
		}
		if(source1=='s'){
			motor2.down=false;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
		
	}
	

public void gameOver(Graphics g,int ans){
		Thread t=Thread.currentThread();
		g.setColor(Color.black);
		g.setFont(new Font("arial",Font.BOLD,33));
	    System.out.println("winner is player "+ans);
	   // g.drawString("Game Over. \nWinner is player"+ans, Display.width/3, Display.height/2);	
	    JOptionPane.showMessageDialog(null, "Game Over. \nWinner is player"+ans);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	
}
