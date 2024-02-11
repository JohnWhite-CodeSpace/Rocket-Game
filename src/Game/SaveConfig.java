package Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveConfig {
	 GamePanel gp;
	 
	 public SaveConfig(GamePanel gp) {
		 this.gp = gp;
	 }
	 
	 public void SaveGame(){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("SaveConfig.txt"));
			//FullScreen config
			if(gp.fullscreenOn ==true) {
				bw.write("FullScreen: On");
			}
			else if(gp.fullscreenOn==false) {
				bw.write("FullScreen: Off");
			}
			bw.newLine();
			
			//Music Volume
			
			bw.write("Music Volume: " + String.valueOf(gp.songs.volumeScale));
			bw.newLine();
			bw.write("Sound Volume: "+String.valueOf(gp.sound.volumeScale));
			bw.newLine();
			//Player
			bw.write("Player type: " + String.valueOf(gp.player.Choice));
			bw.newLine();
			bw.write("Player X position: " + String.valueOf(gp.player.worldx));
			bw.newLine();
			bw.write("Player Y position: " + String.valueOf(gp.player.worldy));
			bw.newLine();
			bw.write("Player angle position: " + String.valueOf(gp.player.PlayerAngle));
			bw.newLine();
			bw.write("Player score: " + String.valueOf(gp.player.planettoken));
			bw.newLine();
			bw.write("Player fuel: " + String.valueOf(gp.player.fuel));
			bw.newLine();
			bw.write("Player life: " + String.valueOf(gp.player.life));
			bw.newLine();
			//Space station
			
			bw.write("Space Station X position: " + String.valueOf(gp.spacestation.worldx));
			bw.newLine();
			bw.write("Space Station Y position: " + String.valueOf(gp.spacestation.worldy));
			bw.newLine();
			bw.write("Space Station angle position: " + String.valueOf(gp.spacestation.SpSangle));
			bw.newLine();
			
			//Planets
			
			for(int i=0; i<gp.planets.length; i++) {
				bw.write("Planet " + i + " X position: " + String.valueOf(gp.planets[i].worldx) );
				bw.newLine();
				bw.write("Planet " + i + " Y position: " + String.valueOf(gp.planets[i].worldy) );
				bw.newLine();
			}
			
			//Asteroids
			
			for(int i=0; i<gp.asteroids.length; i++) {
				if(gp.asteroids[i]!=null) {
					bw.write("Asteroid status: " + gp.asteroids[i].IsAlive);
					bw.newLine();
					if(gp.asteroids[i].IsAlive==true) {
						bw.write("Asteroid " + i + " X position: " + String.valueOf(gp.asteroids[i].worldx) );
						bw.newLine();
						bw.write("Asteroid " + i + " Y position: " + String.valueOf(gp.asteroids[i].worldy) );
						bw.newLine();
					}
					
				}else {
					bw.write("Asteroid status: false");
					bw.newLine();
				}
				
			}
			
			//Asteroid belt
			
			for(int i=0; i<gp.asteroidBelt.length; i++) {
				if(gp.asteroidBelt[i]!=null) {
					bw.write("Asteroid in belt status: " + String.valueOf(gp.asteroidBelt[i].IsAlive));
					bw.newLine();
					if(gp.asteroidBelt[i].IsAlive==true) {
						bw.write("Asteroid in belt " + i + " X position: " + String.valueOf(gp.asteroidBelt[i].worldx) );
						bw.newLine();
						bw.write("Asteroid in belt " + i + " Y position: " + String.valueOf(gp.asteroidBelt[i].worldy) );
						bw.newLine();
					}
					
				}else {
					bw.write("Asteroid in belt status: false");
					bw.newLine();
				}
				
			}
			
			//Comets
			
			for(int i=0; i<gp.comets.length; i++) {
				if(gp.comets[i]!=null) {
					bw.write("Comet status: " + String.valueOf(gp.comets[i].IsAlive));
					bw.newLine();
					if(gp.comets[i].IsAlive==true) {
						bw.write("Comet " + i + " X position: " + String.valueOf(gp.comets[i].worldx) );
						bw.newLine();
						bw.write("Comet " + i + " Y position: " + String.valueOf(gp.comets[i].worldy) );
						bw.newLine();
					}
					
				}else {
					bw.write("Comet status: false");
					bw.newLine();
				}
			}
			
			//Aliens
			
			for(int i=0; i<gp.aliens.length; i++) {
				if(gp.aliens[i]!=null) {
					bw.write("Alien Spaceship status: " + gp.aliens[i].IsAlive);
					bw.newLine();
					if(gp.aliens[i].IsAlive==true) {
						bw.write("Alien Spaceship " + i + " X position: " + String.valueOf(gp.aliens[i].worldx) );
						bw.newLine();
						bw.write("Alien Spaceship " + i + " Y position: " + String.valueOf(gp.aliens[i].worldy) );
						bw.newLine();
					}
					
				}else {
					bw.write("Alien Spaceship status: false");
					bw.newLine();
				}
			}
			bw.close();
			gp.ui.showMessage("Game Saved");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
	 
	 public void LoadGame() throws InterruptedException {
		 int progress =0;
		 GetLoadingProgress(progress);
		 String status = "Loading...";
		 GetLoadingData(status);
		 Thread.sleep(500);
		 try {
			BufferedReader br = new BufferedReader(new FileReader("SaveConfig.txt"));
			String s = br.readLine();
			int xpos, ypos, choice, life, fuel, score;
			double angle;
			if(s.replace("FullScreen: ","") == "On") {
				gp.fullscreenOn = true;
			}else if(s.replace("FullScreen: ","") == "Off") {
				gp.fullscreenOn = false;
			}
			progress = 1;
			GetLoadingProgress(progress);
			status = "Loading player data...";
			GetLoadingData(status);
			Thread.sleep(500);
			s = br.readLine();
			gp.songs.volumeScale = Integer.parseInt(s.replace("Music Volume: ",""));
			s = br.readLine();
			gp.sound.volumeScale = Integer.parseInt(s.replace("Sound Volume: ", ""));
			s = br.readLine();
			choice = Integer.parseInt(s.replace("Player type: ", ""));
			s = br.readLine();
			xpos = Integer.parseInt(s.replace("Player X position: ", ""));
			s = br.readLine();
			ypos = Integer.parseInt(s.replace("Player Y position: ", ""));
			s = br.readLine();
			angle = Double.parseDouble(s.replace("Player angle position: ", ""));
			s = br.readLine();
			score = Integer.parseInt(s.replace("Player score: ", ""));
			s = br.readLine();
			fuel = Integer.parseInt(s.replace("Player fuel: ", ""));
			s = br.readLine();
			life = Integer.parseInt(s.replace("Player life: ", ""));
			gp.player.SetSavedValues(xpos, ypos, angle, choice, life, fuel, score);
			s = br.readLine();
			progress = 2;
			GetLoadingProgress(progress);
			status = "Loading space station position";
			GetLoadingData(status);
			Thread.sleep(500);
			gp.spacestation.worldx = Integer.parseInt(s.replace("Space Station X position: ", ""));
			s = br.readLine();
			gp.spacestation.worldy = Integer.parseInt(s.replace("Space Station Y position: ", ""));
			s = br.readLine();
			gp.spacestation.SpSangle = Integer.parseInt(s.replace("Space Station angle position: ", ""));
			progress = 3;
			GetLoadingProgress(progress);
			status = "Regenerating planets positions";
			GetLoadingData(status);
			Thread.sleep(500);
			for(int i=0; i<gp.planets.length; i++) {
				s = br.readLine();
				gp.planets[i].worldx = Integer.parseInt(s.replace("Planet " + i + " X position: ", ""));
				s = br.readLine();
				gp.planets[i].worldy = Integer.parseInt(s.replace("Planet " + i + " Y position: ", ""));
				
			}
			progress = 4;
			GetLoadingProgress(progress);
			status = "Setting asteroids and comets";
			GetLoadingData(status);
			Thread.sleep(500);
			for(int i=0; i<gp.asteroids.length; i++) {
				if(gp.asteroids[i]!=null) {
					s = br.readLine();
					if(Boolean.parseBoolean(s.replace("Asteroid status: ",""))==true) {
						s = br.readLine();
						gp.asteroids[i].worldx = Integer.parseInt(s.replace("Asteroid " + i + " X position: ", ""));
						System.out.println(gp.asteroids[i].worldx);
						s = br.readLine();
						gp.asteroids[i].worldy = Integer.parseInt(s.replace("Asteroid " + i + " Y position: ", ""));
					}else {
						gp.asteroids[i]=null;
					}
				}
						
			}
			for(int i=0; i<gp.asteroidBelt.length; i++) {
				if(gp.asteroidBelt[i]!=null) {
					s = br.readLine();
					if(Boolean.parseBoolean(s.replace("Asteroid in belt status: ",""))==true) {
						s = br.readLine();
						gp.asteroidBelt[i].worldx = Integer.parseInt(s.replace("Asteroid in belt " + i + " X position: ", ""));
						s = br.readLine();
						gp.asteroidBelt[i].worldy = Integer.parseInt(s.replace("Asteroid in belt " + i + " Y position: ", ""));
					}else {
						gp.asteroidBelt[i] = null;
					}
				}		
			}
			for(int i=0; i<gp.comets.length; i++) {
				if(gp.comets[i]!=null) {
					s = br.readLine();
					if(Boolean.parseBoolean(s.replace("Comet status: ",""))==true) {
						s = br.readLine();
						gp.comets[i].worldx = Integer.parseInt(s.replace("Comet " + i + " X position: ", ""));
						s = br.readLine();
						gp.comets[i].worldy = Integer.parseInt(s.replace("Comet " + i + " Y position: ", ""));
					}
					
				}
			}
			progress = 5;
			GetLoadingProgress(progress);
			status = "Setting up enemies...";
			GetLoadingData(status);
			Thread.sleep(500);
			for(int i=0; i<gp.aliens.length; i++) {
				if(gp.aliens[i]!=null) {
					s = br.readLine();
					if(Boolean.parseBoolean(s.replace("Alien Spaceship status: ",""))==true) {
						s = br.readLine();
						gp.aliens[i].worldx = Integer.parseInt(s.replace("Alien Spaceship " + i + " X position: ", ""));
						s = br.readLine();
						gp.aliens[i].worldy = Integer.parseInt(s.replace("Alien Spaceship " + i + " Y position: ", ""));
					}
				}
			}
			progress = 6;
			GetLoadingProgress(progress);
			status = "Finalising...";
			GetLoadingData(status);
			Thread.sleep(500);
			br.close();
			gp.gameState = gp.playState;
			gp.gameState = gp.pauseState;
			gp.ui.showMessage("Game Loaded");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public void GetLoadingProgress(int state){
		 gp.ui.LoadProgress = state;
	 }
	 public void GetLoadingData(String LoadingObj) {
		 gp.ui.LoadMessage = LoadingObj;
	 }
}
