package Object;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import Entity.Entity;
import Game.GamePanel;
public class OBJ_PlayerLife extends Entity{
	GamePanel gp;
	BufferedImage image4;
	public OBJ_PlayerLife(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stubthis.gp = gp;
        name = "Player Life";
        
        getImage();
		// TODO Auto-generated constructor stub
	}
	public void getImage() {
		lifeimage1=setup("/player/lifefull");
		lifeimage2=setup("/player/lifeempty");
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

