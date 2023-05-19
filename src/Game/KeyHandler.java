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
		if(gp.gameState==gp.titleState) {
			if(gp.ui.titleScreenState==0) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum-- ;
					if(gp.ui.commandNum<0) {
						gp.ui.commandNum=2 ;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.commandNum++ ;
					if(gp.ui.commandNum>2) {
						gp.ui.commandNum=0 ;
					}
				}
				if(code==KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum==0) {
						gp.ui.titleScreenState=1;
					}
					if(gp.ui.commandNum==1) {
						
					}
					if(gp.ui.commandNum==2) {
						System.exit(0);
					}
				}
			}
			else if(gp.ui.titleScreenState==1) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum-- ;
					if(gp.ui.commandNum<0) {
						gp.ui.commandNum=2 ;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.commandNum++ ;
					if(gp.ui.commandNum>2) {
						gp.ui.commandNum=0 ;
					}
				}
				if(code==KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum==0) {
						gp.gameState=gp.playState;
						gp.player.ChooseRocket(1);
					}
					if(gp.ui.commandNum==1) {
						gp.gameState=gp.playState;
						gp.player.ChooseRocket(2);
					}
					if(gp.ui.commandNum==2) {
						gp.ui.titleScreenState=0;
					}
				}
			}
		}
		if(gp.gameState==gp.exitpauseState) {
			if(gp.ui.titleScreenState==0) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum-- ;
					if(gp.ui.commandNum<0) {
						gp.ui.commandNum=2 ;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.commandNum++ ;
					if(gp.ui.commandNum>2) {
						gp.ui.commandNum=0 ;
					}
				}
				if(code==KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum==0) {
						gp.gameState=gp.playState;
					}
					if(gp.ui.commandNum==1) {
						
					}
					if(gp.ui.commandNum==2) {
						System.exit(0);
					}
				}
			}
		}
		if(gp.gameState==gp.GameOverState) {
			gp.ui.commandNum=0;
			if(code==KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum==0){
					gp.ui.titleScreenState=0;
					gp.gameState=gp.titleState;
					gp.player.AsteroidCollision=0;
				}
				
				//gp.ui.lifeOn=true;
				
			}
		}
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code==KeyEvent.VK_SHIFT ) {
			speedPressed = true;
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
		if(code == KeyEvent.VK_ESCAPE) {
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
		if(code == KeyEvent.VK_W) {
			
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code ==KeyEvent.VK_SHIFT) {
			speedPressed=false;
		}
		if(code == KeyEvent.VK_SPACE) {
			shotsFired = false;
		}
	}

}
