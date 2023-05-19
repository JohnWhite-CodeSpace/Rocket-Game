package Game;

import java.awt.Rectangle;

public class GameEventHandler {
	GamePanel gp;
	Rectangle EventRect;
	int EventDefRectX, EventDefRectY;

	public GameEventHandler(GamePanel gp) {
		// TODO Auto-generated constructor stub
		this.gp = gp;
		EventRect = new Rectangle();
		EventRect.x = 23;
		EventRect.y = 23;
		EventRect.width = 2;
		EventRect.height = 2;
		EventDefRectX = EventRect.x;
		EventDefRectY = EventRect.y;
	}
	public void checkEvent() {
		
	}
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		boolean hit = false;
		gp.player.solidArea.x = gp.player.worldx + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldy + gp.player.solidArea.y;
		
		EventRect.x = eventCol*gp.Tilesize + EventRect.x;
		EventRect.y = eventCol*gp.Tilesize + EventRect.y;
		
		if(gp.player.solidArea.intersects(EventRect)) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		EventRect.x = EventDefRectX;
		EventRect.y = EventDefRectY;
		return hit;
	}
}
