package tile;

import java.awt.image.BufferedImage;

public class Tile {
	 public BufferedImage image;
	 public boolean collision;

	 public BufferedImage getImage() {
	    return image;
	 }
	 public Tile setImage(BufferedImage image) {
	        this.image = image;
	        return this;
	    }
}
