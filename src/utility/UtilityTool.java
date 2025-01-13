package utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UtilityTool {
	public Image scaleImage(Image original, int width, int height) {
	    ImageView imageView = new ImageView(original);
	    imageView.setFitWidth(width);
	    imageView.setFitHeight(height);
	    imageView.setPreserveRatio(true);
	    return imageView.getImage(); 
	}
}
