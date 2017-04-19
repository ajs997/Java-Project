package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class loadImage {
	
	public static BufferedImage fullImage,Motors,playerMotor,playerMotor2,enemyMotor,road,grass;
	
public static void init(){
	
	road=imageLoader("/road.jpg");
	grass=imageLoader("/grass.jpg");
    playerMotor=imageLoader("/player.jpg");
    playerMotor2=imageLoader("/player2.jpg");
	enemyMotor=imageLoader("/enemy.jpg");
}
public static BufferedImage imageLoader(String path){
	try {
		return ImageIO.read(loadImage.class.getResource(path));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.exit(1);
	}
return null;
}





}
