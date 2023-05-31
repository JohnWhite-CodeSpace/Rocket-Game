package Game;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[10];
	FloatControl fc;
	int volumeScale=3;
	float volume;
	public Sound() {
		
		soundURL[1] = getClass().getResource("/sound/ShotSound.wav");
		soundURL[2] = getClass().getResource("/sound/AsteroidBoom.wav");
		soundURL[3] = getClass().getResource("/sound/MenuSound1.wav");
		soundURL[4] = getClass().getResource("/sound/MenuSound2.wav");
	}
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
		
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	public void stop() {
		clip.stop();
	}
	public void checkVolume() {
    	switch(volumeScale) {
    	case 0: volume = -80f;	break;
    	case 1: volume = -19f;	break;
    	case 2: volume = -14f;	break;
    	case 3: volume = -9f;	break;
    	case 4:	volume = -4f; 	break;
    	case 5: volume = 1f; 	break;
    	case 6: volume = 6f; 	break;
    	}
    	fc.setValue(volume);
    }
}
