package racing;

import java.awt.*;

/**
  * Great Ideas in Computer Science: RaceSim
  *
  * @author   Ben Allen (bta@duke.edu)
  * 
  * RaceTrack is an object used by Race.java and extends Canvas.  The track
  * and the car  are drawn here.
  *
  * In general, students should NOT modify any of this code.  Comments have
  * been included for experienced programmers.
  */

public class RaceTrack extends Canvas
{
  private Image offscreen = null;      
  private Graphics dbuffer;
  int imagewidth,imageheight;


  /**
  * Constructor - no variables need to be initialized
  */
  public RaceTrack()
  {
    
  }
  
  /**
  * Converts the simulations x coordinate to a screen x coordinate
  */
  private int screenX(int x)
  {
    Integer temp = new Integer(x/5);
    return temp.intValue();
  }
  
  /**
  * Converts the simulations y coordinate to a screen y coordinate
  */
  private int screenY(int y)
  {
    Integer temp = new Integer((1800-y+200)/5);
    return temp.intValue();
  }
  
  /**
  * Receives x,y coordinate of car and draws graphics off screen.  This
  * procedure was originally coded by Greg Keim and Steve Ruby.  Slight
  * modifications have been made.
  */
  public void draw(int x,int y)
  {
    Dimension d = this.getSize();

    if((offscreen == null) ||
       ((imagewidth!=d.width)
	||(imageheight!=d.height)))
    {				
	offscreen = this.createImage(d.width,d.height);				
	imagewidth = d.width;
	imageheight = d.height;
    }
    Graphics g = offscreen.getGraphics();
    paint(g,x,y);
    g = this.getGraphics();
    g.drawImage(offscreen,0,0,this);

  }

  /**
  * Draws graphics to screen with car at x,y.
  */
  public void paint(Graphics g,int x,int y)
  {
    
      g.setColor(Color.white);
      g.fillRect(0,0,600,600);
    
      g.setColor(Color.green);
      g.drawLine(100,40,100,360);//start/finish
      
      g.setColor(Color.black);
      g.drawLine(100,360,500,360);//bot wall
      g.drawLine(100,200,400,200);//mid wall
      g.drawLine(100,40,500,40);//top wall
      g.drawLine(500,40,500,360);//right wall
      
      g.fillOval(screenX(x),screenY(y),15,15);
    
  }
}