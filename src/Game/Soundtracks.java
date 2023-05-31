package Game;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
public class Soundtracks {
	SongArray songQueue = new SongArray();
	Clip clip;
	final boolean[] isMusicPlaying = {true};
	FloatControl fc;
	int volumeScale=3;
	float volume;
	
	public Soundtracks(){
	
	}
	public void PlayMusic() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(songQueue.getNextFile()));
        fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        //checkVolume();
        clip.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP && isMusicPlaying[0]){
                try {
                    clip.close();
                    clip.open(AudioSystem.getAudioInputStream(songQueue.getNextFile()));
                    clip.start();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });}
	public void Play() {
		isMusicPlaying[0] = true;
        if(!clip.isOpen()) {
            try {
                clip.open(AudioSystem.getAudioInputStream(songQueue.getCurrentFile()));
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println("PLAY");
        clip.start();
    }
	public void Stop() {
		 isMusicPlaying[0] = false;
         System.out.println("PAUSE");
         clip.close();
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
		class SongArray{
			URL musicOne = getClass().getResource("/sound/GameSong(3).wav");
		    URL musicTwo = getClass().getResource("/sound/GameSong(1).wav");
		    URL musicThree = getClass().getResource("/sound/GameSong(2).wav");
		    URL musicFour = getClass().getResource("/sound/GameSong(0).wav");
		    URL[] musicList = {musicOne,musicOne, musicTwo, musicThree, musicFour};
		    int songIndex = 0;

		    public URL getNextFile(){
		        if (songIndex +1> musicList.length){
		            songIndex = 0;
		        }
		        return musicList[songIndex++];
		    }
		    public URL getCurrentFile(){
		        return musicList[songIndex];
		    }
		    
	}
		

