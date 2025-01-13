package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import game.GamePanel;

public class Main extends Application {
    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        GamePanel gamePanel = new GamePanel();

        Scene scene = new Scene(gamePanel, gamePanel.screenWidth, gamePanel.screenHeight);
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();
        primaryStage.setTitle("JavaFX Game");
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event -> {
            gamePanel.stopGameThread();
            System.exit(0);
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
