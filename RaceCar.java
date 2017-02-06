package racing;
import java.applet.Applet;
import java.awt.*;
import java.lang.*;

/**
  * Great Ideas in Computer Science: RaceSim
  *
  * @author   Ben Allen (bta@duke.edu)
  * 
  * RaceCar is an object used by Race.java.  The object receives an
  * acceleration variable and a turn radius variable and computes what
  * the next step will be.  The race's coordinate system is being used.
  * Screen coordinates are figured out by RaceTrack.java.
  *
  * In general, students should NOT modify any of this code.  Comments
  * have been included for experienced programmers.
  */

public class RaceCar extends Applet 
{
  protected double[] state = new double[10];  
  protected int amax;
  protected int amin;
  public final int FIGUREHEIGHT = 2;
  public final int FIGUREWIDTH  = 3;

  /**
  * Constructor - state variables initialized
  */
  public RaceCar()
  {
     state[1]=0;      //  x position
     state[2]=0;      //  y position
     state[3]=0;      //  x velocity
     state[4]=0;      //  y velocity
     state[5]=1;      //  x direction
     state[6]=0;      //  y direction
     state[7]=1;      //  1 if not skidding; 0 if skidding  (control)
     state[8]=0;      //  0 not complete; 1 crashed into wall; 2 complete
     state[9]=0;      //  time
     amax=9;
     amin=-amax;
  }
  
  /**
  * Returns the state of the car as an int
  */
  public int state()
  {
    return (int)state[8];
  }

  /**
  * Sets the x,y position of the car in the simulation (not the screen)
  */
  
  public void paint (Graphics myCar)
	// all paint methods require a Graphics parameter)
{
	  
	  Color REDBROWN = new Color ( 182, 100, 110 ) ;
      Color DARKBROWN = new Color ( 150, 70, 80 ) ;
      Color LTBLUE = new Color ( 35, 206, 255 ) ;
      Color DARKGOLD = new Color ( 240, 220, 0 ) ;
	// background for figure
      myCar.setColor ( Color.white ) ;
      myCar.fillRect ( 0, 0, FIGUREWIDTH, FIGUREHEIGHT ) ;

	// front tire
      myCar.setColor ( Color.black ) ;
myCar.fillOval ( 20, 170, 100, 100 ) ;
      myCar.setColor ( Color.white ) ;
myCar.fillOval ( 30, 180, 80, 80 ) ;
      myCar.setColor ( Color.black ) ;
myCar.drawOval ( 40, 190, 60, 60 ) ;

	// back tire
      myCar.setColor ( Color.black ) ;
myCar.fillOval ( 270, 170, 100, 100 ) ;
      myCar.setColor ( Color.white ) ;
myCar.fillOval ( 280, 180, 80, 80 ) ;
      myCar.setColor ( Color.black ) ;
myCar.drawOval ( 290, 190, 60, 60 ) ;

	// car hood
      myCar.setColor ( Color.blue) ;
myCar.fillRect ( 10, 113, 122, 12 ) ;
      myCar.setColor (Color.pink);
myCar.fillRect ( 10, 123, 122, 82 ) ;

	// car hood ornament
      myCar.setColor ( DARKGOLD ) ;
myCar.fillOval ( 10, 105, 10, 10 ) ;

	//  car window
      myCar.setColor ( LTBLUE ) ;
      myCar.fillRect ( 130, 15, 130, 100 ) ; 

	// car door
      myCar.setColor ( REDBROWN ) ;
myCar.fillRect ( 130, 113, 130, 92 ) ;

	// car backseat
      myCar.setColor ( REDBROWN ) ;
myCar.fillRect ( 258, 15, 122, 190 ) ;

	// car trunk
      myCar.setColor ( REDBROWN ) ;
myCar.fillRect ( 378, 80, 57, 125 ) ;

	// car running board
      myCar.setColor ( DARKBROWN ) ;
myCar.fillRect ( 118, 205, 154, 10 ) ;

	// visor
      myCar.setColor ( Color.black ) ;
myCar.drawLine ( 131, 15, 110, 30) ;
myCar.drawLine ( 131, 16, 110, 31) ;
myCar.drawLine ( 131, 17, 110, 32) ;

	// door handle
      myCar.setColor ( Color.black ) ;
myCar.drawLine ( 145, 125, 170, 125) ;
myCar.drawLine ( 145, 124, 170, 124) ;
myCar.drawLine ( 145, 123, 170, 123) ;
}
  
  public void setPos(int xPos,int yPos)
  {
    state[1]=xPos;
    state[2]=yPos;
  }
  
  /**
  * Returns the current x coordinate of the car as an Int (rounding will occur)
  */
  public int getXPos()
  {
     return (int)state[1];  
  }
  
  /**
  * Returns the current y coordinate of the car as an Int (rounding will occur)
  */
  public int getYPos()
  {
     return (int)state[2];
  }
  
  /**
  * Returns the current x velocity of the car as an Int (rounding will occur)
  */
  public int getXVelocity()
  {
    return (int)state[3];
  }
  
  /**
  * Determines whether the car has crossed a wall and thus crashed.
  * State[8] variable is modified accordingly
  */
  private void state8(int x0,int y0,int x1,int y1)
  {
    if (x1>2500)
      state[8]=1;
    if ((y1>1800) ||(y1<200))
      state[8]=1;
    if ( (y0>1000) && (y1<1000) )
      if(x1<2000)
        state[8]=1;
    if ( (y0<1000) && (y1>1000) )
      if(x1<2000)
        state[8]=1; 
           
    if ((y1<1000) && (x1<500))
      state[8]=2;
  }
  
  /**
  * Recieves acceleration and turn radius and decides car's next position.
  * Both skid and crash are checked.  This procedure was originally
  * written for Great Ideas in Computer Science, Second Edition.
  */
  public void drive(int a,double tr)
  {
   double xold,yold,vel,le,le2;
   xold=0;
   yold=0;
   double slopepathi,slopepathf,sloperadiusi,sloperadiusf;
   double xnew,ynew,signe;
   double xcenter=0;
   double ycenter=0;
   int flagpathi,flagradiusi,flagpathf,flagradiusf;
   int LEFT, RIGHT;
   double turndir;
   int gvar, gtvar;
   turndir = 0;
   LEFT = -1; RIGHT = 1;
   state[9] = state[9] + 1;
   if (state[8] == 0) //if the boundaries have not been violated then
   {
        if (state[7] == 0)   //if control has been lost
        {
           xold = state[1];
           yold = state[2];
           state[1] = state[1] + state[3];
           state[2] = state[2] + state[4];
        }
        else  //when control has not been lost
        {
           if (a > amax) 
             a = amax;
           if (a < amin) 
             a = amin;
           xold = state[1];
           yold = state[2];
           state[3] = state[3] + state[5]*a;           //update velocity
           state[4] = state[4] + state[6]*a;
           vel = java.lang.Math.sqrt(state[3]*state[3] + state[4]*state[4]);
           state[1] = state[1] + state[3];             //update position
           state[2] = state[2] + state[4];
           if (tr != 0)                  //if the car is turning
           {
              if ( ((vel*vel)/java.lang.Math.abs(tr)) > amax) //check to see
              {                                        // if control is lost 
                 state[7] = 0;
                 tr = 0;
              }
           }
           if (tr != 0)                  //if turn is to be made
           {
                 if ( java.lang.Math.abs(state[1]-xold) < 0.001 ) //find
                 {                                  // slope of path (initial)
                        flagpathi = 1;
                        flagradiusi = 0;
                        slopepathi = 1000;
                        sloperadiusi = 0;
                 }
                 else
                 {
                        flagpathi = 0;
                        slopepathi = (state[2]-yold)/(state[1]-xold);
                 }
                  
                 if ((java.lang.Math.abs(slopepathi) < 0.001) && (flagpathi==0))
                 {//find slope of radius (initial)
                        flagradiusi = 1;
                        sloperadiusi = 1000;
                 }
                 else
                 {
                        flagradiusi = 0;
                        sloperadiusi = -1/slopepathi;
                 }
                
                 if (tr < 0) 
                 {
                   turndir = LEFT;
                 }
                 else 
                 {
                   turndir = RIGHT;
                 }
                 
                 if ( (flagpathi == 0) && (flagradiusi == 0) )
                 {
                    if (slopepathi > 0)
                      signe = 1; 
                    else 
                      signe = -1;
                    
                    if ((xold-state[1]) > 0) 
                      signe = -signe;
                      
                    xcenter = xold +
                       signe*turndir*java.lang.Math.sqrt((tr*tr)/
                       (1 + sloperadiusi*sloperadiusi));
                    ycenter = yold + sloperadiusi*(xcenter-xold);
                           //compute position of center of turning circle
                 }
                 
                 if ((flagpathi == 1) && (flagradiusi == 0))
                 {
                    if (state[2] > yold)
                    {
                       xcenter = xold + tr;
                       ycenter = yold;
                    }
                    else
                    {
                       xcenter = xold - tr;
                       ycenter = yold;
                    }
                 }
                 if ((flagpathi == 0) && (flagradiusi == 1) )
                 {
                    if (state[1] > xold) 
                    {
                       xcenter = xold;
                       ycenter = yold - tr;
                    }
                    else
                    {
                       xcenter = xold;
                       ycenter = yold + tr;
                    }
                 }
                 le2 = (state[1]-xcenter)*(state[1]-xcenter)  +
                           (state[2]-ycenter)*(state[2]-ycenter);
                 le = java.lang.Math.sqrt(le2);  //find distance from center
                                                 // to position
                 xnew = xcenter+(java.lang.Math.abs(tr)/le)*(state[1]-xcenter);
                 ynew = ycenter+(java.lang.Math.abs(tr)/le)*(state[2]-ycenter);
                              //find new position
                 if (java.lang.Math.abs(state[1]-xcenter) < 0.001)//find
                                                      // slope - radius(final)
                 {
                    flagpathf = 0;
                    flagradiusf = 1;
                    slopepathf = 0;
                    sloperadiusf = 1000;
                 }
                 else
                 {
                    flagradiusf = 0;
                    sloperadiusf = (state[2]-ycenter)/(state[1]-xcenter);
                 }
                 if ((java.lang.Math.abs(sloperadiusf)<0.001)&&(flagradiusf==0))
                              //find slope of path (final)
                 {
                    flagpathf = 1;
                    slopepathf = 1000;
                 }
                 else
                 {
                    flagpathf = 0;
                    slopepathf = -1/sloperadiusf;
                 }
                 state[1] = xnew;
                 state[2] = ynew;
                 if ( (flagpathf == 0) && (flagradiusf == 0))
                 {
                    if ((sloperadiusf*turndir) > 0) 
                      signe = 1;
                    else 
                      signe = -1;
                    if ((state[1]-xcenter) < 0) 
                      signe = -signe;
                    state[3] = signe*java.lang.Math.sqrt((vel*vel)/
                                                  (slopepathf*slopepathf+1));
                    state[4] = slopepathf*state[3];
                    state[5] = signe*java.lang.Math.sqrt(1/
                                                  (slopepathf*slopepathf+1));
                    state[6] = slopepathf*state[5];
                 }
                 if ((flagpathf == 0) && (flagradiusf == 1))
                 {
                    if ( ((ycenter-ynew)*(0-turndir)) > 0 ) 
                      signe = 1;
                    else 
                      signe = -1;
                    state[3] = vel * signe;
                    state[4] = 0;
                    state[5] = signe;
                    state[6] = 0;
                 }
                 if ((flagpathf == 1) && (flagradiusf == 0)) 
                 {
                    if ( ((xcenter-xnew)*turndir) > 0 ) 
                      signe = 1; 
                    else 
                      signe = -1;
                    state[3] = 0;
                    state[4] = vel * signe;
                    state[5] = 0;
                    state[6] = signe;
                 }
              }
           }
        }
        state8((int)xold,(int)yold,(int)state[1],(int)state[2]); //check state8
   }
}