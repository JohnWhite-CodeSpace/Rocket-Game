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
		if(worldMap==null) {
			try {
				worldMap = ImageIO.read(getClass().getResourceAsStream("/maps/Map2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		g2.drawImage(gp.player.Entity1,playerX,playerY,playerSize,playerSize,null);
		int planetX = (int)(x+gp.pluto.worldx/scale);
		int planetY = (int)(y+gp.pluto.worldy/scale);
		int planetSize = (int)(gp.Tilesize/3);
		g2.drawImage(gp.pluto.Entity1,planetX,planetY, planetSize, planetSize, null);
		planetSize = (int)(gp.Tilesize/2);
		planetX = (int)(x+gp.neptune.worldx/scale);
		planetY = (int)(y+gp.neptune.worldy/scale);
		g2.drawImage(gp.neptune.Entity1,planetX,planetY,planetSize,planetSize,null );
		planetX = (int)(x+gp.uranus.worldx/scale);
		planetY = (int)(y+gp.uranus.worldy/scale);
		g2.drawImage(gp.uranus.Entity1,planetX,planetY,planetSize,planetSize,null );
		planetX = (int)(x+gp.saturn.worldx/scale);
		planetY = (int)(y+gp.saturn.worldy/scale);
		g2.drawImage(gp.saturn.Entity1,planetX,planetY,planetSize,planetSize,null );
		planetX = (int)(x+gp.jupiter.worldx/scale);
		planetY = (int)(y+gp.jupiter.worldy/scale);
		g2.drawImage(gp.jupiter.Entity1,planetX,planetY,planetSize,planetSize,null );
		planetSize = (int)(gp.Tilesize/3);
		planetX = (int)(x+gp.mars.worldx/scale);
		planetY = (int)(y+gp.mars.worldy/scale);
		g2.drawImage(gp.mars.Entity1,planetX,planetY,planetSize,planetSize,null );
		planetX = (int)(x+gp.earth.worldx/scale);
		planetY = (int)(y+gp.earth.worldy/scale);
		g2.drawImage(gp.earth.Entity1,planetX,planetY,planetSize,planetSize,null );
		planetX = (int)(x+gp.venus.worldx/scale);
		planetY = (int)(y+gp.venus.worldy/scale);
		g2.drawImage(gp.venus.Entity1,planetX,planetY,planetSize,planetSize,null );
		planetX = (int)(x+gp.mercury.worldx/scale);
		planetY = (int)(y+gp.mercury.worldy/scale);
		g2.drawImage(gp.mercury.Entity1,planetX,planetY,planetSize,planetSize,null );
		planetSize = (int)(gp.Tilesize);
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
			int playerX = (int)(x+gp.player.worldx/scale);
			int playerY = (int)(y+gp.player.worldy/scale);
			int playerSize = (int)(gp.Tilesize/2);
			g2.drawImage(gp.player.Entity1,playerX,playerY,playerSize,playerSize,null);
			int planetX = (int)(x+gp.pluto.worldx/scale);
			int planetY = (int)(y+gp.pluto.worldy/scale);
			int planetSize = (int)(gp.Tilesize/4);
			g2.drawImage(gp.pluto.Entity1,planetX,planetY, planetSize, planetSize, null);
			planetSize=(int)(gp.Tilesize/3);
			planetX = (int)(x+gp.neptune.worldx/scale);
			planetY = (int)(y+gp.neptune.worldy/scale);
			g2.drawImage(gp.neptune.Entity1,planetX,planetY,planetSize,planetSize,null );
			planetX = (int)(x+gp.uranus.worldx/scale);
			planetY = (int)(y+gp.uranus.worldy/scale);
			g2.drawImage(gp.uranus.Entity1,planetX,planetY,planetSize,planetSize,null );
			planetX = (int)(x+gp.saturn.worldx/scale);
			planetY = (int)(y+gp.saturn.worldy/scale);
			g2.drawImage(gp.saturn.Entity1,planetX,planetY,planetSize,planetSize,null );
			
			planetX = (int)(x+gp.jupiter.worldx/scale);
			planetY = (int)(y+gp.jupiter.worldy/scale);
			g2.drawImage(gp.jupiter.Entity1,planetX,planetY,planetSize,planetSize,null );
			planetSize=(int)(gp.Tilesize/4);
			planetX = (int)(x+gp.mars.worldx/scale);
			planetY = (int)(y+gp.mars.worldy/scale);
			g2.drawImage(gp.mars.Entity1,planetX,planetY,planetSize,planetSize,null );
			planetX = (int)(x+gp.earth.worldx/scale);
			planetY = (int)(y+gp.earth.worldy/scale);
			g2.drawImage(gp.earth.Entity1,planetX,planetY,planetSize,planetSize,null );
			planetX = (int)(x+gp.venus.worldx/scale);
			planetY = (int)(y+gp.venus.worldy/scale);
			g2.drawImage(gp.venus.Entity1,planetX,planetY,planetSize,planetSize,null );
			planetX = (int)(x+gp.mercury.worldx/scale);
			planetY = (int)(y+gp.mercury.worldy/scale);
			g2.drawImage(gp.mercury.Entity1,planetX,planetY,planetSize,planetSize,null );
			planetSize=(int)(gp.Tilesize/2);
			planetX = (int)(x+gp.sol.worldx/scale);
			planetY = (int)(y+gp.sol.worldy/scale);
			g2.drawImage(gp.sol.Entity1,planetX,planetY,planetSize,planetSize,null );
			int SpaceStationX = (int)(x+gp.spacestation.worldx/scale);
			int SpaceStationY = (int)(y+gp.spacestation.worldy/scale);
			int SpaceStationSize = (int)(gp.Tilesize/3);
			g2.drawImage(gp.spacestation.Entity1,SpaceStationX,SpaceStationY,SpaceStationSize,SpaceStationSize,null );
			for(int i=0; i<gp.asteroidBelt.length;i++) {
				if(gp.asteroidBelt[i]!=null) {
				int asteroidSize = (int)(gp.Tilesize/4);
					planetX = (int)(x+gp.asteroidBelt[i].worldx/scale);
					planetY = (int)(y+gp.asteroidBelt[i].worldy/scale);
					g2.drawImage(gp.asteroidBelt[i].Entity1,planetX,planetY,asteroidSize,asteroidSize,null );
				}
			}
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		}
	}
	public void drawPlanetTokens(Graphics2D g2) {
		if(gp.keyH.ObjectiveOn==true) {
			int x = gp.Tilesize/2;
			int y = gp.Tilesize*5+gp.Tilesize/2;
			g2.setFont(font4);
			g2.drawString("Objective: Study all planets in the solar system and return safely to Earth with gathered data.", x, y);
			y+=gp.Tilesize/2;
			g2.drawString("Planets Visited:" + gp.player.planettoken, x, y);
			y+=gp.Tilesize/2;
			g2.drawString("Press 'TAB' Key to hide the objective info.", x, y);
		}
		
	}
}
