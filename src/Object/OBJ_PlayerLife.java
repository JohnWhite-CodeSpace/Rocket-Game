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
		lifeimage1=setup("/player/fulllife");
		lifeimage2=setup("/player/eleven12life");
		lifeimage3=setup("/player/ten12life");
		lifeimage4=setup("/player/nine12life");
		lifeimage5=setup("/player/eight12life");
		lifeimage6=setup("/player/seven12life");
		lifeimage7=setup("/player/six12life");
		lifeimage8=setup("/player/five12life");
		lifeimage9=setup("/player/four12life");
		lifeimage10=setup("/player/three12life");
		lifeimage11=setup("/player/two12life");
		lifeimage12=setup("/player/one12life");
		lifeimage13=setup("/player/emptylife");
				
       
    }
	public BufferedImage DrawLifeBar(int AsteroidCollision){

		image4 = null;
		switch(AsteroidCollision) {
		case 0:
			image4 = lifeimage1;
		break;
		case 1:
			image4 = lifeimage2;
		break;
		case 2:
			image4 = lifeimage3;
		break;
		case 3:
			image4 = lifeimage4;
		break;
		case 4:
			image4 = lifeimage5;
		break;
		case 5:
			image4 = lifeimage6;
		break;
		case 6:
			image4 = lifeimage7;
		break;
		case 7:
			image4 = lifeimage8;
		break;
		case 8:
			image4 = lifeimage9;
		break;
		case 9:
			image4 = lifeimage10;
		break;
		case 10:
			image4 = lifeimage11;
		break;
		case 11:
			image4 = lifeimage12;
		break;
		case 12:
			image4 = lifeimage13;
		break;
		};
		return image4;
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

