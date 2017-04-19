package motorcargame_display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Display {
	private String title;
	public static int width,height;
	public static JFrame frame,frame2;
	public static JPanel glass,controls;
	public static Canvas canvas,canvas2;
public Display(String title, int width, int height){
	this.title=title;
	this.height=height;
	this.width=width;
	createDisplay();
}
 public void createDisplay(){
	 frame= new JFrame(title);
	 frame.setSize(width, height);
	 frame.setVisible(true);
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.setLocationRelativeTo(null);
	 canvas=new Canvas();
	 canvas.setPreferredSize(new Dimension(width,height));
	 canvas.setFocusable(false); 
	
	/* canvas2=new Canvas();
	 canvas2.setPreferredSize(new Dimension(width,height));
	 canvas2.setFocusable(false); */
	 
	 
	 /*JPanel glass = new JPanel();
     glass.setSize(450, 750);
     glass.add(canvas2);
     glass.setVisible(true);
     
     JPanel controls = new JPanel();
     controls.setSize(450, 750);
    // controls.setBackground(Color.RED);
     controls.add(canvas);
     controls.setVisible(true);

     JSplitPane splitPane = new JSplitPane();
     splitPane.setSize(width, height);
     splitPane.setDividerSize(0);
     splitPane.setDividerLocation(450);
     splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
     splitPane.setLeftComponent(controls);
     splitPane.setRightComponent(glass);

     frame.add(splitPane);*/
	 frame.add(canvas);
     frame.pack();
	 
 }
}
