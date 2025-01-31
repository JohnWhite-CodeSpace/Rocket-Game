package game;

import java.util.Random;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements javafx.event.EventHandler<KeyEvent> {
    public boolean upPressed, downPressed, leftPressed, rightPressed, shotsFired, MinimapOn, FullMap = false;
    public GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void handle(KeyEvent e) {
        KeyCode code = e.getCode();
        switch (e.getEventType().getName()) {
            case "KEY_PRESSED":
                handleKeyPressed(code);
                break;
            case "KEY_RELEASED":
                handleKeyReleased(code);
                break;
        }
    }

    public void handleKeyPressed(KeyCode code) {
        switch (gp.game_state) {
            case 1:
                handleTitleScreenKeyPressed(code);
                break;
            case 2: 
                PlayStateKeyPressed(code);
                break;
        }
    }

    public void handleKeyReleased(KeyCode code) {
        switch (gp.game_state) {
            case 1:
                break;
            case 2:
                PlayStateKeyReleased(code);
                break;
        }
    }

    private void handleTitleScreenKeyPressed(KeyCode code) {
        if (code == KeyCode.W || code == KeyCode.UP) {
            gp.ui.getTitleScreen().previousOption();
        }
        if (code == KeyCode.S || code == KeyCode.DOWN) {
            gp.ui.getTitleScreen().nextOption();
        }
        if (code == KeyCode.ENTER) {
            int selectedOption = gp.ui.getTitleScreen().getSelectedOption();
            if (selectedOption == 0) {
            	gp.refresh_screen = true;
                gp.game_state = gp.play_state;
            }
            else if(selectedOption == 4) {
            	gp.exit();
            }
        }
    }

    private void PlayStateKeyPressed(KeyCode code) {
    	Random random = new Random();
		if (code == KeyCode.W) {
			upPressed = true;
		}
		if (code == KeyCode.S) {
			downPressed = true;
		}
		if (code == KeyCode.A) {
			leftPressed =true;
		}
		if (code == KeyCode.D) {
			rightPressed = true;
		}
		if (code== KeyCode.SPACE) {
			shotsFired = true;
		}
		if (code == KeyCode.M) {
			MinimapOn = !MinimapOn;
			
		}
		if (code == KeyCode.N) {
			FullMap = true;
		}
		if (code == KeyCode.Q) {
			gp.bestRoute.solveTSP();
		}
		if (code == KeyCode.T) {
			gp.player.worldX = random.nextInt(480000);
			gp.player.worldY = random.nextInt(480000);
		}
        if (code == KeyCode.ESCAPE) {
        	gp.game_state = gp.menu_state;
        	gp.refresh_screen = true;
        }
    }

    private void PlayStateKeyReleased(KeyCode code) {
        if (code == KeyCode.W) {
            upPressed = false;
        }
        if (code == KeyCode.S) {
            downPressed = false;
        }
        if (code == KeyCode.A) {
            leftPressed = false;
        }
        if (code == KeyCode.D) {
            rightPressed = false;
        }
        if (code == KeyCode.SPACE) {
            shotsFired = false;
        }
		if (code == KeyCode.N) {
			FullMap = false;
		}
    }
}