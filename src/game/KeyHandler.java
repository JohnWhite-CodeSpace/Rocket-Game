package game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {
	public boolean upPressed, downPressed, leftPressed, rightPressed, shotsFired;
	public void handleKeyPressed(KeyEvent e) {
		
		KeyCode code = e.getCode();
		
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
	}
	public void handleKeyReleased(KeyEvent e) {
		
		KeyCode code = e.getCode();
		
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
		if (code== KeyCode.SPACE) {
			shotsFired = false;
		}
	}
	@Override
	public void handle(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
