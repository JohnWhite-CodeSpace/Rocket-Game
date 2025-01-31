package game;

import game_ui.TitleScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UI {
	GamePanel gp;
	private final TitleScreen titleScreen;
	public UI(GamePanel gp) {
		this.gp = gp;
        this.titleScreen = new TitleScreen(gp, this);
	    
	}
	public int getXForCenteredText(String text, GraphicsContext gc) {
	    Font font = gc.getFont();
	    Text tempText = new Text(text);
	    tempText.setFont(font);
	    double textWidth = tempText.getLayoutBounds().getWidth();
	    int x = (int) (gp.screenWidth / 2 - textWidth / 2);
	    return x;
	}
	public void MainMenu(GraphicsContext gc) {
		titleScreen.draw(gc);
	}
	public TitleScreen getTitleScreen() {
	    return titleScreen;
	}
	public void PauseState(GraphicsContext gc) {
		
	}
	public void RocketMenu(GraphicsContext gc) {
		
	}
	public void SettingsState(GraphicsContext gc) {
		
	}
}
