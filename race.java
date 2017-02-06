package racing;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;




public class race extends java.applet.Applet implements Runnable, ActionListener
{

  Button startB;
  car car;
  RaceTrack track;
  Thread animator = null;
  RaceCar auto1;
  Label outLabel;
  int x=0;
  int y=0;
  int time;
  double sec;

  /**
  * Defines a new thread.  Threads are being used to slow the animation
  */
  public void start()
  {
      animator = new Thread(this);
      animator.start();
  }
  
  /**
  * Tells the track to update with new coordinates and puts the thread to
  * sleep for sec seconds
  */
  public void run()
  {
     x=auto1.getXPos(); 
     y=auto1.getYPos();
     track.draw(x,y);
     int temp = (int)(1000 * sec);
     try {Thread.sleep(temp);} catch (InterruptedException e)
       {System.out.println("error in thread sleep");}
  }
  
  /**
  * Makes the current thread null
  */
  public void stop()
  {
    animator = null;
  }
  
  /**
  * Variables initialized and objects added to the applet
  */
  public void init()
  {
    outLabel=new Label ("                         ");
    track = new RaceTrack();
    car=new car();
    startB = new Button("Start");
    startB.addActionListener(this);
    track.setSize(600,400);
    add(car);
    add(track);
    add(startB);
    add(outLabel);
    track.repaint();
    sec=.1;                 //sec * 1 equals delay after each move in seconds
                            //sec = 1 creates a 1 second delay,
                            //sec = .5 creates a half second delay, etc
  }

  /**
  * Button handler method
  */
  public void actionPerformed(ActionEvent event)
  {
    Object cause = event.getSource();
    if (cause == startB) 
    {
       auto1=new RaceCar();
       outLabel.setText(" ");
       x=500;                      //Initial x coordinate
       y=1400;                     //Initial y coordinate
       start(); 
       
       auto1.setPos(x,y);          //Sets the car to start position
       time=1;               
       int a=3;             
       int turnr=0;
       
       while ((time<=10) && ( !complete() )) //complete() has been defined below
       {
         auto1.drive(a,turnr);     //tells car acceleration and turn radius,
                                   //  must be followed by run()
         run();                    //Updates graphics on screen
         time=time+1;              //Increment the simulation time
       }
       a=0;
       while ((x<2000) && ( !complete() ) )  
       {
         auto1.drive(a,turnr);
         run();
         time=time+1;
       }
       turnr=400;
       while ( ((x>2000) || (y>1000)) && ( !complete() ) )
       {
         auto1.drive(a,turnr);
         run();
         time=time+1;
       }
       a=3;
       turnr=0;
       while (!complete())
       {
         auto1.drive(a,turnr);
         run();
         time=time+1;
       }  
       stop();                     //Makes thread null
       output();                   //Prints output of race to screen
    }
  }
  
  /**
  * Outputs the result of the race to the screen
  */
  private void output()
  {
    String tempString = new String();
    if ((complete()) && (!finish()))
      tempString = "Crashed ";
    else 
      tempString = "Finished ";
    Integer tempInt = new Integer(time-1);
    tempString=tempString + "in " +tempInt.toString() +" seconds";
    outLabel.setText(tempString);
  }
  
  /**
  * Returns true if the race is over (crash or finished) and false if it
  * is still going
  */
  private boolean complete()
  {
    if (auto1.state()>0)
      return true;
    return false;
  }
  
  /**
  * Returns true if race successfully finished or false if crash resulted
  * Assumes that complete() is true. If complete is not true, procedure
  * returns false
  */
  private boolean finish()
  {
    if (complete())
      if (auto1.state()==2)
        return true;
      else
        return false;
    return false;
  }
}