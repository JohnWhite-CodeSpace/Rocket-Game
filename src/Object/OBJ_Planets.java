package Object;

import java.awt.geom.Ellipse2D;

import Entity.Planets;
import Game.GamePanel;

public class OBJ_Planets extends Planets{
	GamePanel gp;
	int graphicsFactorX;
	int graphicsFactorY;
	
	public OBJ_Planets(GamePanel gp,int Index) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
        this.Index = Index;
        getImage(returnPath(Index));
        //Change Xcircle, Ycircle params and add AdjustX and AdjustY to each planet.
        Xcircle=Radcircle/2;
	    Ycircle=Radcircle/2;
	    planetSolidArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle,Radcircle);
	    AdjustX = (int) Radcircle/2;
	    AdjustY=(int) Radcircle/2;
		infoArea = new Ellipse2D.Double(Xcircle,Ycircle,Radcircle*2,Radcircle*2);
        IsAlive = false;
        
	}
	public void getImage(String[] path) {
		Entity1 = setup(path[0], gp.Tilesize*graphicsFactorX, gp.Tilesize*graphicsFactorY);	
		Entity2 = setup(path[1], gp.Tilesize*graphicsFactorX, gp.Tilesize*graphicsFactorY);	
		Entity3 = setup(path[2], gp.Tilesize*graphicsFactorX, gp.Tilesize*graphicsFactorY);	
		Entity4 = setup(path[3], gp.Tilesize*graphicsFactorX, gp.Tilesize*graphicsFactorY);	
    }
		
    public void SetAction() {
    	direction = "planet";
    }
    
    public String[] returnPath(int Index) {
    	String [] tempArr;
    	switch(Index) {
    	case 0:
    		tempArr = new String[] {"/planets/Mercury1", "/planets/Mercury2", "/planets/Mercury3", "/planets/Mercury4"};
    		this.radius = 80*gp.Tilesize;
    		this.centerx = gp.Tilesize*500-(5/2)*gp.Tilesize;
    		this.centery= gp.Tilesize*500-(5/2)*gp.Tilesize;
    		this.name = "Mercury";
    		this.Radcircle = 5*gp.Tilesize;
    		this.angle=4.5;
    		graphicsFactorX=5;
    		graphicsFactorY=5;
    		this.PlanetSizeFactor = 4;
    		return tempArr;
    	case 1:
    		tempArr = new String[] {"/planets/Venus1", "/planets/Venus2", "/planets/Venus3", "/planets/Venus4"};
    		this.radius = 120*gp.Tilesize;
    		this.centerx = gp.Tilesize*500-(7*gp.Tilesize)/2;
    		this.centery= gp.Tilesize*500-(7*gp.Tilesize)/2;
    		this.name = "Venus";
    		this.Radcircle = 7*gp.Tilesize;
    		this.angle=1;
    		graphicsFactorX=5;
    		graphicsFactorY=5;
    		this.PlanetSizeFactor = 4;
    		return tempArr;
    	case 2:
    		tempArr = new String[] {"/planets/Earth1", "/planets/Earth2", "/planets/Earth3", "/planets/Earth4"};
    		this.radius = 140*gp.Tilesize;
    		this.centerx = gp.Tilesize*500-4*gp.Tilesize;
    		this.centery= gp.Tilesize*500-4*gp.Tilesize;
    		this.name = "Earth";
    		this.Radcircle = gp.Tilesize*8;
    		this.angle=5;
    		graphicsFactorX=8;
    		graphicsFactorY=8;
    		this.PlanetSizeFactor = 4;
    		return tempArr;
    	case 3:
    		tempArr = new String[] {"/planets/Mars1", "/planets/Mars2", "/planets/Mars3", "/planets/Mars4"};
    		this.radius = 190*gp.Tilesize;
    		this.centerx = gp.Tilesize*500-3*gp.Tilesize;
    		this.centery= gp.Tilesize*500-3*gp.Tilesize;
    		this.name = "Mars";
    		this.Radcircle = 6*gp.Tilesize;
    		this.angle=4.5;
    		graphicsFactorX=6;
    		graphicsFactorY=6;
    		this.PlanetSizeFactor = 4;
    		return tempArr;
    	case 4:
    		tempArr = new String[] {"/planets/Jupiter1", "/planets/Jupiter2", "/planets/Jupiter3", "/planets/Jupiter4"};
    		this.radius = 250*gp.Tilesize;
    		this.centerx = gp.Tilesize*500-7*gp.Tilesize;
    		this.centery= gp.Tilesize*500-7*gp.Tilesize;
    		this.name = "Jupiter";
    		this.Radcircle = 14*gp.Tilesize;
    		this.angle=3;
    		graphicsFactorX=14;
    		graphicsFactorY=14;
    		this.PlanetSizeFactor = 2;
    		return tempArr;
    	case 5:
    		tempArr = new String[] {"/planets/Saturn1", "/planets/Saturn2", "/planets/Saturn3", "/planets/Saturn4"};
    		this.radius = 310*gp.Tilesize;
    		this.centerx = gp.Tilesize*500-8*gp.Tilesize;
    		this.centery= gp.Tilesize*500-8*gp.Tilesize;;
    		this.name = "Saturn";
    		this.Radcircle = 6*gp.Tilesize;
    		this.angle=2;
    		graphicsFactorX=16;
    		graphicsFactorY=11;
    		this.PlanetSizeFactor = 3;
    		return tempArr;
    	case 6:
    		tempArr = new String[] {"/planets/Uranus1", "/planets/Uranus2", "/planets/Uranus3", "/planets/Uranus4"};
    		this.radius = 390*gp.Tilesize;
    		this.centerx = gp.Tilesize*500-6*gp.Tilesize;
    		this.centery= gp.Tilesize*500-6*gp.Tilesize;
    		this.name = "Mercury";
    		this.Radcircle = 12*gp.Tilesize;
    		this.angle=0.4;
    		graphicsFactorX=12;
    		graphicsFactorY=12;
    		this.PlanetSizeFactor = 3;
    		return tempArr;
    	case 7:
    		tempArr = new String[] {"/planets/Neptune1", "/planets/Neptune2", "/planets/Neptune3", "/planets/Neptune4"};
    		this.radius = 420*gp.Tilesize;;
    		this.centerx = gp.Tilesize*500-5*gp.Tilesize;
    		this.centery= gp.Tilesize*500-5*gp.Tilesize;
    		this.name = "Neptune";
    		this.Radcircle = 10*gp.Tilesize;
    		this.angle=5;
    		graphicsFactorX=10;
    		graphicsFactorY=10;
    		this.PlanetSizeFactor = 3;
    		return tempArr;
    	case 8:
    		tempArr = new String[] {"/planets/Pluto1", "/planets/Pluto2", "/planets/Pluto3", "/planets/Pluto4"};
    		this.radius = 480*gp.Tilesize;
    		this.centerx = gp.Tilesize*500-2*gp.Tilesize;
    		this.centery=gp.Tilesize*500-2*gp.Tilesize;
    		this.name = "Pluto";
    		this.Radcircle = 4*gp.Tilesize;
    		this.angle=0.1;
    		graphicsFactorX=4;
    		graphicsFactorY=4;
    		this.PlanetSizeFactor= 5;
    		return tempArr;
    	default:
    		tempArr = new String[] {"/planets/Pluto1", "/planets/Pluto2", "/planets/Pluto3", "/planets/Pluto4"};
    		this.radius = 480*gp.Tilesize;;
    		this.centerx = gp.Tilesize*500-(5/2)*gp.Tilesize;
    		this.centery=gp.Tilesize*500-(5/2)*gp.Tilesize;
    		this.name = "NONE - ENTITY NOT FOUND!";
    		this.Radcircle = 5*gp.Tilesize;
    		graphicsFactorX=5;
    		graphicsFactorY=5;
    		this.angle=4.5;
    		this.PlanetSizeFactor= 5;
    	}
    	return tempArr;
    }

}



