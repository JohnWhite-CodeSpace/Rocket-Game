package tilemap_manager;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Tile {
    public Image image;
    public boolean collision = false;
    public Rectangle collisionArea;

    public Tile() {
        if (collision) {
            this.collisionArea = new Rectangle();
        }
    }
}
