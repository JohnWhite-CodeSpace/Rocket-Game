package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Game.GamePanel;

public class Saturn extends Entity{
	Entity user;
	int counter=0;
	int SaturnCount=0;
	int SaturnIsDone=0;
	public Saturn(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}
	public void set(int Worldx, int Worldy, String direction, boolean alive, Entity user) {
		this.worldx = Worldx;
		this.worldy = Worldy;
		this.direction = direction;
		this.IsAlive=alive;
		this.life = this.maxLife;
		this.user = user;
	}
	public void update() {
		collisionOn=false;
   	 
		int newX = (int) ((radius * Math.cos(angle))  + centerx);
        int newY = (int) ((radius * Math.sin(angle))  + centery);
       
		if(collisionOn==false) {
			angle+=0.0001;
			if(angle>=2*Math.PI) {
				angle=0;
			}
			switch(direction) {
				case "planet":worldx=newX;
				worldy=newY;break;
				
				}
			if(SaturnIsDone==0&&gp.player.infoplanets==3) {
				counter++;
			}
				spriteCounter++;
			if(spriteCounter>30) {
				if(spriteNum==1) {
					spriteNum=2;
				}
				else if(spriteNum==2) {
					spriteNum=3;
				}
				else if(spriteNum==3) {
					spriteNum=4;
				}
				else if(spriteNum==4) {
					spriteNum=1;
				}
				spriteCounter=0;
			}
			
		}
		if(counter>5) {
			counter=0;
			SaturnCount++;
		}
	}
	public void getPlanetInfo(Graphics2D g2) {
		g2.setColor(new Color(0,0,0,140));
		g2.setFont(new Font("Arial",Font.BOLD,15));
		int x = gp.screenWidth/4;
		int y = gp.Tilesize;
		int width = gp.screenWidth/2;
		int height = gp.screenHeight/6;
		g2.fillRect(x, y, width, height);
		g2.setColor(Color.white);
		String text = "Collecting data..." + SaturnCount;
		g2.drawString(text, x, y);
		if(SaturnCount==100) {
			text = "This is Saturn, the second largest planet in our Solar System";
			g2.drawString(text, x, y+gp.Tilesize/2);
			text = "Atmosphere composition: 96.3% Hydrogen, 3.25% Helium, ";
			g2.drawString(text, x, y+gp.Tilesize);
			text = "also trace ammounts of ammonia, acetylene, ethane, propane and phosphine were found.";
			g2.drawString(text, x, y+3*gp.Tilesize/2);
			text = "Diameter: 25,000 km";
			g2.drawString(text, x, y+2*gp.Tilesize);
			text = "Mass: 5.6834×10^26 kg ";
			g2.drawString(text, x, y+5*gp.Tilesize/2);
			text = "Average orbital speed: 9.68 km/s";
			g2.drawString(text, x, y+3*gp.Tilesize);
			text = "Known satelites: 146";
			g2.drawString(text, x, y+7*gp.Tilesize/2);
			SaturnIsDone=1;
		}

	}

}
