package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Game.GamePanel;

public class Planets extends Entity{
		Entity user;
		int counter=0;
		public int Index;
		int ResearchIsDone=0;
		int ResearchCount = 0;
		public int PlanetSizeFactor = 1;
		public Planets(GamePanel gp) {
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
					worldy=newY; break;
					}
				if(ResearchIsDone==0&&gp.player.infoplanets==Index) {
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
					ResearchCount++;
				}
			}
		}
		public void getPlanetInfo(Graphics2D g2, int Index) {
			g2.setColor(new Color(0,0,0,140));
			g2.setFont(new Font("Arial",Font.BOLD,15));
			int x = gp.screenWidth/4;
			int y = gp.Tilesize;
			int width = gp.screenWidth/3;
			int height = gp.screenHeight/6;
			g2.fillRect(x, y, width, height);
			g2.setColor(Color.white);
			String text = "Collecting data..." + ResearchCount;
			g2.drawString(text, x, y);
			String[] temparray ;
			if(ResearchCount==100) {
			switch(Index) {
			case 0:
				temparray = new String[] {this.name, "0.330","4890","5429","3.7",
						"4.3", "1407.6","4222.6","57.9","46.0","69.8","167",
						"0","0","No","Yes"
				};
				break;
			case 1:
				temparray = new String[] {this.name, "4.87","12104","5243","8.9",
						"10.4", "-5832.5","2802.0","108.2","107.5","108.9","464",
						"92","0","No","No"
				};
				break;
			case 2:
				temparray = new String[] {this.name, "5.97","12756","5514","9.8",
						"11.2", "23.9","24.0","149.6","147.1","152.1","15",
						"1","1","No","Yes"
				};
				break;
			case 3:
				temparray = new String[] {this.name, "0.642","6792","3934","3.7",
						"5.0", "24.6","24.7","228.0","206.7","249.3","-65",
						"0.01","2","No","No"
				};
				break;
			case 4:
				temparray = new String[] {this.name, "1898","142984","1326","23.1",
						"59.5", "9.9","9.9","778.5","740.6","816.4","-110",
						"Unknown","95","Yes","Yes"
				};
				break;
			case 5:
				temparray = new String[] {this.name, "568","120536","687","9.0",
						"35.5", "10.7","10.7","1432.0","1357.6","1506.5","-140",
						"Unknown","146","Yes","Yes"
				};
				break;
			case 6:
				temparray = new String[] {this.name, "86.8","51118","1270","8.7",
						"21.3", "-17.2","17.2","2867.0","2732.7","3001.4","-195",
						"Unknown","28","Yes","Yes"
				};
				break;
			case 7:
				temparray = new String[] {this.name, "102","49528","1638","11.0",
						"23.5", "16.1","16.1","4515.0","4471.1","4558.9","-200",
						"Unknown","16","Yes","Yes"
				};
				break;
			case 8:
				temparray = new String[] {this.name, "0.0130","2376","1850","0.7",
						"1.3", "-153.3","153.3","5906.4","4436.8","7375.9","-225",
						"0.00001","5","No","Unknown"
				};
				break;
			default:
				temparray = new String[] {this.name, "xxx","xxx","xxx","xxx",
						"xxx", "xxx","xxx","xxx","xxx","xxx","xxx",
						"xxx","xxx","?","?"
				};
				break;
			}
			ResearchIsDone= 1;
			DrawData(g2,temparray);
		}
			
	}
		public void DrawData(Graphics2D g2, String[] temparray) {
			g2.setFont(new Font("Arial",Font.BOLD,10));
			int x = gp.screenWidth/4;
			int y = gp.Tilesize+gp.Tilesize/8;
			String text="";
			text = "Planet: " + temparray[0];
			g2.drawString(text, x, y+(int) 0.5*gp.Tilesize/4);
			text = "Mass[10E24 kg]: "+ temparray[1];
			g2.drawString(text, x, y+1*gp.Tilesize/5);
			text = "Diameter[km]:" + temparray[2];
			g2.drawString(text, x, y+2*gp.Tilesize/5);
			text = "Density[kq/m^3]: "+temparray[3];
			g2.drawString(text, x, y+3*gp.Tilesize/5);
			text = "Gravity[m/s^2]: "+ temparray[4];
			g2.drawString(text, x, y+4*gp.Tilesize/5);
			text = "Escape Velocity[km/s]: "+ temparray[5];
			g2.drawString(text, x, y+5*gp.Tilesize/5);
			text = "Rotation Period[hours]: "+temparray[6];
			g2.drawString(text, x, y+6*gp.Tilesize/5);
			text = "Length of Day[hours]: "+temparray[7];
			g2.drawString(text, x, y+7*gp.Tilesize/5);
			text = "Distance from Sun[10e6 km]: "+ temparray[8];
			g2.drawString(text, x, y+8*gp.Tilesize/5);
			text = "Perihelion[10E6 km]: "+ temparray[9];
			g2.drawString(text, x, y+9*gp.Tilesize/5);
			text = "Aphelion[10E6 km]: " + temparray[10];
			g2.drawString(text, x, y+10*gp.Tilesize/5);
			text = "Mean Temperature [C]: " + temparray[11];
			g2.drawString(text, x, y+11*gp.Tilesize/5);
			text = "Surface Pressure[bars]: " + temparray[12];
			g2.drawString(text, x, y+12*gp.Tilesize/5);
			text = "Number of Moons: " + temparray[13];
			g2.drawString(text, x, y+13*gp.Tilesize/5);
			text = "Ring System?: " + temparray[14];
			g2.drawString(text, x, y+14*gp.Tilesize/5);
			text = "Global Magnetic Field?: " + temparray[15];
			g2.drawString(text, x, y+15*gp.Tilesize/5);
		}
}
