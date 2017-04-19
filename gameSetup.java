package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import manager.gameManager;
import motorcargame_display.Display;

public class gameSetup implements Runnable {
  
	private Thread thread;
	private Display display;
	private int width,height;
	private String title;
	private BufferStrategy buffer,buffer2;
	private Graphics g2,g;
	private gameManager manager;
	public gameSetup(String title, int width,int height){
		this.title=title;
		this.height=height;
		this.width=width;
	}
	public void init(){
		display=new Display(title,width,height); 
		manager=new gameManager();
		manager.init();
		}
	
	public synchronized void start(){
		if(thread==null){
			
		
		thread=new Thread(this);
		thread.start();
		}
		
	}
	public synchronized void stop(){
		try {
			thread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	public void render(){
		buffer= display.canvas.getBufferStrategy();
		//buffer2= display.canvas2.getBufferStrategy();
		if(buffer==null){
			display.canvas.createBufferStrategy(3);
			return;
		}
		g= buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		//draw
	manager.render(g);
		
		buffer.show();
		g.dispose();
		
			
		/*if(buffer2==null){
			display.canvas2.createBufferStrategy(3);
			return;
		}
		g2= buffer2.getDrawGraphics();
		g2.clearRect(0, 0, width, height);
		//draw
	manager.render(g2);
		
		buffer2.show();
		g2.dispose();*/
	}
	public void tick()
	{
		manager.tick(g);
		
	}
	
	public void run(){
		init(); 
		int fps=50;
		double timePerTick=1000000000/fps;
		double delta=0;
		long current=System.nanoTime();
		
		while(true){
			delta=delta+(System.nanoTime()-current)/timePerTick;
			current=System.nanoTime();
			if(delta>=1)
			{
				tick();
				render(); 
				delta--;
			}
		}
		
	}
}
