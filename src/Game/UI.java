package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

import Object.OBJ_PlayerLife;

import javax.imageio.ImageIO;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	public boolean messageOn = false;
	public boolean lifeOn = false;
	public String Message="";
	public int lifecounter = 0;
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String CurrentDialogue = "";
	Font font, font2, font3;
	public int commandNum = 0;
	public int titleScreenState =0;
	double playTime =0;
	OBJ_PlayerLife lifebar;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		lifebar = new OBJ_PlayerLife(gp);
		font = new Font("Arial",Font.BOLD,50);
		font2 = new Font("Arial", Font.BOLD,90);
		font3 = new Font("Arial",Font.BOLD,20);
		lifeOn=true;
	}
	public void showMessage(String text) {
		
		Message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(font);
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		if(gp.gameState==gp.titleState) {
			try {
				DrawTitleScreen("NEW GAME");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(gp.gameState == gp.playState) {
			GameTimer();
			DrawLife();
			
			
		}
		
		if(gp.gameState == gp.pauseState) {
			PauseScreen();
		}
		if(gp.gameState == gp.exitpauseState) {
			try {
				DrawTitleScreen("RESUME GAME");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if(gp.gameState==gp.GameOverState) {
			try {
				GameOverScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public void PauseScreen() {
		String text = "GAME PAUSED";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/3;
		g2.drawString(text, x, y);
	}
	public int getXForCenteredText(String text) {
		int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - lenght/2;
		return x;
	}
	public void DrawTitleScreen(String State) throws IOException {
		if(titleScreenState==0) {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/TitleScreen.png"));
		g2.drawImage(image, 0,0,gp.screenWidth,gp.screenHeight-100,null);
		
		
		g2.setFont(font2);
		String text = "Rocket Adventure";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/6;
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		BufferedImage image2 = ImageIO.read(getClass().getResourceAsStream("/player/chonker.png"));
		x = gp.screenWidth/2 - gp.Tilesize;
		y=gp.screenHeight/6 +35;
		g2.drawImage(image2, x, y, gp.Tilesize*2, gp.Tilesize*2,null);
		
		
		//MENU
		
		g2.setFont(font);
		text = State;;
		x = getXForCenteredText(text);
		y+=gp.Tilesize*4;
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if(commandNum==0) {
			g2.setColor(Color.green);
			g2.drawString(">", x-gp.Tilesize, y);
		}
		text = "LOAD GAME";
		x = getXForCenteredText(text);
		y+=gp.Tilesize*2;
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if(commandNum==1) {
			g2.setColor(Color.green);
			g2.drawString(">", x-gp.Tilesize, y);
		}
		text = "EXIT";
		x = getXForCenteredText(text);
		y+=gp.Tilesize*2;
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if(commandNum==2) {
			g2.setColor(Color.green);
			g2.drawString(">", x-gp.Tilesize, y);
			
		}
		}
		else if(titleScreenState==1) {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/TitleScreen.png"));
			g2.drawImage(image, 0,0,gp.screenWidth,gp.screenHeight-100,null);
			
			
			g2.setColor(Color.black);
			g2.setFont(font2);
			
			String text = "Select your rocket class:";
			int x = getXForCenteredText(text);
			int y =gp.Tilesize*3;
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			g2.setFont(font);
			text = "Destroyer class (Chonker)";
			x = getXForCenteredText(text);
			y +=gp.Tilesize*4;
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			if(commandNum==0) {
				g2.setColor(Color.green);
				g2.drawString(">", x-gp.Tilesize, y);
			}
			
			text = "Scout class (Speedy)";
			x = getXForCenteredText(text);
			y +=gp.Tilesize*4;
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			if(commandNum==1) {
				g2.setColor(Color.green);
				g2.drawString(">", x-gp.Tilesize, y);
			}
			text = "Back";
			x = getXForCenteredText(text);
			y +=gp.Tilesize*3+24;
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			if(commandNum==2) {
				g2.setColor(Color.green);
				g2.drawString(">", x-gp.Tilesize, y);
			}
			BufferedImage image2 = ImageIO.read(getClass().getResourceAsStream("/player/chonker.png"));
			x = gp.screenWidth/2 - gp.Tilesize;
			y=gp.screenHeight/6 +3*gp.Tilesize+16;
			g2.drawImage(image2, x, y, gp.Tilesize*2, gp.Tilesize*2,null);
			
			BufferedImage image3 = ImageIO.read(getClass().getResourceAsStream("/player/speedy1.png"));
			x = gp.screenWidth/2 - gp.Tilesize;
			y=gp.screenHeight/6 +7*gp.Tilesize+16;
			g2.drawImage(image3, x, y,gp.Tilesize*2,gp.Tilesize*2,null);
		}

}
	public synchronized void GameTimer() {
		g2.setFont(font);
		g2.setColor(Color.white);
		playTime +=(double)1/100;
		String text = String.format("%.2f", playTime);
		g2.drawString("Time: " + text , gp.screenWidth - gp.Tilesize*7, 50);
		if(messageOn == true) {
			g2.setFont(font3);
			g2.drawString(Message, gp.Tilesize,4*gp.Tilesize);
			messageCounter++;
			if(messageCounter > 180) {
				messageCounter = 0;
				messageOn = false;
			}
		}
	}
	public void DrawLife() {
		if(lifeOn==true) {
			g2.setFont(font);
			g2.setColor(Color.white);
			g2.drawString("Health", gp.Tilesize, gp.Tilesize);
			BufferedImage LifeImage = lifebar.DrawLifeBar(gp.player.AsteroidCollision);
			g2.drawImage(LifeImage,gp.Tilesize-15,gp.Tilesize,gp.Tilesize*4,gp.Tilesize*2,null);
			if(gp.player.AsteroidCollision>12) {
				gp.gameState=gp.GameOverState;
			}
		}
		}
	public void GameOverScreen() throws IOException {
				BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/TitleScreen.png"));
				g2.drawImage(image, 0,0,gp.screenWidth,gp.screenHeight-100,null);
				
				
				g2.setFont(font2);
				String text = "GAME OVER";
				int x = getXForCenteredText(text);
				int y = gp.screenHeight/6;
				
				g2.setColor(Color.black);
				g2.drawString(text, x+5, y+5);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
				g2.setFont(font);
				text = "EXIT TO MENU";
				x = getXForCenteredText(text);
				y+=gp.Tilesize*5;
				g2.setColor(Color.black);
				g2.drawString(text, x+5, y+5);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
				if(commandNum==0) {
					g2.setColor(Color.green);
					g2.drawString(">", x-gp.Tilesize, y);
				}

	}
}

	