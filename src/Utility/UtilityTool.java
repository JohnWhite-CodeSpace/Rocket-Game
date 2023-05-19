package Utility;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.GamePanel;

public class UtilityTool {
	    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
	        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
	        Graphics2D graphics2D = scaledImage.createGraphics();
	        graphics2D.drawImage(original, 0, 0, width, height, null);
	        graphics2D.dispose();

	        return scaledImage;
	    }
	    public static void changeAlpha(Graphics2D graphics2D, float alphaValue) {
	        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	    }
	    public static boolean isInsidePlayerView(int worldX, int worldY, GamePanel gamePanel) {
	        return worldX + gamePanel.Tilesize > gamePanel.player.worldx - gamePanel.player.screenx
	                && worldX - gamePanel.Tilesize < gamePanel.player.worldy + gamePanel.player.screeny
	                && worldY + gamePanel.Tilesize > gamePanel.player.worldx - gamePanel.player.screenx
	                && worldY - gamePanel.Tilesize < gamePanel.player.worldy + gamePanel.player.screeny;
	    }
	    
}
