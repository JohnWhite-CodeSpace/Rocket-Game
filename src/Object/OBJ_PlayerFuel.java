package Object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import Entity.Entity;
import Game.GamePanel;

public class OBJ_PlayerFuel extends Entity{
	GamePanel gp;
	BufferedImage image4;
	public OBJ_PlayerFuel(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stubthis.gp = gp;
        name = "Player Life";
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		fuel100=setup("/player/fuel100");
		fuel75=setup("/player/fuel50");
		fuel50=setup("/player/fuel25");
		fuel25=setup("/player/fuelA0");
		fuel0=setup("/player/fuelempty");
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
