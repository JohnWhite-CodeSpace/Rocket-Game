package Game;

import Entity.Entity;

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
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up"://ok
			entityTopRow = (entityTopWorldy-entity.speed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
		break;
		case "down": //ok
			entityBottomRow = (entityBottomWorldy+entity.speed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
		break;
		case "left": //ok
			entityLeftCol = (entityLeftWorldx-entity.speed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
		break;
		case "right": //ok
			entityRightCol = (entityRightWorldx+entity.speed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
		break;
		case "upright": //ok
			entityTopRow = (entityTopWorldy-entity.speed)/gp.Tilesize;
			entityRightCol = (entityRightWorldx+entity.speed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
				}
		break;
		case "upleft": //ok
			entityTopRow = (entityTopWorldy-entity.speed)/gp.Tilesize;
			entityLeftCol = (entityLeftWorldx-entity.speed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
				}
		break;
		case "downright": //ok
			entityBottomRow = (entityTopWorldy+entity.speed)/gp.Tilesize;
			entityRightCol = (entityRightWorldx+entity.speed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
				}
		break;
		case "downleft"://ok
			entityBottomRow = (entityBottomWorldy+entity.speed)/gp.Tilesize;
			entityLeftCol = (entityLeftWorldx-entity.speed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
				}
		break;
		case "fastup"://ok
			
			entityTopRow = (entityTopWorldy-entity.HyperSpeed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
		break;
		case "fastdown"://ok
			entityBottomRow = (entityBottomWorldy+entity.HyperSpeed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
		break;
		case "fastleft"://ok
			entityLeftCol = (entityLeftWorldx-entity.HyperSpeed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
		break;
		case "fastright": //ok
			entityRightCol = (entityRightWorldx+entity.HyperSpeed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
		break;
		
		
		case "fastupright": //ok
			entityTopRow = (entityTopWorldy-entity.HyperSpeed)/gp.Tilesize;
			entityRightCol = (entityRightWorldx+entity.HyperSpeed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
				}
		break;
		case "fastupleft":
			entityTopRow = (entityTopWorldy-entity.HyperSpeed)/gp.Tilesize;
			entityLeftCol = (entityRightWorldx-entity.HyperSpeed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
				}
		break;
		case "fastdownright"://ok
			entityBottomRow = (entityTopWorldy+entity.HyperSpeed)/gp.Tilesize;
			entityRightCol = (entityRightWorldx+entity.HyperSpeed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
				}
		break;
		case "fastdownleft":
			entityBottomRow = (entityTopWorldy+entity.HyperSpeed)/gp.Tilesize;
			entityLeftCol = (entityRightWorldx-entity.HyperSpeed)/gp.Tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
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
					case "up":
						entity.solidArea.y-=entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision==true) {
								entity.collisionOn = true;
							}
							if(player = true) {
								index  = i;
							}
						}
						break;
					case "down":
						entity.solidArea.y+=entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision==true) {
								entity.collisionOn = true;
							}
							if(player = true) {
								index  = i;
							}
						}
						break;
					case "left":
						entity.solidArea.x-=entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision==true) {
								entity.collisionOn = true;
							}
							if(player = true) {
								index  = i;
							}
						}
						break;
					case "right":
						entity.solidArea.x+=entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision==true) {
								entity.collisionOn = true;
							}
							if(player = true) {
								index  = i;
							}
						}
						break;
					case "upleft":
						entity.solidArea.y-=entity.speed;
						entity.solidArea.x-=entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision==true) {
								entity.collisionOn = true;
							}
							if(player = true) {
								index  = i;
							}
						}
						break;
					case "upright":
						entity.solidArea.y-=entity.speed;
						entity.solidArea.x+=entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision==true) {
								entity.collisionOn = true;
							}
							if(player = true) {
								index  = i;
							}
						}
						break;
					case "downleft":
						entity.solidArea.y+=entity.speed;
						entity.solidArea.x-=entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision==true) {
								entity.collisionOn = true;
							}
							if(player = true) {
								index  = i;
							}
						}
						break;
					case "downright":
						entity.solidArea.y+=entity.speed;
						entity.solidArea.x+=entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision==true) {
								entity.collisionOn = true;
							}
							if(player = true) {
								index  = i;
							}
						}
						break;
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
					case "up":
						entity.solidArea.y-=entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							index  = i;
							entity.AsteroidCollision++;
						}
						break;
					case "down":
						entity.solidArea.y+=entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							index  = i;
							entity.AsteroidCollision++;
						}
						break;
					case "left":
						entity.solidArea.x-=entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							index  = i;
							entity.AsteroidCollision++;
						}
						break;
					case "right":
						entity.solidArea.x+=entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							index  = i;
							entity.AsteroidCollision++;
						}
						break;
					case "upleft":
						entity.solidArea.y-=entity.speed;
						entity.solidArea.x-=entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							index  = i;
							entity.AsteroidCollision++;
						}
						break;
					case "upright":
						entity.solidArea.y-=entity.speed;
						entity.solidArea.x+=entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							index  = i;
							entity.AsteroidCollision++;
						}
						break;
					case "downleft":
						entity.solidArea.y+=entity.speed;
						entity.solidArea.x-=entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							index  = i;
							entity.AsteroidCollision++;
						}
						break;
					case "downright":
						entity.solidArea.y+=entity.speed;
						entity.solidArea.x+=entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
								entity.collisionOn = true;
								index  = i;
								entity.AsteroidCollision++;
						}
						break;
					case "fastup":
						entity.solidArea.y-=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							entity.AsteroidCollision++;
							index  = i;
						}
						break;
					case "fastdown":
						entity.solidArea.y+=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							entity.AsteroidCollision++;
							index  = i;
						}
						break;
					case "fastleft":
						entity.solidArea.x-=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							entity.AsteroidCollision++;
							index  = i;
						}
						break;
					case "fastright":
						entity.solidArea.x+=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							entity.AsteroidCollision++;
							index  = i;
						}
						break;
					case "fastupleft":
						entity.solidArea.y-=gp.player.HyperSpeed;
						entity.solidArea.x-=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							entity.AsteroidCollision++;
							index  = i;
						}
						break;
					case "fastupright":
						entity.solidArea.y-=gp.player.HyperSpeed;
						entity.solidArea.x+=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							entity.AsteroidCollision++;
							index  = i;
						}
						break;
					case "fastdownleft":
						entity.solidArea.y+=gp.player.HyperSpeed;
						entity.solidArea.x-=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							entity.AsteroidCollision++;
							index  = i;
						}
						break;
					case "fastdownright":
						entity.solidArea.y+=gp.player.HyperSpeed;
						entity.solidArea.x+=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
								entity.collisionOn = true;
								entity.AsteroidCollision++;
								index  = i;
						}
						break;
					}
					
					
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
				
				
			}
			return index;
			
	}
		public void playerCheck(Entity entity) {
			
			if(gp.player!=null) {
				entity.solidArea.x = entity.worldx + entity.solidArea.x;
				entity.solidArea.y = entity.worldy + entity.solidArea.y;
				
				gp.player.solidArea.x = gp.player.worldx + gp.player.solidArea.x;
				gp.player.solidArea.y = gp.player.worldy + gp.player.solidArea.y;
				switch(entity.direction) {
				case "up":
					entity.solidArea.y-=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "down":
					entity.solidArea.y+=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "left":
					entity.solidArea.x-=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "right":
					entity.solidArea.x+=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "upleft":
					entity.solidArea.y-=entity.speed;
					entity.solidArea.x-=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "upright":
					entity.solidArea.y-=entity.speed;
					entity.solidArea.x+=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "downleft":
					entity.solidArea.y+=entity.speed;
					entity.solidArea.x-=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						

					}
					break;
				case "downright":
					entity.solidArea.y+=entity.speed;
					entity.solidArea.x+=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
							entity.collisionOn = true;
							
						
					}
					break;
				case "fastup":
					entity.solidArea.y-=gp.player.HyperSpeed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "fastdown":
					entity.solidArea.y+=gp.player.HyperSpeed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "fastleft":
					entity.solidArea.x-=gp.player.HyperSpeed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "fastright":
					entity.solidArea.x+=gp.player.HyperSpeed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "fastupleft":
					entity.solidArea.y-=gp.player.HyperSpeed;
					entity.solidArea.x-=gp.player.HyperSpeed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "fastupright":
					entity.solidArea.y-=gp.player.HyperSpeed;
					entity.solidArea.x+=gp.player.HyperSpeed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						
					}
					break;
				case "fastdownleft":
					entity.solidArea.y+=gp.player.HyperSpeed;
					entity.solidArea.x-=gp.player.HyperSpeed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
						

					}
					break;
				case "fastdownright":
					entity.solidArea.y+=gp.player.HyperSpeed;
					entity.solidArea.x+=gp.player.HyperSpeed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
							entity.collisionOn = true;
							
						
					}
					break;
				}
				
				
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			
			
		}
		public void checkEntity(Entity entity, Entity target) {
			// TODO Auto-generated method stub
			
			
				if(target!=null) {
					entity.solidArea.x = entity.worldx + entity.solidArea.x;
					entity.solidArea.y = entity.worldy + entity.solidArea.y;
					
					target.solidArea.x = target.worldx + target.solidArea.x;
					target.solidArea.y = target.worldy + target.solidArea.y;
					switch(entity.direction) {
					case "up":
						entity.solidArea.y-=entity.speed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "down":
						entity.solidArea.y+=entity.speed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "left":
						entity.solidArea.x-=entity.speed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "right":
						entity.solidArea.x+=entity.speed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "upleft":
						entity.solidArea.y-=entity.speed;
						entity.solidArea.x-=entity.speed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "upright":
						entity.solidArea.y-=entity.speed;
						entity.solidArea.x+=entity.speed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "downleft":
						entity.solidArea.y+=entity.speed;
						entity.solidArea.x-=entity.speed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "downright":
						entity.solidArea.y+=entity.speed;
						entity.solidArea.x+=entity.speed;
						if(entity.solidArea.intersects(target.solidArea)) {
								entity.collisionOn = true;
							
						}
						break;
					case "fastup":
						entity.solidArea.y-=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "fastdown":
						entity.solidArea.y+=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "fastleft":
						entity.solidArea.x-=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "fastright":
						entity.solidArea.x+=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "fastupleft":
						entity.solidArea.y-=gp.player.HyperSpeed;
						entity.solidArea.x-=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "fastupright":
						entity.solidArea.y-=gp.player.HyperSpeed;
						entity.solidArea.x+=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;
						}
						break;
					case "fastdownleft":
						entity.solidArea.y+=gp.player.HyperSpeed;
						entity.solidArea.x-=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target.solidArea)) {
							entity.collisionOn = true;

						}
						break;
					case "fastdownright":
						entity.solidArea.y+=gp.player.HyperSpeed;
						entity.solidArea.x+=gp.player.HyperSpeed;
						if(entity.solidArea.intersects(target.solidArea)) {
								entity.collisionOn = true;
							
						}
						break;
					}
					
					
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target.solidArea.x = target.solidAreaDefaultX;
				target.solidArea.y = target.solidAreaDefaultY;
			
		}

}
