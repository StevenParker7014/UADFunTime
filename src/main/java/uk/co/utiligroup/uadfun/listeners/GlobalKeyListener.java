package uk.co.utiligroup.uadfun.listeners;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import uk.co.utiligroup.uadfun.audio.SoundPlay;

@Component
public class GlobalKeyListener implements NativeKeyListener {

  Random r = new Random();

  List<File> files;
  List<File> specials;

  boolean enabledStrangeKeys;
  
  public GlobalKeyListener() {
    File root = new File("sounds");
    files = Arrays.asList(root.listFiles(f -> f.isFile() && f.getName().toLowerCase().endsWith("wav")));
    specials = Arrays.asList(new File(root, "special").listFiles(f -> f.isFile() && f.getName().toLowerCase().endsWith("wav")));
  }  

  @Autowired
  private SoundPlay soundPlay;

  @Override
  public void nativeKeyPressed(NativeKeyEvent e) {
    try {
      int num = r.nextInt(1000);
      if (num >= 666 && num <= 669) {
        soundPlay.playsound(specials.get(r.nextInt(specials.size())));
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
    String[] words = { "asdfk", " so bad ", " suck it ", "lknnoin", " shitty code " };
    int num = r.nextInt(1000);
    if (enabledStrangeKeys && num >= 600 && num <= 700) {
      String toPost = words[r.nextInt(words.length)];
      for (char c : toPost.toCharArray()) {
        KeyCodes kc = KeyCodes.getKeyCode("" + c);
        GlobalScreen.postNativeEvent(new NativeKeyEvent(NativeKeyEvent.NATIVE_KEY_PRESSED,
                                                        0, // WHEN
                                                        0, // modifiers
                                                        kc.getRawCode(), // raw
                                                        kc.getCode(), // keycode
                                                        NativeKeyEvent.CHAR_UNDEFINED));
      }
    }
  }

  @Override
  public void nativeKeyTyped(NativeKeyEvent e) {

  }
  
  
  
  public boolean isEnabledStrangeKeys() {
    return enabledStrangeKeys;
  }

  public void setEnabledStrangeKeys(boolean enabledStrangeKeys) {
    this.enabledStrangeKeys = enabledStrangeKeys;
  }



  public enum KeyCodes {
    A(NativeKeyEvent.VC_A, KeyEvent.getExtendedKeyCodeForChar('A')),
    B(NativeKeyEvent.VC_B, KeyEvent.getExtendedKeyCodeForChar('B')),
    C(NativeKeyEvent.VC_C, KeyEvent.getExtendedKeyCodeForChar('C')),
    D(NativeKeyEvent.VC_D, KeyEvent.getExtendedKeyCodeForChar('D')),
    E(NativeKeyEvent.VC_E, KeyEvent.getExtendedKeyCodeForChar('E')),
    F(NativeKeyEvent.VC_F, KeyEvent.getExtendedKeyCodeForChar('F')),
    G(NativeKeyEvent.VC_G, KeyEvent.getExtendedKeyCodeForChar('G')),
    H(NativeKeyEvent.VC_H, KeyEvent.getExtendedKeyCodeForChar('H')),
    I(NativeKeyEvent.VC_I, KeyEvent.getExtendedKeyCodeForChar('I')),
    J(NativeKeyEvent.VC_J, KeyEvent.getExtendedKeyCodeForChar('J')),
    K(NativeKeyEvent.VC_K, KeyEvent.getExtendedKeyCodeForChar('K')),
    L(NativeKeyEvent.VC_L, KeyEvent.getExtendedKeyCodeForChar('L')),
    M(NativeKeyEvent.VC_M, KeyEvent.getExtendedKeyCodeForChar('M')),
    N(NativeKeyEvent.VC_N, KeyEvent.getExtendedKeyCodeForChar('N')),
    O(NativeKeyEvent.VC_O, KeyEvent.getExtendedKeyCodeForChar('O')),
    P(NativeKeyEvent.VC_P, KeyEvent.getExtendedKeyCodeForChar('P')),
    Q(NativeKeyEvent.VC_Q, KeyEvent.getExtendedKeyCodeForChar('Q')),
    R(NativeKeyEvent.VC_R, KeyEvent.getExtendedKeyCodeForChar('R')),
    S(NativeKeyEvent.VC_S, KeyEvent.getExtendedKeyCodeForChar('S')),
    T(NativeKeyEvent.VC_T, KeyEvent.getExtendedKeyCodeForChar('T')),
    U(NativeKeyEvent.VC_U, KeyEvent.getExtendedKeyCodeForChar('U')),
    V(NativeKeyEvent.VC_V, KeyEvent.getExtendedKeyCodeForChar('V')),
    W(NativeKeyEvent.VC_W, KeyEvent.getExtendedKeyCodeForChar('W')),
    X(NativeKeyEvent.VC_X, KeyEvent.getExtendedKeyCodeForChar('X')),
    Y(NativeKeyEvent.VC_Y, KeyEvent.getExtendedKeyCodeForChar('Y')),
    Z(NativeKeyEvent.VC_Z, KeyEvent.getExtendedKeyCodeForChar('Z')),
    SPACE(NativeKeyEvent.VC_SPACE, KeyEvent.getExtendedKeyCodeForChar(' '));

    int code;
    int rawCode;
    
    private KeyCodes(int code, int rawCode) {
      this.code = code;
      this.rawCode = rawCode;
    }

    public int getCode() {
      return code;
    }

    public void setCode(int code) {
      this.code = code;
    }

    public int getRawCode() {
      return rawCode;
    }

    public void setRawCode(int rawCode) {
      this.rawCode = rawCode;
    }
    
    public static KeyCodes getKeyCode(String code) {
      try {
        return KeyCodes.valueOf(" ".equals(code) ? "SPACE" : code.toUpperCase());
      } catch (Exception exception) {
        Random r = new Random();
        return KeyCodes.values()[r.nextInt(KeyCodes.values().length)];
      }
    }
  }

}
