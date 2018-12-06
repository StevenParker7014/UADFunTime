package uk.co.utiligroup.uadfun.audio;

import java.io.File;

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

  public void playsound(File file) throws Exception {
    if(!disabled) {
      System.err.println("Playing clip " + file);
      template.convertAndSend("/topic/greetings", new Message("Playing clip " + file));
      Clip sound = AudioSystem.getClip();
      
      sound.open(AudioSystem.getAudioInputStream(file));
      sound.start();
      while (sound.isRunning()) {
        Thread.sleep(1000);
        sound.close();
      }
    }
  }

}
