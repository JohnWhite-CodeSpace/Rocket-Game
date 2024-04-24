package tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;

public class Minimap extends TileManager{
	GamePanel gp;
	BufferedImage worldMap=null;
	Font font4;
	public boolean MiniMapOn = false;
	public Minimap(GamePanel gp) {
		super(gp);
		this.gp=gp;
		CreateMiniMap();
		font4 = new Font("Arial",Font.BOLD,15);
	}
	public void CreateMiniMap() {	
		int worldMapWidth = 1600;
		int worldMapHeight = 1600;
		worldMap = new BufferedImage(worldMapWidth,worldMapHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)worldMap.createGraphics();
		int col=0;
		int row=0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[col][row];
			int x = 16 * col/10;
			int y = 16 * row/10;
			
			g2.drawImage(tile[tileNum].image, x, y, null);
			col+=10;
			
			if(col == gp.maxWorldCol) {
				col=0;
				row+=10;
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
		int y = gp.screenHeight/2-height/2;
		g2.fillRect(x-10, y-10, width+20, height+20);
		g2.drawImage(worldMap,x,y,width,height,null);

		
		double scale = (double)(gp.Tilesize*gp.maxWorldCol)/width;
		int playerX = (int)(x+gp.player.worldx/scale);
		int playerY = (int)(y+gp.player.worldy/scale);
		int playerSize = (int)(gp.Tilesize/2);
		int planetX =0;
		int planetY=0;
		int planetSize=0;
		g2.drawImage(gp.player.Entity1,playerX,playerY,playerSize,playerSize,null);
		for(int i=0; i<gp.SolarSystem.size(); i++) {
			planetX = (int)(x+gp.SolarSystem.get(i).worldx/scale);
			planetY = (int)(y+gp.SolarSystem.get(i).worldy/scale);
			planetSize = (int)(gp.Tilesize/3);
			g2.drawImage(gp.SolarSystem.get(i).Entity1,planetX,planetY, planetSize, planetSize, null);
		}
		planetSize=(int)(gp.Tilesize/2);
		planetX = (int)(x+gp.sol.worldx/scale);
		planetY = (int)(y+gp.sol.worldy/scale);
		g2.drawImage(gp.sol.Entity1,planetX,planetY,planetSize,planetSize,null );
		
		int SpaceStationX = (int)(x+gp.spacestation.worldx/scale);
		int SpaceStationY = (int)(y+gp.spacestation.worldy/scale);
		int SpaceStationSize = (int)(gp.Tilesize/2);
		g2.drawImage(gp.spacestation.Entity1,SpaceStationX,SpaceStationY,SpaceStationSize,SpaceStationSize,null );
		
		for(int i=0; i<gp.asteroidBelt.length;i++) {
			if(gp.asteroidBelt[i]!=null) {
				int asteroidSize = (int)(gp.Tilesize/3);
				planetX = (int)(x+gp.asteroidBelt[i].worldx/scale);
				planetY = (int)(y+gp.asteroidBelt[i].worldy/scale);
				g2.drawImage(gp.asteroidBelt[i].Entity1,planetX,planetY,asteroidSize,asteroidSize,null );
			}
		}
		
		
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial",Font.BOLD,30));
		g2.drawString("World Map", gp.Tilesize, gp.Tilesize);
	}
	public void drawMiniMap(Graphics2D g2) {
		if(gp.keyH.MinimapOn==true) {
			int width=350;
			int height=350;
			int x = gp.screenWidth-width-20;
			int y = gp.Tilesize*2;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.9f));
			g2.drawImage(worldMap,x,y,width,height,null);
			double scale = (double)(gp.Tilesize*gp.maxWorldCol)/width;
			int playerSize = (int)(gp.Tilesize/3);
			int playerX = (int)(x+gp.player.worldx/scale - playerSize/2);
			int playerY = (int)(y+gp.player.worldy/scale - playerSize/2);
			int planetX = 0;
			int planetY = 0;
			int planetSize = 0;
			g2.drawImage(gp.player.Entity1,playerX,playerY,playerSize,playerSize,null);
			if(gp.clientProjection!=null) {
				playerX = (int)(x+gp.clientProjection.worldx/scale - playerSize/2);
				playerY = (int)(y+gp.clientProjection.worldy/scale - playerSize/2);
				g2.drawImage(gp.player.Entity1,playerX,playerY,playerSize,playerSize,null);
			}
			if(gp.serverProjection!=null) {
				playerX = (int)(x+gp.serverProjection.worldx/scale - playerSize/2);
				playerY = (int)(y+gp.serverProjection.worldy/scale - playerSize/2);
				g2.drawImage(gp.player.Entity1,playerX,playerY,playerSize,playerSize,null);
			}
			for(int i=0; i<gp.SolarSystem.size(); i++) {
				planetSize = (int)(gp.Tilesize/gp.SolarSystem.get(i).PlanetSizeFactor);
				planetX = (int)(x+gp.SolarSystem.get(i).worldx/scale - planetSize/2);
				planetY = (int)(y+gp.SolarSystem.get(i).worldy/scale - planetSize/2);
				
				g2.drawImage(gp.SolarSystem.get(i).Entity1,planetX,planetY, planetSize, planetSize, null);
			}
			planetSize=(int)(gp.Tilesize-12);
			planetX = (int)(x+gp.sol.worldx/scale - planetSize/2);
			planetY = (int)(y+gp.sol.worldy/scale - planetSize/2);
			g2.drawImage(gp.sol.Entity1,planetX,planetY,planetSize,planetSize,null );
			int SpaceStationSize = (int)(gp.Tilesize/3);
			int SpaceStationX = (int)(x+gp.spacestation.worldx/scale - SpaceStationSize/2);
			int SpaceStationY = (int)(y+gp.spacestation.worldy/scale - SpaceStationSize/2);		
			g2.drawImage(gp.spacestation.Entity1,SpaceStationX,SpaceStationY,SpaceStationSize,SpaceStationSize,null );
			for(int i=0; i<gp.asteroidBelt.length;i++) {
				if(gp.asteroidBelt[i]!=null) {
				int asteroidSize = (int)(gp.Tilesize/5);
					planetX = (int)(x+gp.asteroidBelt[i].worldx/scale - asteroidSize/2);
					planetY = (int)(y+gp.asteroidBelt[i].worldy/scale - asteroidSize/2);
					g2.drawImage(gp.asteroidBelt[i].Entity1,planetX,planetY,asteroidSize,asteroidSize,null );
				}
			}
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		}
	}
	public void drawPlanetTokens(Graphics2D g2) {
		if(gp.keyH.ObjectiveOn==true && gp.gameState==gp.playState) {
			int x = gp.Tilesize/2;
			int y = gp.Tilesize*5+gp.Tilesize/2;
			g2.setFont(font4);
			g2.drawString("Objective: Study all planets of the Solar System and return safely to Earth with gathered data.", x, y);
			y+=gp.Tilesize/2;
			g2.drawString("Remember to save at least 25% of your fuel before you land on Earth.", x, y);
			y+=gp.Tilesize/2;
			g2.drawString("Planets Visited:" + gp.player.planettoken, x, y);
			y+=gp.Tilesize/2;
			g2.drawString("Press 'I' Key to hide the objective info.", x, y);
		}
		
	}
}
