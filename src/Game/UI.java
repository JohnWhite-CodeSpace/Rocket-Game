package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import Object.OBJ_PlayerLife;
import Object.OBJ_PlayerFireRecharge;
import Object.OBJ_PlayerFuel;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class UI {
	GamePanel gp;
	public Graphics2D g2;
	public boolean messageOn = false;
	public boolean lifeOn = false;
	public String Message="";
	public int lifecounter = 0;
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String CurrentDialogue = "";
	Font font, font2, font3, font4;
	public int commandNum = 0;
	public int info=999;
	public int titleScreenState =0;
	public int LoadProgress;
	public String LoadMessage;
	public double playTime =0;
	public BufferedImage lifefull,lifeempty;
	public BufferedImage recharge1, recharge2;
	public BufferedImage Fuel100,Fuel75,Fuel50, Fuel25, Fuel0;
	Timer timer;
	OBJ_PlayerLife lifebar;
	BufferedImage image;
	public UI(GamePanel gp) {
		this.gp = gp;
		lifebar = new OBJ_PlayerLife(gp);
		font = new Font("Arial",Font.BOLD,50);
		font2 = new Font("Arial", Font.BOLD,90);
		font3 = new Font("Arial",Font.BOLD,20);
		font4 = new Font("Arial",Font.BOLD,15);
		lifeOn=true;
		OBJ_PlayerLife life1 = new OBJ_PlayerLife(gp);
		OBJ_PlayerFireRecharge rechargebar = new OBJ_PlayerFireRecharge(gp);
		OBJ_PlayerFuel PFuel = new OBJ_PlayerFuel(gp);
		lifefull = life1.bar1;
		lifeempty = life1.bar2;
		recharge1 = rechargebar.bar1;
		recharge2 = rechargebar.bar2;
		Fuel100 = PFuel.fuel100;
		Fuel75 = PFuel.fuel75;
		Fuel50 = PFuel.fuel50;
		Fuel25 = PFuel.fuel25;
		Fuel0 = PFuel.fuel0;
	}
	public void showMessage(String text) {
		Message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
	    this.g2 = g2;
		g2.setFont(font);
		g2.setColor(Color.white);
		switch (gp.gameState) {
		case 0:
			try {
				DrawTitleScreen("NEW GAME");
				LoadSaveConsole(g2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		case 1:
			GameTimer(g2);
			try {
				DrawLife();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DrawFireRecharge();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DrawFuelBar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DrawDialogScreen(gp.player.infoplanets);
			break;
		case 2:
			PauseScreen();
			try {
				DrawLife();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DrawFireRecharge();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DrawFuelBar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				DrawTitleScreen("RESUME GAME");
				LoadSaveConsole(g2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			try {
				GameOverScreen();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			break;
		case 6:
			DrawWinScreen();
			break;
		case 8:
			try {
				DrawLoadScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
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
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/TitleScreen.png"));
		g2.drawImage(image, 0,0,gp.screenWidth,gp.screenHeight,null);
		switch(titleScreenState) {
		case 0:
			g2.setFont(font2);
			String text = "Rocket Adventure (BETA)";
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
			text = "OPTIONS";
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
			text = "EXIT";
			x = getXForCenteredText(text);
			y+=gp.Tilesize*2;
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum==3) {
				g2.setColor(Color.green);
				g2.drawString(">", x-gp.Tilesize, y);
			}
			break;
		case 1:
			g2.setColor(Color.black);
			g2.setFont(font2);
			
			text = "Select your rocket class:";
			x = getXForCenteredText(text);
			y =gp.Tilesize*3;
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
			image2 = ImageIO.read(getClass().getResourceAsStream("/player/chonker.png"));
			x = gp.screenWidth/2 - gp.Tilesize;
			y=gp.screenHeight/6 +3*gp.Tilesize+16;
			g2.drawImage(image2, x, y, gp.Tilesize*2, gp.Tilesize*2,null);
			
			BufferedImage image3 = ImageIO.read(getClass().getResourceAsStream("/player/speedy1.png"));
			x = gp.screenWidth/2 - gp.Tilesize;
			y=gp.screenHeight/6 +7*gp.Tilesize+16;
			g2.drawImage(image3, x, y,gp.Tilesize*2,gp.Tilesize*2,null);
			break;
			
		case 2:
			g2.setColor(Color.black);
			g2.setFont(font2);
			
			text = "Options:";
			x = getXForCenteredText(text);
			y =gp.Tilesize*3;
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			g2.setFont(font);
			
			
			
			text = "Set FullScreen";
			x = getXForCenteredText(text);
			y +=gp.Tilesize*3;
			g2.setColor(Color.black);
			g2.drawString(text, x-215, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x-220, y);
			g2.setStroke(new BasicStroke(8));
			g2.setColor(Color.black);
			g2.drawRect(x+200, y-gp.Tilesize/2-10, 34, 34);
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(x+201, y-gp.Tilesize/2-9, 32, 32);
			if(gp.fullscreenOn==true) {
				g2.setColor(Color.red);
				g2.fillRect(x+201, y-gp.Tilesize/2-9, 32, 32);
				
			}
			if(commandNum==0) {
				g2.setColor(Color.green);
				g2.drawString(">", x-gp.Tilesize-250, y);
				if(gp.keyH.enterPressed==true) {
					if(gp.fullscreenOn==false) {
						gp.fullscreenOn=true;
					}else if(gp.fullscreenOn==true) {
						gp.fullscreenOn=false;
					}
					gp.keyH.enterPressed=false;
				}
			}
			text = "Music Volume";
			x = getXForCenteredText(text);
			y +=gp.Tilesize*2;
			g2.setColor(Color.black);
			g2.drawString(text, x-215, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x-220, y);
			g2.setColor(Color.black);
			g2.drawRect(x+200, y-gp.Tilesize/2, 244, 26);
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(x+202, y-gp.Tilesize/2+2,240,23);
			int volumeWidth = 40*gp.songs.volumeScale;
			g2.setColor(Color.red);
			g2.fillRect(x+202, y-gp.Tilesize/2+2, volumeWidth, 23);
			if(commandNum==1) {
				g2.setColor(Color.green);
				g2.drawString(">", x-gp.Tilesize-250, y);
			}
			text = "SE Volume";
			x = getXForCenteredText(text);
			y +=gp.Tilesize*2;
			g2.setColor(Color.black);
			g2.drawString(text, x-215, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x-220, y);
			g2.setColor(Color.black);
			g2.drawRect(x+160, y-gp.Tilesize/2, 244, 26);
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(x+162, y-gp.Tilesize/2+2,240,23);
			volumeWidth = 40*gp.sound.volumeScale;
			g2.setColor(Color.red);
			g2.fillRect(x+162, y-gp.Tilesize/2+2, volumeWidth, 23);
			if(commandNum==2) {
				g2.setColor(Color.green);
				g2.drawString(">", x-gp.Tilesize-250, y);
			}
			text = "Controls";
			x = getXForCenteredText(text);
			y +=gp.Tilesize*2;
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			if(commandNum==3) {
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
			
			if(commandNum==4) {
				g2.setColor(Color.green);
				g2.drawString(">", x-gp.Tilesize, y);
			}
			break;
			
		case 3:
			g2.setColor(Color.black);
			g2.setFont(font2);
			
			text = "Game Controls:";
			x = getXForCenteredText(text);
			y =gp.Tilesize*2;
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			g2.setFont(font);
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/KEYBOARD.png"));
			g2.drawImage(image2, gp.screenWidth/4-200,gp.screenHeight/11,gp.screenWidth/2+600,gp.screenHeight/2+150,null);
			text = "Back";
			x = getXForCenteredText(text);
			y +=gp.Tilesize*13+24;
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			if(commandNum==0) {
				g2.setColor(Color.green);
				g2.drawString(">", x-gp.Tilesize, y);
			}
			break;
		
		case 4:
			g2.setFont(font2);
			text = "GAME PAUSED";
			x = getXForCenteredText(text);
			y = gp.screenHeight/6;
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			BufferedImage image4 = ImageIO.read(getClass().getResourceAsStream("/player/chonker.png"));
			x = gp.screenWidth/2 - gp.Tilesize;
			y=gp.screenHeight/6 +35;
			g2.drawImage(image4, x, y, gp.Tilesize*2, gp.Tilesize*2,null);
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
			text = "SAVE GAME";
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
			text = "LOAD GAME";
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
			text = "OPTIONS";
			x = getXForCenteredText(text);
			y+=gp.Tilesize*2;
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum==3) {
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
			if(commandNum==4) {
				g2.setColor(Color.green);
				g2.drawString(">", x-gp.Tilesize, y);
			}
			break;
		}
}
	
	public synchronized void GameTimer(Graphics2D g2) {
	        g2.setFont(font);
	        g2.setColor(Color.white);
	        playTime += (double) 1 / 60;
	        String text = String.format("%.2f", playTime);
	        g2.drawString("Time: " + text, gp.screenWidth - gp.Tilesize * 7, 50);

	       
	            if (messageOn) {
	                g2.setFont(font3);
	                g2.drawString(Message, gp.Tilesize, 4 * gp.Tilesize);
	                messageCounter++;
	                if (messageCounter > 180) {
	                    messageCounter = 0;
	                    messageOn = false;
	                }
	            }
	        
	}
	public synchronized void LoadSaveConsole(Graphics2D g2) {
	        g2.setColor(Color.white);
	            if (messageOn) {
	                g2.setFont(font3);
	                g2.drawString(Message, gp.Tilesize, 2*gp.Tilesize);
	                messageCounter++;
	                if (messageCounter > 180) {
	                    messageCounter = 0;
	                    messageOn = false;
	                }
	            }
	}
	public void DrawLife() throws IOException {
		if(lifeOn==true) {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Heart.png"));
			g2.drawImage(image, gp.Tilesize*4,gp.Tilesize,gp.Tilesize+gp.Tilesize/2,gp.Tilesize+gp.Tilesize/2,null);
			g2.setFont(font);
			g2.setColor(Color.white);
			g2.drawString("Health", gp.Tilesize-10, 2*gp.Tilesize);
			int x = gp.Tilesize/2;
			int y = 2*gp.Tilesize;
			int i=0;
			while(i<gp.player.maxLife) {
				g2.drawImage(lifeempty, x, y,gp.Tilesize,gp.Tilesize, null);
				i++;
				x+=gp.Tilesize/3;
			}
			x = gp.Tilesize/2;
			y = 2*gp.Tilesize;
			i=0;
			while(i<gp.player.life) {
				g2.drawImage(lifefull, x, y,gp.Tilesize,gp.Tilesize, null);
				i++;
				x+=gp.Tilesize/3;
			}
		}
	}
	public void GameOverScreen() throws IOException {
				g2.setColor(new Color(0,0,0,100));
				g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
				
				
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
	public void DrawFireRecharge() throws IOException {
		g2.setFont(font);
		g2.setColor(Color.white);
		g2.drawString("Fire recharge", gp.Tilesize-10, gp.Tilesize*20+20);
		image = ImageIO.read(getClass().getResourceAsStream("/objects/"+gp.player.Weapon+".png"));
		g2.drawImage(image, gp.Tilesize*5,gp.Tilesize*21,gp.Tilesize+gp.Tilesize/2,gp.Tilesize+gp.Tilesize/2,null);
		int x = gp.Tilesize/2;
		int y = gp.Tilesize*21;
		int i=0;
		while(i<gp.player.maxrecharge) {
			g2.drawImage(recharge2, x, y,gp.Tilesize,gp.Tilesize, null);
			i+=10;
			x+=gp.Tilesize/3;
		}
		x = gp.Tilesize/2;
		y = gp.Tilesize*21;
		i=0;
		while(i<gp.player.recharge) {
			g2.drawImage(recharge1, x, y,gp.Tilesize,gp.Tilesize, null);
			i+=10;
			x+=gp.Tilesize/3;
		}
	}
	public void DrawFuelBar() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/Canister.png"));
		g2.drawImage(image, gp.Tilesize*28,gp.Tilesize*21+10,gp.Tilesize*2,gp.Tilesize*2,null);
		g2.setFont(font);
		g2.setColor(Color.white);
		g2.drawString("Fuel", gp.Tilesize*28, gp.Tilesize*20+20);
		g2.setFont(font);
		int x = gp.Tilesize*30 + gp.Tilesize/2;
		int y = gp.Tilesize*22 + gp.Tilesize/2;
		int i=0;
		while(i<gp.player.maxfuel/20) {
				g2.drawImage(Fuel0, x, y,gp.Tilesize,gp.Tilesize, null);
			i++;
			y-=gp.Tilesize/3;
		}
		x = gp.Tilesize*30 + gp.Tilesize/2;
		y = gp.Tilesize*22 + gp.Tilesize/2;
		i=0;
		while(i<gp.player.fuel/20) {
			if(i>=0&&i<3) {
				g2.drawImage(Fuel25, x, y,gp.Tilesize,gp.Tilesize, null);
			}
			else if(i>=3&&i<5) {
				g2.drawImage(Fuel50, x, y,gp.Tilesize,gp.Tilesize, null);
			}
			else if(i>=5 && i<7) {
				g2.drawImage(Fuel75, x, y,gp.Tilesize,gp.Tilesize, null);
				
			}
			else if(i>=7 && i<=10) {
				g2.drawImage(Fuel100, x, y,gp.Tilesize,gp.Tilesize, null);
			}
			else if(i>10) {
				g2.drawImage(Fuel100, x, y,gp.Tilesize,gp.Tilesize, null);
			}
			i++;
			y-=gp.Tilesize/3;
		}
	}
	public void DrawWinScreen() {
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		
		g2.setFont(font2);
		String text = "YOU WON";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/6;
		
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		g2.setFont(font);
		text = "Author:";
		x = getXForCenteredText(text);
		y+=gp.Tilesize*2;
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x,y);
		text = "Jan Bialy";
		x = getXForCenteredText(text);
		y+=gp.Tilesize*2;
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x,y);
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
	public void DrawDialogScreen(int infoplanets) {
		if(infoplanets!=999) {
			gp.planets[infoplanets].getPlanetInfo(g2);
		}
	}
	public void DrawLoadScreen() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/TitleScreen.png"));
		g2.drawImage(image, 0,0,gp.screenWidth,gp.screenHeight,null);
		g2.setFont(font2);
		String text = "Loading";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/6;
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		x = getXForCenteredText(text);
		y+=gp.Tilesize*8;
		g2.setFont(font);
		text = LoadMessage;
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
	}
}

	