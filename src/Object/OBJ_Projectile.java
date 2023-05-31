package Object;



import java.awt.Rectangle;
import Entity.Projectile;
import Game.GamePanel;

public class OBJ_Projectile extends Projectile {
		GamePanel gp;
    public OBJ_Projectile(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Projectile";
        maxLife = 120;
        life = maxLife;
        IsAlive = false;
        speed=10;
        velocity = 20;
        HyperSpeed=15;
        solidArea = new Rectangle();
		solidArea.x = 15;
		solidArea.y = 15;
		solidArea.width = 16;
		solidArea.height = 16;
        getImage();
    }

    public void getImage() {
	    	Bullet1=(setup("/objects/upbul", gp.Tilesize, gp.Tilesize));
	        Bullet2=(setup("/objects/upbul2", gp.Tilesize, gp.Tilesize));
	        Bullet3=(setup("/objects/upbul3", gp.Tilesize, gp.Tilesize));
    		Bullet4=(setup("/objects/pellet", gp.Tilesize, gp.Tilesize));
        
       
    }
   
  

}


