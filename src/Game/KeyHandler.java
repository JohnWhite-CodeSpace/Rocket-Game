package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import LanSupport.ClientSide;
import LanSupport.ServerSide;

public class KeyHandler implements KeyListener{

	public boolean upPressed;
	public boolean downPressed;
	public boolean rightPressed;
	public boolean leftPressed;
	public boolean shiftPressed;
	public boolean speedPressed;
	public boolean shotsFired;
	public boolean enterPressed;
	public int changeFireMode=0;
	public int choice=0;
	public boolean IsFirstGame=true;
	public boolean MapOn=false;
	public boolean ObjectiveOn=true;
	public boolean MinimapOn=false;
	public boolean IsFullSCOn=false;
	public boolean ReturnToGame=false;
	public int ClientOrServer = 1;
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
		if(gp.gameState == gp.playState) {
			PlayerKeyBounds(code);
		}
		if(gp.gameState==gp.titleState) {
			TitleScreenHandler(code,0);
		}		
		if(gp.gameState==gp.exitpauseState) {
			ExitPauseHandler(code);
		}
		if(gp.gameState==gp.GameOverState) {
			GameOverState(code);
		}
		if(gp.gameState==gp.WinState) {
			WinState(code);
		}
		if(gp.gameState==gp.MultiplayerSetup) {
			try {
				MultiplayerMenuHandler(code);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(gp.gameState == gp.IPPortScreen) {
			IPPortMenuHandler(code);
			if(gp.ui.IPTextField.isEditing) {
				gp.ui.IPTextField.processKeyEvent(e);
			}
			else if(gp.ui.PortTextField.isEditing) {
				gp.ui.PortTextField.processKeyEvent(e);
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
	public void PlayerKeyBounds(int code) {
		//MAP
		if(code==KeyEvent.VK_M) {
			MapOn=!MapOn;
			SetMap(MapOn);
		}
		//OBJECTIVES
		if(code==KeyEvent.VK_I) {
			ObjectiveOn=!ObjectiveOn;
			System.out.println("Please kill me. I haven't slept for 3 days and my eyes are bleeding. Please end my suffering...");
		}
		//MINI MAP
		if(code==KeyEvent.VK_N) {
			MinimapOn=!MinimapOn;
		}
		//PLAYER MOVEMENT
		if(code == KeyEvent.VK_W) {
			upPressed = true;
			gp.player.direction = "player1";
			gp.player.velocity += gp.player.Acceleration;
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
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
			gp.player.direction = "player1";
			gp.player.PlayerAngle -= gp.player.AngularVelocity;
			gp.player.fuelconsumption+=10;
			gp.player.FuelUsage();
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
			gp.player.direction = "player1";
			gp.player.PlayerAngle += gp.player.AngularVelocity;
			gp.player.fuelconsumption+=10;
			gp.player.FuelUsage();
		}
		//EXTRA ACCELERATION
		if(code==KeyEvent.VK_SHIFT ) {
			speedPressed = true;
			gp.player.fuelconsumption+=50;
			gp.player.FuelUsage();
		}
		//FIRE
		if(code == KeyEvent.VK_SPACE) {
			shotsFired = true;
		}
		//DEBUG & GOD MODE
		if(code == KeyEvent.VK_T) {
			if(DebugMode==false) {
				DebugMode=true;
				if(gp.player.life<gp.player.maxLife) {
					gp.player.life=gp.player.maxLife;
				}
			}
			else if(DebugMode==true) {
				DebugMode=false;
			}
		}
		
		//CHANGING WEAPONS
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
		//PAUSE FROM MENU SCREEN
		if(code == KeyEvent.VK_ESCAPE) {
			gp.playSE(3);
			if(gp.gameState==gp.playState) {
				gp.gameState=gp.exitpauseState;
				gp.ui.titleScreenState=4;
			}
			else if(gp.gameState==gp.exitpauseState) {
				gp.gameState=gp.playState;
			}
			else if(gp.gameState == gp.pauseState) {
				gp.gameState=gp.exitpauseState;
			}
		}
		//PAUSE
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
	public void TitleScreenHandler(int code, int titlestate) {
		switch(gp.ui.titleScreenState) {
		case 0:
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum-- ;
				gp.playSE(4);
				if(gp.ui.commandNum<0) {
					gp.ui.commandNum=4 ;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++ ;
				gp.playSE(4);
				if(gp.ui.commandNum>4) {
					gp.ui.commandNum=0 ;
				}
			}
			if(code==KeyEvent.VK_ENTER) {
				gp.playSE(3);
				switch(gp.ui.commandNum) {
				case 0: 
					if(ReturnToGame==false) {
						gp.ui.titleScreenState=1; 
					}
					else if(ReturnToGame==true) {
						gp.gameState=gp.playState;
						ReturnToGame=false;
					}
					
					break;
				case 1:
					gp.gameState=gp.MultiplayerSetup; 
					enterPressed = false; break;
				case 2: gp.gameState = gp.LoadState; try {
					gp.config.LoadGame();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}System.out.println("Game Loaded"); break;
				case 3: gp.ui.titleScreenState=2; break;
				case 4: 
					if(gp.Server!=null) {
						gp.Server.CloseServer();
					}
					if(gp.Clients!=null) {
						gp.Clients.CloseConnection();
					}
					System.exit(0); break;
					
				}
			}
		break;
		case 1:
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
				switch(gp.ui.commandNum) {
				case 0:
					if(IsFirstGame==true) {
						choice=1;
						gp.player.setDefaultValues();
						gp.gameState=gp.playState;
					}
					if(IsFirstGame==false) {
						choice=1;
						gp.player.setDefaultValues();
						gp.restart();
						gp.gameState=gp.playState;
					}if(gp.Multiplayer==true&&ClientOrServer==1) {
						gp.startHostGame(gp.ui.IPTextField.text, Integer.parseInt(gp.ui.PortTextField.text));
                		System.out.println("Starting Server...");
					}else if(gp.Multiplayer==true && ClientOrServer==0) {
						gp.ConnectToGame(gp.ui.IPTextField.text, Integer.parseInt(gp.ui.PortTextField.text));
                		System.out.println("Starting Client..."); 
					}
				break;
				case 1:
					if(IsFirstGame==true) {
						choice=2;
						gp.player.setDefaultValues();
						gp.gameState=gp.playState;
					}
					if(IsFirstGame==false) {
						choice=2;
						gp.player.setDefaultValues();
						gp.restart();
						gp.gameState=gp.playState;
					}if(gp.Multiplayer==true&&ClientOrServer==1) {
						gp.startHostGame(gp.ui.IPTextField.text, Integer.parseInt(gp.ui.PortTextField.text));
                		System.out.println("Starting Server...");
					}else if(gp.Multiplayer==true && ClientOrServer==0) {
						gp.ConnectToGame(gp.ui.IPTextField.text, Integer.parseInt(gp.ui.PortTextField.text));
                		System.out.println("Starting Client..."); 
					}
					
				break;
				case 2: gp.ui.titleScreenState=0; break;
				}
				
			}
		break;
		case 2:
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
				switch(gp.ui.commandNum) {
				case 0:
					if(gp.fullscreenOn==true) {
						gp.setWindowScreen();
					}else {
						gp.setFullScreen();
					}
				break;
				case 1: break;
				case 2: break;
				case 3: gp.ui.titleScreenState=3; break;
				case 4: gp.ui.titleScreenState=0; break;
				}
			
			}
		break;
		case 3:
			if(code == KeyEvent.VK_W) {
				gp.playSE(4);
				gp.ui.commandNum-- ;
				if(gp.ui.commandNum<0) {
					gp.ui.commandNum=0 ;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.playSE(4);
				gp.ui.commandNum++ ;
				if(gp.ui.commandNum>0) {
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
		break;
		case 4:
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum-- ;
				gp.playSE(4);
				if(gp.ui.commandNum<0) {
					gp.ui.commandNum=4 ;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++ ;
				gp.playSE(4);
				if(gp.ui.commandNum>4) {
					gp.ui.commandNum=0 ;
				}
			}
			if(code==KeyEvent.VK_ENTER) {
				gp.playSE(3);
				switch(gp.ui.commandNum) {
				case 0: 
					if(ReturnToGame==false) {
						gp.ui.titleScreenState=1; 
					}
					else if(ReturnToGame==true) {
						gp.gameState=gp.playState;
						ReturnToGame=false;
					}
					
					break;
				case 1:	gp.config.SaveGame(); System.out.println("Game Saved");break;
				case 2: gp.gameState = gp.LoadState; try {
						gp.config.LoadGame();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}System.out.println("Game Loaded");break;
				case 3: gp.ui.titleScreenState=2;break;
				case 4: System.exit(0); break;
					
				}
			}
		break;
		}
	}
	public void MultiplayerMenuHandler(int code) throws IOException {
	if(gp.gameState==gp.MultiplayerSetup) {
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum-- ;
			gp.playSE(4);
			if(gp.ui.commandNum<0) {
				gp.ui.commandNum=4 ;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++ ;
			gp.playSE(4);
			if(gp.ui.commandNum>4) {
				gp.ui.commandNum=0 ;
			}
		}
		if(code==KeyEvent.VK_ENTER) {
			gp.playSE(3);
			switch(gp.ui.commandNum) {
			case 0: 
				if(enterPressed==true) {
					gp.ui.commandNum=0;
					gp.gameState=gp.IPPortScreen;
					ClientOrServer = 1;
				}
				enterPressed=true;
				break;
			case 1: 
				if(enterPressed==true) {
					gp.ui.commandNum=0;
					gp.gameState=gp.IPPortScreen;
					ClientOrServer = 1;
				}
				enterPressed=true;
				break;
			case 2: 
				if(enterPressed==true) {
					gp.ui.commandNum=0;
					gp.gameState=gp.IPPortScreen;
					ClientOrServer = 0;
				}
				enterPressed=true;
				break;
			case 3: 
				if(enterPressed==true) {
					gp.ui.commandNum=0;
					gp.gameState=gp.IPPortScreen;
					ClientOrServer = 1;
				}
				enterPressed=true;
				break;	
			case 4: gp.gameState = gp.titleState;
				gp.ui.titleScreenState=0;
				break;
			}
		}
	}
		
	}
	public void IPPortMenuHandler(int code) {
	    if (gp.gameState == gp.IPPortScreen) {
	        if (code == KeyEvent.VK_W && gp.ui.IPTextField.isEditing==false &&
	        		gp.ui.PortTextField.isEditing==false) {
	            gp.ui.commandNum--;
	            gp.playSE(4);
	            if (gp.ui.commandNum < 0) {
	                gp.ui.commandNum = 3;
	            }
	        }
	        if (code == KeyEvent.VK_S && gp.ui.IPTextField.isEditing==false &&
	        		gp.ui.PortTextField.isEditing==false) {
	            gp.ui.commandNum++;
	            gp.playSE(4);
	            if (gp.ui.commandNum > 3) {
	                gp.ui.commandNum = 0;
	            }
	        }
	        if (code == KeyEvent.VK_ENTER) {
	            gp.playSE(3);
	            switch (gp.ui.commandNum) {
	                case 0:
	                    // Toggle editing mode of the custom text field
	                	gp.ui.IPTextField.isSelected=!gp.ui.IPTextField.isSelected;
	                    if (!gp.ui.IPTextField.isEditing) {
	                        gp.ui.IPTextField.startEditing();
	                    } else {
	                        gp.ui.IPTextField.stopEditing();
	                    }
	                    break;
	                case 1:
	                	gp.ui.PortTextField.isSelected=!gp.ui.PortTextField.isSelected;
	                	if (!gp.ui.PortTextField.isEditing) {
	                        gp.ui.PortTextField.startEditing();
	                    } else {
	                        gp.ui.PortTextField.stopEditing();
	                    }
	                	break;
	                case 2:
	                	if (enterPressed==true) {
                        	gp.ui.commandNum=0;
                        		gp.gameState=gp.titleState;
                        		gp.ui.titleScreenState = 1;
                        		gp.Multiplayer=true;                        		
                        }
	                    break;
	                case 3:
	                    if (enterPressed==true) {
	                        gp.gameState = gp.MultiplayerSetup;
	                    }
	                    enterPressed=true;
	                    break;
	            }
	            enterPressed = true;
	        }
	    }
	}
	
	public void ExitPauseHandler(int code) {
			ReturnToGame=true;
			TitleScreenHandler(code,0);
	}
	public void GameOverState(int code) {
		gp.ui.commandNum=0;
		if(code==KeyEvent.VK_ENTER) {
			gp.playSE(4);
			if(gp.ui.commandNum==0){
				gp.ui.titleScreenState=0;
				gp.gameState=gp.titleState;
				gp.player.AsteroidCollision=0;
				IsFirstGame=false;
			}
		}
	}
	public void WinState(int code) {
		gp.ui.commandNum=0;
		if(code==KeyEvent.VK_ENTER) {
			gp.playSE(4);
			if(gp.ui.commandNum==0){
				gp.ui.titleScreenState=0;
				gp.gameState=gp.titleState;
				gp.player.AsteroidCollision=0;
				IsFirstGame=false;
			}
		}
		
	}
}