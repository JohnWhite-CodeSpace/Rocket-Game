package Object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import Entity.Entity;
import Game.GamePanel;

public class OBJ_PlayerFireRecharge extends Entity{

	public OBJ_PlayerFireRecharge(GamePanel gp) {
		super(gp);
		name = "Recharge Bar";
		getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		bar1 = setup("/player/rechargefull");
		bar2 = setup("/player/rechargeempty");
	}
	public BufferedImage setup(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
	}
}
