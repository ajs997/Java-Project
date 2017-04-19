package world;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import motor.motor;
import motorcargame_display.Display;
import tile.tile;

public class world {

	private int[][] tilee;
	private int width,height;
	private motor motor;
	public world(motor motor){
		loadWorld("res/world.txt");
		this.motor=motor;
	}
	
	private void loadWorld(String path){
		String file= loadFile(path);
		String[] space=file.split("\\s+");
		width=Integer.parseInt(space[0]);
		 height=Integer.parseInt(space[1]);
		tilee=new int[width][height];
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				tilee[x][y]=Integer.parseInt(space[2+(x+y *width)]) ;
			}
		}
	}
	
	public String loadFile(String path) {
		StringBuilder sr=new StringBuilder();
		try {
			BufferedReader reader=new BufferedReader(new FileReader(path));
		
		    String line;
		    while((line=reader.readLine())!=null){
		    	sr.append(line +"\n");
		    }
		    reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sr.toString();
		
	}
	
	public void render(Graphics g){
		
		int start=Math.max(0,motor.getOfset()/tile.tileHeight );
		int end=Math.min(height, (motor.getOfset()+Display.height +20)/tile.tileHeight);
		
		for(int i=0;i<width ;i++){
			for(int j=start;j<end;j++){
				tile t= tile.tiles[tilee[i][j]]; 
				t.render(g, i*tile.tileWidth, (j*tile.tileHeight)-motor.getOfset());
			}
		}
			
	}
	
}
