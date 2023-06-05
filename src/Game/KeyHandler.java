package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed;
	public boolean downPressed;
	public boolean rightPressed;
	public boolean leftPressed;
	public boolean shiftPressed;
	public boolean speedPressed;
	public boolean shotsFired;
	public boolean shotsFired2;
	public boolean enterPressed;
	public int changeFireMode=0;
	public boolean IsFirstGame=true;
	public boolean MapOn=false;
	GamePanel gp;
	//DEBUG
	
	boolean DebugMode = false;
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		int code2 = e.getKeyCode();
		if(gp.gameState==gp.titleState) {
			if(gp.ui.titleScreenState==0) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum-- ;
					gp.playSE(4);
					if(gp.ui.commandNum<0) {
						gp.ui.commandNum=3 ;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.commandNum++ ;
					gp.playSE(4);
					if(gp.ui.commandNum>3) {
						gp.ui.commandNum=0 ;
					}
				}
				if(code==KeyEvent.VK_ENTER) {
					gp.playSE(3);
					if(gp.ui.commandNum==0) {
						gp.ui.titleScreenState=1;
					}
					if(gp.ui.commandNum==1) {
						
					}
					if(gp.ui.commandNum==2) {
						gp.ui.titleScreenState=2;
					}
					if(gp.ui.commandNum==3) {
						System.exit(0);
					}
				}
			}
			else if(gp.ui.titleScreenState==1) {
				if(code == KeyEvent.VK_W) {
					gp.playSE(4);
					gp.ui.commandNum-- ;
					if(gp.ui.commandNum<0) {
						gp.ui.commandNum=2 ;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.playSE(4);
					gp.ui.commandNum++ ;
					if(gp.ui.commandNum>2) {
						gp.ui.commandNum=0 ;
					}
				}
				if(code==KeyEvent.VK_ENTER) {
					gp.playSE(3);
					if(gp.ui.commandNum==0) {
						if(IsFirstGame==true) {
							gp.gameState=gp.playState;
						}
						if(IsFirstGame==false) {
							gp.restart();
							gp.gameState=gp.playState;
						}
						gp.player.ChooseRocket(1);
					}
					if(gp.ui.commandNum==1) {
						if(IsFirstGame==true) {
							gp.gameState=gp.playState;
						}
						if(IsFirstGame==false) {
							gp.restart();
							gp.gameState=gp.playState;
						}
						gp.player.ChooseRocket(2);
					}
					if(gp.ui.commandNum==2) {
						gp.ui.titleScreenState=0;
					}
				}
				
			}
		}
		 if(gp.ui.titleScreenState==2) {
				if(code == KeyEvent.VK_W) {
					gp.playSE(4);
					gp.ui.commandNum-- ;
					if(gp.ui.commandNum<0) {
						gp.ui.commandNum=4 ;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.playSE(4);
					gp.ui.commandNum++ ;
					if(gp.ui.commandNum>4) {
						gp.ui.commandNum=0 ;
					}
				}
				if(code == KeyEvent.VK_A) {
					if(gp.ui.commandNum==1 && gp.songs.volumeScale>0) {
						gp.songs.volumeScale--;
						gp.songs.checkVolume();
						gp.playSE(4);
					}
					if(gp.ui.commandNum==2 && gp.sound.volumeScale>0) {
						gp.sound.volumeScale--;
						gp.playSE(4);
					}
				}
				if(code == KeyEvent.VK_D) {
					if(gp.ui.commandNum==1 && gp.songs.volumeScale<6) {
						gp.songs.volumeScale++;
						gp.songs.checkVolume();
						gp.playSE(4);
					}
					if(gp.ui.commandNum==2 && gp.sound.volumeScale<6) {
						gp.sound.volumeScale++;
						gp.playSE(4);
					}
				}
				if(code==KeyEvent.VK_ENTER) {
					gp.playSE(3);
					if(gp.ui.commandNum==0) {
						enterPressed=true;
						gp.ui.titleScreenState=2;
					}
					if(gp.ui.commandNum==1) {
						
					}
					if(gp.ui.commandNum==2) {
											
					}
					if(gp.ui.commandNum==3) {
						gp.ui.titleScreenState=3;
					}
					if(gp.ui.commandNum==4) {
						gp.ui.titleScreenState=0;
					}
				}
		 }
				if(gp.ui.titleScreenState==3) {
					if(code == KeyEvent.VK_W) {
						gp.playSE(4);
						gp.ui.commandNum-- ;
						if(gp.ui.commandNum<0) {
							gp.ui.commandNum=4 ;
						}
					}
					if(code == KeyEvent.VK_S) {
						gp.playSE(4);
						gp.ui.commandNum++ ;
						if(gp.ui.commandNum>4) {
							gp.ui.commandNum=0 ;
						}
					}
					if(code==KeyEvent.VK_ENTER) {
						gp.playSE(3);
						if(gp.ui.commandNum==0) {
							enterPressed=true;
							gp.ui.titleScreenState=2;
						}
					}
				}
				
				
		if(gp.gameState==gp.exitpauseState) {
			if(gp.ui.titleScreenState==0) {
				if(code == KeyEvent.VK_W) {
					gp.playSE(4);
					gp.ui.commandNum-- ;
					if(gp.ui.commandNum<0) {
						gp.ui.commandNum=3 ;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.playSE(4);
					gp.ui.commandNum++ ;
					if(gp.ui.commandNum>3) {
						gp.ui.commandNum=0 ;
					}
				}
				if(code==KeyEvent.VK_ENTER) {
					gp.playSE(3);
					if(gp.ui.commandNum==0) {
						gp.gameState=gp.playState;
					}
					if(gp.ui.commandNum==1) {
						
					}
					if(gp.ui.commandNum==2) {
						gp.ui.titleScreenState=2;
					}
					if(gp.ui.commandNum==3) {
						System.exit(0);
					}
				}
			}
		}
		if(gp.gameState==gp.GameOverState) {
			gp.ui.commandNum=0;
			if(code==KeyEvent.VK_ENTER) {
				gp.playSE(4);
				if(gp.ui.commandNum==0){
					gp.ui.titleScreenState=0;
					gp.gameState=gp.titleState;
					gp.player.AsteroidCollision=0;
					IsFirstGame=false;
				}
				
				//gp.ui.lifeOn=true;
				
			}
		}
		if(code==KeyEvent.VK_M) {
			MapOn=!MapOn;
			SetMap(MapOn);
		}
				
		if(code == KeyEvent.VK_W) {
			upPressed = true;
			gp.player.direction = "player1";
			gp.player.velocity += gp.player.Acceleration;
			//System.out.println(gp.player.velocity);
            if (gp.player.velocity > gp.player.MaxSpeed) {
            	gp.player.velocity = gp.player.MaxSpeed;
            }
            gp.player.fuelconsumption+=10;
            gp.player.FuelUsage();
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
			gp.player.direction = "player1";
			gp.player.velocity -= gp.player.Acceleration;
            if (gp.player.velocity < -gp.player.MaxSpeed) {
            	gp.player.velocity = -gp.player.MaxSpeed;
            }
            gp.player.fuelconsumption+=10;
            gp.player.FuelUsage();
            
		}
		if(code == KeyEvent.VK_A || code2==KeyEvent.VK_A) {
			leftPressed = true;
			gp.player.direction = "player1";
			gp.player.PlayerAngle -= gp.player.AngularVelocity;
			//System.out.println(gp.player.PlayerAngle);
			gp.player.fuelconsumption+=10;
			gp.player.FuelUsage();
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
			gp.player.direction = "player1";
			gp.player.PlayerAngle += gp.player.AngularVelocity;
			//System.out.println(gp.player.PlayerAngle);
			gp.player.fuelconsumption+=10;
			gp.player.FuelUsage();
		}
		
		if(code==KeyEvent.VK_SHIFT ) {
			speedPressed = true;
			gp.player.fuelconsumption+=50;
			gp.player.FuelUsage();
		}
		if(code == KeyEvent.VK_SPACE) {
			shotsFired = true;
		}
		//DEBUG
		if(code == KeyEvent.VK_T) {
			if(DebugMode==false) {
				DebugMode=true;
			}
			else if(DebugMode==true) {
				DebugMode=false;
			}
		}
		
		
		if(code==KeyEvent.VK_F) {
			if(gp.player.ammoType.equals("bullet1")) {
				changeFireMode=0;
				gp.player.ammoType="pellet";
				gp.ui.showMessage("Machinegun fire mode On!");
				gp.player.rein=true;
			}
			else if(gp.player.ammoType.equals("pellet")) {
				changeFireMode=1;
				gp.player.ammoType="bullet1";
				gp.ui.showMessage("Rocket fire mode On!");
				gp.player.rein=true;
			}
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.playSE(3);
			if(gp.gameState==gp.playState) {
				gp.gameState=gp.exitpauseState;
				gp.ui.titleScreenState=0;
			}
			else if(gp.gameState==gp.exitpauseState) {
				gp.gameState=gp.playState;
			}
			else if(gp.gameState == gp.pauseState) {
				gp.gameState=gp.exitpauseState;
			}
		}
		if(code == KeyEvent.VK_PAUSE) {
			gp.playSE(3);
			if(gp.gameState==gp.playState) {
				gp.gameState=gp.pauseState;
			}
			else if(gp.gameState==gp.pauseState) {
				gp.gameState=gp.playState;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code ==KeyEvent.VK_SHIFT) {
			speedPressed=false;
		}
		if(code == KeyEvent.VK_SPACE) {
			shotsFired = false;
		}
	}
	public void SetMap(boolean mapOn) {
		if(mapOn==true) {
			gp.gameState=gp.MapState;
		}
		else if(mapOn==false) {
			gp.gameState=gp.playState;
		}
	}
}