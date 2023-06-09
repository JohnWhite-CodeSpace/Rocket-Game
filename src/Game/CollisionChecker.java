package Game;
import Entity.Entity;
import java.awt.geom.Ellipse2D;
public class CollisionChecker {
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp=gp;
	}
	public void CheckTile(Entity entity) {
		int entityLeftWorldx = entity.worldx + entity.solidArea.x;
		int entityRightWorldx = entity.worldx + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldy = entity.worldy + entity.solidArea.y;
		int entityBottomWorldy = entity.worldy + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldx/gp.Tilesize;
		int entityRightCol = entityRightWorldx/gp.Tilesize;
		int entityTopRow = entityTopWorldy/gp.Tilesize;
		int entityBottomRow = entityBottomWorldy/gp.Tilesize;
		
		int tileNum1, tileNum2, tileNum3, tileNum4;
		
		switch(entity.direction) {
		case "player1":
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
		    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
		    tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
		    tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
		    if ((gp.tileM.tile[tileNum1].collision==true && gp.tileM.tile[tileNum2].collision==true) &&
		        ((gp.player.PlayerAngle >= 270 && gp.player.PlayerAngle <= 360) ||
		         (gp.player.PlayerAngle >= -360 && gp.player.PlayerAngle <= -270) ||
		         (gp.player.PlayerAngle >= -90 && gp.player.PlayerAngle <= 90))) {
		    	entity.collisionOn = true;
		    }
		    
		    else if ((gp.tileM.tile[tileNum3].collision==true && gp.tileM.tile[tileNum4].collision==true) &&
		               ((gp.player.PlayerAngle >= 180 && gp.player.PlayerAngle <= 270) ||
		                (gp.player.PlayerAngle >= -180 && gp.player.PlayerAngle <= -90) ||
		                (gp.player.PlayerAngle >= -270 && gp.player.PlayerAngle <= -90) ||
		                (gp.player.PlayerAngle >= 90 && gp.player.PlayerAngle <= 180))) {
		    	entity.collisionOn = true;
		    }
		    
		    else if ((gp.tileM.tile[tileNum1].collision==true && gp.tileM.tile[tileNum3].collision==true) &&
			            ((gp.player.PlayerAngle <= 0 && gp.player.PlayerAngle>=-90 )||
			            (gp.player.PlayerAngle <= -90 && gp.player.PlayerAngle>=-180 )|| 
			            (gp.player.PlayerAngle>=180 && gp.player.PlayerAngle<=360))) {
		    	entity.collisionOn = true;
			}
		    
		    else if ((gp.tileM.tile[tileNum2].collision==true && gp.tileM.tile[tileNum4].collision==true) &&
			            ((gp.player.PlayerAngle <= 180 && gp.player.PlayerAngle>=0 )||
			            (gp.player.PlayerAngle <= -180 && gp.player.PlayerAngle>=-360 ))) {
				entity.collisionOn = true;
			}else {
				entity.collisionOn=false;
			}
		    
		    
		    

		    break;
		case "comet","asteroid":
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
		    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
		    tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
		    tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
		    if ((gp.tileM.tile[tileNum1].collision==true && gp.tileM.tile[tileNum2].collision==true) &&
		        ((entity.Aangle >= 270 && entity.Aangle <= 360) ||
		         (entity.Aangle >= -360 && entity.Aangle <= -270) ||
		         (entity.Aangle >= -90 && entity.Aangle <= 90))) {
		    	entity.collisionOn = true;
		    }
		    
		    else if ((gp.tileM.tile[tileNum3].collision==true && gp.tileM.tile[tileNum4].collision==true) &&
		               ((entity.Aangle >= 180 && entity.Aangle <= 270) ||
		                (entity.Aangle >= -180 && entity.Aangle <= -90) ||
		                (entity.Aangle >= -270 && entity.Aangle <= -90) ||
		                (entity.Aangle >= 90 && entity.Aangle <= 180))) {
		    	entity.collisionOn = true;
		    }
		    
		    else if ((gp.tileM.tile[tileNum1].collision==true && gp.tileM.tile[tileNum3].collision==true) &&
			            ((entity.Aangle <= 0 && entity.Aangle>=-90 )||
			            (entity.Aangle <= -90 && entity.Aangle>=-180 )|| 
			            (entity.Aangle>=180 && entity.Aangle<=360))) {
		    	entity.collisionOn = true;
			}
		    
		    else if ((gp.tileM.tile[tileNum2].collision==true && gp.tileM.tile[tileNum4].collision==true) &&
			            ((entity.Aangle <= 180 && entity.Aangle>=0 )||
			            (entity.Aangle <= -180 && entity.Aangle>=-360 ))) {
				entity.collisionOn = true;
			}else {
				entity.collisionOn=false;
			}
		    
		    
		    

		    break;
		}
	
	}
		public int checkObject(Entity entity, boolean player) {
			int index =999;
			
			for(int i=0; i<gp.obj.length; i++) {
				if(gp.obj[i]!=null) {
					entity.solidArea.x = entity.worldx + entity.solidArea.x;
					entity.solidArea.y = entity.worldy + entity.solidArea.y;
					
					gp.obj[i].solidArea.x = gp.obj[i].worldx + gp.obj[i].solidArea.x;
					gp.obj[i].solidArea.y = gp.obj[i].worldy + gp.obj[i].solidArea.y;
					switch(entity.direction) {
					case "player1":
						entity.solidArea.y-=entity.velocity * Math.cos(Math.toRadians(entity.PlayerAngle));
						entity.solidArea.x+=entity.velocity * Math.sin(Math.toRadians(entity.PlayerAngle));
					break;
					}
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision==true) {
							entity.collisionOn = true;
						}
						if(player = true) {
							index  = i;
						}
					}
					
				}
				
			}
			return index;
		}
		public int checkEntity(Entity entity, Entity[] target) {
			
			int index =999;
			for(int i=0; i<target.length; i++) {
				if(target[i]!=null) {
					entity.solidArea.x = entity.worldx + entity.solidArea.x;
					entity.solidArea.y = entity.worldy + entity.solidArea.y;
					
					target[i].solidArea.x = target[i].worldx + target[i].solidArea.x;
					target[i].solidArea.y = target[i].worldy + target[i].solidArea.y;
					switch(entity.direction) {
					case "player1":
						entity.solidArea.y-=gp.player.velocity * Math.cos(Math.toRadians(gp.player.PlayerAngle));
						entity.solidArea.x+=gp.player.velocity * Math.sin(Math.toRadians(gp.player.PlayerAngle));
						break;
					case "bullet1","pellet":
						entity.solidArea.y-=gp.player.velocity * Math.cos(Math.toRadians(gp.player.PlayerAngle));
						entity.solidArea.x+=gp.player.velocity * Math.sin(Math.toRadians(gp.player.PlayerAngle));
						break;
						
					}
					if(entity.solidArea.intersects(target[i].solidArea)) {
						if(target[i]!=entity) {
							entity.collisionOn = true;
							index  = i;
						}
					}
					
				
					entity.solidArea.x = entity.solidAreaDefaultX;
					entity.solidArea.y = entity.solidAreaDefaultY;
					target[i].solidArea.x = target[i].solidAreaDefaultX;
					target[i].solidArea.y = target[i].solidAreaDefaultY;
				}
				
				
			}
			return index;
			
	}
		public boolean playerCheck(Entity entity) {
			boolean contactPlayer = false;
			if(gp.player!=null) {
				entity.solidArea.x = entity.worldx + entity.solidArea.x;
				entity.solidArea.y = entity.worldy + entity.solidArea.y;
				
				gp.player.solidArea.x = gp.player.worldx + gp.player.solidArea.x;
				gp.player.solidArea.y = gp.player.worldy + gp.player.solidArea.y;
				switch(entity.direction) {
				case "player1":
					entity.solidArea.y-=gp.player.velocity * Math.cos(Math.toRadians(gp.player.PlayerAngle));
					entity.solidArea.x+=gp.player.velocity * Math.sin(Math.toRadians(gp.player.PlayerAngle));
					break;
				
				case "ABelt":
					entity.solidArea.y-=gp.player.velocity * Math.cos(Math.toRadians(gp.player.PlayerAngle));
					entity.solidArea.x+=gp.player.velocity * Math.sin(Math.toRadians(gp.player.PlayerAngle));
					break;
				}
				if(entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;
					contactPlayer = true;
				}
				
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			
			return contactPlayer;
		}
		public boolean checkEntity(Entity entity, Entity target) {
			// TODO Auto-generated method stub
			boolean contactPlayer = false;
			
				if(target!=null) {
					entity.solidArea.x = entity.worldx + entity.solidArea.x;
					entity.solidArea.y = entity.worldy + entity.solidArea.y;
					
					target.solidArea.x = target.worldx + target.solidArea.x;
					target.solidArea.y = target.worldy + target.solidArea.y;
					switch(entity.direction) {

					case "player1":
						entity.solidArea.y-=gp.player.velocity * Math.cos(Math.toRadians(gp.player.PlayerAngle));
						entity.solidArea.x+=gp.player.velocity * Math.sin(Math.toRadians(gp.player.PlayerAngle));
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
							contactPlayer = true;
						}
					break;
				case "ABelt":
					entity.solidArea.y-=gp.player.velocity * Math.cos(Math.toRadians(gp.player.PlayerAngle));
					entity.solidArea.x+=gp.player.velocity * Math.sin(Math.toRadians(gp.player.PlayerAngle));
					if(entity.solidArea.intersects(target.solidArea)) {
						entity.collisionOn = true;
						contactPlayer = true;
					}
					break;
				}
					
					
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target.solidArea.x = target.solidAreaDefaultX;
				target.solidArea.y = target.solidAreaDefaultY;
				return contactPlayer;
		}

		public boolean PlanetPlayerCheck(Entity entity,Entity target) {
			double planetSAX= (int) (target.worldx + target.Xcircle);
			double planetSAY = (int) (target.worldy + target.Ycircle);
			boolean contactPlayer = false;
			Ellipse2D solidarea = null;
			if(entity!=null) {
				
				
				entity.solidArea.x = entity.worldx + entity.solidArea.x;
				entity.solidArea.y = entity.worldy + entity.solidArea.y;
				switch(entity.direction) {
				
				case "player1":
					planetSAY-=gp.player.velocity * Math.cos(Math.toRadians(gp.player.PlayerAngle));
					planetSAX+=gp.player.velocity * Math.sin(Math.toRadians(gp.player.PlayerAngle));
					solidarea = new Ellipse2D.Double(planetSAX, planetSAY,target.Radcircle,target.Radcircle);
					break;
					
				}
				if(solidarea!=null)
				if(solidarea.intersects(entity.solidArea)) {
					entity.collisionOn = true;
					if(target!=gp.earth) {
						entity.life=0;
					}
					if(target==gp.earth) {
						gp.player.GameStatus();
					}
					contactPlayer = true;
				}
				
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			planetSAX = target.solidAreaDefaultX;
			planetSAY = target.solidAreaDefaultY;
			return contactPlayer;
		
		}
		public int checkPlanetInfo(Entity entity, Entity[] target) {
			int index = 999;
			for(int i=0; i<target.length;i++) {
				if(target[i]!=null) {
				double planetSAX= (int) (target[i].worldx + target[i].Xcircle-3*target[i].Radcircle/2);
				double planetSAY = (int) (target[i].worldy + target[i].Ycircle-3*target[i].Radcircle/2);
				Ellipse2D infoarea = null;
				if(entity!=null) {
					
					
					entity.solidArea.x = entity.worldx + entity.solidArea.x;
					entity.solidArea.y = entity.worldy + entity.solidArea.y;
					switch(entity.direction) {
					
					case "player1":
						planetSAY-=gp.player.velocity * Math.cos(Math.toRadians(gp.player.PlayerAngle));
						planetSAX+=gp.player.velocity * Math.sin(Math.toRadians(gp.player.PlayerAngle));
						infoarea = new Ellipse2D.Double(planetSAX, planetSAY,target[i].Radcircle*3,target[i].Radcircle*3);
						break;
						
					}
					if(infoarea!=null)
					if(infoarea.intersects(entity.solidArea)) {
						index=i;
					}
					
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				planetSAX = target[i].solidAreaDefaultX;
				planetSAY = target[i].solidAreaDefaultY;
				}
			}
			
			
			return index;
		}

}
