package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import Utility.UtilityTool;
import Game.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		LoadMap("/maps/map6.txt");
	}
	public void getTileImage() {
			setup(0,"empty",false);
			setup(1,"neutronstar",false);
			setup(2,"star1",false);
			setup(3,"reddwarf",false);
			setup(4,"smallstars",false);
			setup(5,"border2",true);
			setup(6,"SagitariusA1",false);
			setup(7,"SagitariusA2",false);
			setup(8,"SagitariusA3",false);
			setup(9,"SagitariusA4",false);
			setup(10,"smallstars2",false);
			setup(11,"star2",false);
			setup(12,"star3",false);
			setup(13,"reddwarf2",false);
			setup(14,"SagitariusAmiddle",false);
			setup(15,"SagitariusAright",false);
			setup(16,"SagitariusAleft",false);
			setup(17,"SagitariusAmiddleUP",false);
			setup(18,"SagitariusAmiddleDOWN", false);
	}
	
	public void setup(int index, String imagePath, boolean collision) {
		UtilityTool UTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imagePath + ".png"));
			tile[index].image = UTool.scaleImage(tile[index].image, gp.Tilesize, gp.Tilesize);
			tile[index].collision=collision;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void LoadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader bufread= new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol&&row < gp.maxWorldRow) {
				String line = bufread.readLine();
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if(col==gp.maxWorldCol) {
					col=0;
					row++;
				}
			}
			bufread.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2d) {
	    int playerCol = gp.player.worldx / gp.Tilesize;
	    int playerRow = gp.player.worldy / gp.Tilesize;

	    int startCol = Math.max(playerCol - (gp.screenWidth / (2 * gp.Tilesize)), 0);
	    int endCol = Math.min(playerCol + (gp.screenWidth / (2 * gp.Tilesize)) + 1, gp.maxWorldCol - 1);

	    int startRow = Math.max(playerRow - (gp.screenHeight / (2 * gp.Tilesize)), 0);
	    int endRow = Math.min(playerRow + (gp.screenHeight / (2 * gp.Tilesize)) + 1, gp.maxWorldRow - 1);

	    for (int row = startRow; row <= endRow; row++) {
	        int screenY = (row * gp.Tilesize) - gp.player.worldy + gp.player.screeny;
	        for (int col = startCol; col <= endCol; col++) {
	            int tileNum = mapTileNum[col][row];

	            int screenX = (col * gp.Tilesize) - gp.player.worldx + gp.player.screenx;

	            g2d.drawImage(tile[tileNum].image, screenX, screenY, null);
	        }
	    }
	}
}