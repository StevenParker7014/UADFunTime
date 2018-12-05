package uk.co.utiligroup.uadfun.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import uk.co.utiligroup.uadfun.audio.SoundPlay;

@Component
public class GlobalKeyListener implements NativeKeyListener {

  Random r = new Random();

  List<String> files = Arrays.asList("homer.wav", "moo.wav");
  
  @Autowired
  private SoundPlay soundPlay;

  @Override
  public void nativeKeyPressed(NativeKeyEvent e) {
    try {
      int num = r.nextInt(1000);
      System.err.println(num);
      if (num >= 666 && num <= 669) {
        soundPlay.playsound("scream.wav");
        return;
      }
      if (num > 970) {
        soundPlay.playsound(files.get(r.nextInt(files.size())));
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent e) {

  }

  @Override
  public void nativeKeyTyped(NativeKeyEvent e) {

  }

}
