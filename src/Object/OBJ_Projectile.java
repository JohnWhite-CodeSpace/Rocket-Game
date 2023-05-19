package Object;



import Entity.Projectile;
import Game.GamePanel;

public class OBJ_Projectile extends Projectile {
		GamePanel gp;
    public OBJ_Projectile(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Projectile";
        speed=5;
        maxLife = 150;
        life = maxLife;
        IsAlive = false;
        
        getImage();
    }

    public void getImage() {
        up1=(setup("/objects/upbul", gp.Tilesize, gp.Tilesize));
        upright1=(setup("/objects/uprightbul", gp.Tilesize, gp.Tilesize));
        upleft1 =(setup("/objects/upleftbul", gp.Tilesize, gp.Tilesize));
        down1 =(setup("/objects/downbul", gp.Tilesize, gp.Tilesize));
        downright1=(setup("/objects/downrightbul", gp.Tilesize, gp.Tilesize));
        downleft1 = (setup("/objects/downleftbul", gp.Tilesize, gp.Tilesize));
        right1 = (setup("/objects/rightbul", gp.Tilesize, gp.Tilesize));
        left1 = (setup("/objects/leftbul", gp.Tilesize, gp.Tilesize));
        
        up2=(setup("/objects/upbul2", gp.Tilesize, gp.Tilesize));
        upright2=(setup("/objects/uprightbul2", gp.Tilesize, gp.Tilesize));
        upleft2 =(setup("/objects/upleftbul2", gp.Tilesize, gp.Tilesize));
        down2 =(setup("/objects/downbul2", gp.Tilesize, gp.Tilesize));
        downright2=(setup("/objects/downrightbul2", gp.Tilesize, gp.Tilesize));
        downleft2 = (setup("/objects/downleftbul2", gp.Tilesize, gp.Tilesize));
        right2 = (setup("/objects/rightbul2", gp.Tilesize, gp.Tilesize));
        left2 = (setup("/objects/leftbul2", gp.Tilesize, gp.Tilesize));
        
        up3=(setup("/objects/upbul3", gp.Tilesize, gp.Tilesize));
        upright3=(setup("/objects/uprightbul3", gp.Tilesize, gp.Tilesize));
        upleft3 =(setup("/objects/upleftbul3", gp.Tilesize, gp.Tilesize));
        down3 =(setup("/objects/downbul3", gp.Tilesize, gp.Tilesize));
        downright3=(setup("/objects/downrightbul3", gp.Tilesize, gp.Tilesize));
        downleft3 = (setup("/objects/downleftbul3", gp.Tilesize, gp.Tilesize));
        right3 = (setup("/objects/rightbul3", gp.Tilesize, gp.Tilesize));
        left3 = (setup("/objects/leftbul3", gp.Tilesize, gp.Tilesize));
       
    }
   

}


