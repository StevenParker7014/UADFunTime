package uk.co.utiligroup.uadfun.audio;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlay {
  
  public static boolean disabled = true;

  public static void playsound(String file) throws Exception {
    if(!disabled) {
      System.err.println("Playing clip " + file);
      URL uri = SoundPlay.class.getResource("/sound/" + file);
      Clip sound = AudioSystem.getClip();
      sound.open(AudioSystem.getAudioInputStream(new File(uri.toURI())));
      sound.start();
      while (sound.isRunning()) {
        Thread.sleep(1000);
        sound.close();
      }
    }
  }

}
