package uk.co.utiligroup.uadfun.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import uk.co.utiligroup.uadfun.audio.SoundPlay;

public class GlobalKeyListener implements NativeKeyListener {

  Random r = new Random();

  List<String> files = Arrays.asList("homer.wav", "moo.wav");

  @Override
  public void nativeKeyPressed(NativeKeyEvent e) {
    try {
      int num = r.nextInt(100);
      if (num > 98) {
        SoundPlay.playsound("scream.wav");
        return;
      }
      if (num > 90) {
        SoundPlay.playsound(files.get(r.nextInt(files.size())));
      }
    } catch (Exception exception) {

    }
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent e) {

  }

  @Override
  public void nativeKeyTyped(NativeKeyEvent e) {

  }

}
