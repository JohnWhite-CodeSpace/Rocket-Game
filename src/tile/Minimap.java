package tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.GamePanel;

public class Minimap extends TileManager{
	GamePanel gp;
	BufferedImage worldMap;

	public boolean MiniMapOn = false;
	public Minimap(GamePanel gp) {
		super(gp);
		this.gp=gp;
	}
	public void CreateMiniMap() {
		
		int WorldMapWidth = gp.Tilesize*gp.maxScreenCol;
		int WorldMapHeight = gp.Tilesize*gp.maxWorldRow;
		
		worldMap=new BufferedImage(WorldMapWidth, WorldMapHeight,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)worldMap.createGraphics();
		
		int col = 0;
		int row = 0;
		while(col<gp.maxWorldCol&&row<gp.maxWorldRow){
			int tileNum = mapTileNum[col][row];
			int x = gp.Tilesize*col;
			int y = gp.Tilesize*row;
			g2.drawImage(tile[tileNum].image, x, y,null);
			col++;
			if(col==gp.maxWorldCol) {
				col=0;
				row++;
			}
		}
	}
	public void drawFullMapScreen(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int width=250;
		int height=250;
		int x = gp.screenWidth/2-width/2;
		int y = gp.screenHeight/2-height/2;
		g2.drawImage(worldMap,x,y,width,height,null);
	}
}
