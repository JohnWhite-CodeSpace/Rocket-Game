package Entity;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Game.GamePanel;

public class Venus extends Entity {
	Entity user;
	int counter=0;
	int VenusCount=0;
	int VenusIsDone=0;
	public Venus(GamePanel gp) {
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
	        	 
		int newX = (int) ((radius * Math.cos(angle)) + centerx);
        int newY = (int) ((radius * Math.sin(angle)) + centery);
       
		if(collisionOn==false) {
			angle+=0.0001;
			if(angle>=2*Math.PI) {
				angle=0;
			}
			switch(direction) {
				case "planet":worldx=newX;
				worldy=newY;break;
				
				}
			if(VenusIsDone==0&&gp.player.infoplanets==7) {
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
			if(counter>5) {
				counter=0;
				VenusCount++;
			}
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
		String text = "Collecting data..." + VenusCount;
		g2.drawString(text, x, y);
		if(VenusCount==100) {
			text = "This is Venus, the rocky planet with the thickest atmosphere of all rocky planets in the Solar System.";
			g2.drawString(text, x, y+gp.Tilesize/2);
			text = "Atmospheres composition: 96.5% Carbon dioxide, 3.5% Nitrogen, ";
			g2.drawString(text, x, y+gp.Tilesize);
			text = "also trace ammounts of sulfur dioxide, argon, carbon monoxide, neon and water vapour were found.";
			g2.drawString(text, x, y+3*gp.Tilesize/2);
			text = "Diameter: 12,104 km";
			g2.drawString(text, x, y+2*gp.Tilesize);
			text = "Mass: 4.867×10^24 kg ";
			g2.drawString(text, x, y+5*gp.Tilesize/2);
			text = "Average orbital speed: 35.02 km/s";
			g2.drawString(text, x, y+3*gp.Tilesize);
			text = "Known satelites: None";
			g2.drawString(text, x, y+7*gp.Tilesize/2);
			VenusIsDone=1;
		}
	}

}



