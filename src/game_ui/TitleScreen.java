package game_ui;

import game.GamePanel;
import game.UI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TitleScreen {
    private final GamePanel gp;
    private final UI ui;
    private final Image titleImage;
    private final Image chonkerImage;
    private int commandNum = 0;
    
    public TitleScreen(GamePanel gp, UI ui) {
        this.gp = gp;
        this.ui = ui;
        this.titleImage = new Image(getClass().getResourceAsStream("/objects/TitleScreen.png"));
        this.chonkerImage = new Image(getClass().getResourceAsStream("/player/chonker.png"));
    }
    
    public void draw(GraphicsContext gc) {
        gc.drawImage(titleImage, 0, 0, gp.screenWidth, gp.screenHeight);

        String titleText = "Rocket Adventure (BETA)";
        gc.setFont(Font.font("Arial", 50));
        int x = ui.getXForCenteredText(titleText, gc);
        int y = gp.screenHeight / 6;
        drawTextWithShadow(gc, titleText, x, y);

        gc.drawImage(chonkerImage, gp.screenWidth / 2 - gp.tileSize, y + gp.tileSize, gp.tileSize * 2, gp.tileSize * 2);

        String[] menuOptions = {"START GAME", "MULTIPLAYER (LAN)", "LOAD GAME", "OPTIONS", "EXIT"};
        y += gp.tileSize * 4;
        gc.setFont(Font.font("Arial", 30));
        for (int i = 0; i < menuOptions.length; i++) {
            String option = menuOptions[i];
            x = ui.getXForCenteredText(option, gc);
            drawTextWithShadow(gc, option, x, y);

            if (i == commandNum) {
                gc.setFill(Color.GREEN);
                gc.fillText(">", x - gp.tileSize, y);
            }
            y += gp.tileSize * 2;
        }
    }
    
    private void drawTextWithShadow(GraphicsContext gc, String text, int x, int y) {
        gc.setFill(Color.BLACK);
        gc.fillText(text, x + 5, y + 5);
        gc.setFill(Color.WHITE);
        gc.fillText(text, x, y);
    }

    public void nextOption() {
        commandNum = (commandNum + 1) % 5;
    }

    public void previousOption() {
        commandNum = (commandNum - 1 + 5) % 5;
    }

    public int getSelectedOption() {
        return commandNum;
    }
}
