package tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class tile {

	public static tile[] tiles=new tile[24];
	
	public static tile roadTile= new roadTile(0);
	public static tile grassTile=new grass(1);
	
	public BufferedImage texture;
	
	public static final int tileWidth=64, tileHeight=64;
	public tile(BufferedImage texture,int id){
		this.texture=texture;
		tiles[id]=this;
	}
	
	public void render(Graphics g,int x,int y){
		g.drawImage(texture,x,y,null);
	}
}
