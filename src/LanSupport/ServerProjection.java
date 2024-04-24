package LanSupport;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Entity.Entity;
import Game.GamePanel;

public class ServerProjection extends Entity{
	GamePanel gp;
	private String serverData; // Store data received from the client

    public ServerProjection(GamePanel gp) {
        super(gp);
        this.gp=gp;
        setDefaultValues();
        getPlayerImage();
    }

    // Method to receive and store data from the client
    public void receiveServerData(String data) {
        serverData = data;
    }
    public void setDefaultValues() {
		worldx=gp.Tilesize*400;
		worldy=gp.Tilesize*470;
		direction = "serverprojection";
		PlayerAngle = 0;
	}
	public void getPlayerImage() {
		Entity1 = setup("/player/chonker", gp.Tilesize, gp.Tilesize);			
		Entity2 = setup("/player/chonker2", gp.Tilesize, gp.Tilesize);
		Entity3 = setup("/player/chonker3", gp.Tilesize, gp.Tilesize);
		
	}
	
	public void update() {
		spriteCounter++;
		if (serverData!=null) {
            // Example: Update ServerProjection based on data received from the client
            // You need to adjust this part based on the actual data received
			String[] data = serverData.split("-");
			if(data.length>6) {
	            String playerDirection = data[0];
	            double velocity = Double.parseDouble(data[1]);
	            double playerAngle = Double.parseDouble(data[2]);
	
	            // Update ServerProjection object based on the received data
	            this.direction = playerDirection;
	            this.velocity = velocity;
	            this.PlayerAngle = playerAngle;
	            
	            double newy= velocity * Math.cos(Math.toRadians(PlayerAngle));
	    		double newx= velocity * Math.sin(Math.toRadians(PlayerAngle));
	    		switch(direction) {
				case "serverprojection":worldy-=newy;worldx+=newx;break;
				}
	            // Clear client data after processing
	        }
		}
		
		if(spriteCounter>10) {
			
			if(spriteNum==1) {
				spriteNum=2;
			}
			else if(spriteNum==2) {
				spriteNum=3;
			}
			else if(spriteNum==3) {
				spriteNum=1;
			}
			spriteCounter=0;
		}
		
	}
	public AffineTransform rotatedImage() {
	    int screenX = worldx - gp.player.worldx + gp.player.screenx;
	    int screenY = worldy - gp.player.worldy + gp.player.screeny;

	    AffineTransform transform = new AffineTransform();
	    transform.translate(screenX, screenY);
	    transform.rotate(Math.toRadians(this.PlayerAngle),gp.Tilesize/2,gp.Tilesize/2);

	    return transform;
}
}
