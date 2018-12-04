package uk.co.utiligroup.uadfun.audio;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import uk.co.utiligroup.uadfun.web.socket.Message;

@Service
public class SoundPlay {
  
  public boolean disabled = true;
  
  @Autowired
  private SimpMessagingTemplate template;

  public void playsound(String file) throws Exception {
    if(!disabled) {
      System.err.println("Playing clip " + file);
      template.convertAndSend("/gs-guide-websocket", new Message("Playing clip " + file));
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
