package tile;

import java.awt.Color;
import java.awt.Font;
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
		CreateMiniMap();
	}
	public void CreateMiniMap() {
		
		int WorldMapWidth = gp.Tilesize*gp.maxWorldCol;
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
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setColor(Color.green);
		
		
		int width=700;
		int height=700;
		int x = gp.screenWidth/2-width/2;
		int y = gp.screenHeight/2-height/2-3*gp.Tilesize;
		g2.fillRect(x-10, y-10, width+20, height+20);
		g2.drawImage(worldMap,x,y,width,height,null);
		
		
		double scale = (double)(gp.Tilesize*gp.maxWorldCol)/width;
		int playerX = (int)(x+gp.player.worldx/scale);
		int playerY = (int)(y+gp.player.worldy/scale);
		int playerSize = (int)(gp.Tilesize/2);
		g2.drawImage(gp.player.Player1,playerX,playerY,playerSize,playerSize,null);
		int planetX = (int)(x+gp.pluto.worldx/scale);
		int planetY = (int)(y+gp.pluto.worldy/scale);
		int planetSize = (int)(gp.Tilesize/2);
		g2.drawImage(gp.pluto.Planet1,planetX,planetY, planetSize, planetSize, null);
		planetX = (int)(x+gp.neptune.worldx/scale);
		planetY = (int)(y+gp.neptune.worldy/scale);
		g2.drawImage(gp.neptune.Planet1,planetX,planetY,planetSize,planetSize,null );
		planetX = (int)(x+gp.uranus.worldx/scale);
		planetY = (int)(y+gp.uranus.worldy/scale);
		g2.drawImage(gp.uranus.Planet1,planetX,planetY,planetSize,planetSize,null );
		planetX = (int)(x+gp.saturn.worldx/scale);
		planetY = (int)(y+gp.saturn.worldy/scale);
		g2.drawImage(gp.saturn.Planet1,planetX,planetY,planetSize,planetSize,null );
		planetX = (int)(x+gp.jupiter.worldx/scale);
		planetY = (int)(y+gp.jupiter.worldy/scale);
		g2.drawImage(gp.jupiter.Planet1,planetX,planetY,planetSize,planetSize,null );
		int SpaceStationX = (int)(x+gp.spacestation.worldx/scale);
		int SpaceStationY = (int)(y+gp.spacestation.worldy/scale);
		int SpaceStationSize = (int)(gp.Tilesize/2);
		g2.drawImage(gp.spacestation.SpaceStation1,SpaceStationX,SpaceStationY,SpaceStationSize,SpaceStationSize,null );
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial",Font.BOLD,30));
		g2.drawString("World Map", gp.Tilesize, gp.Tilesize);
	}
}
