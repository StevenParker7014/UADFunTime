package uk.co.utiligroup.uadfun.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import uk.co.utiligroup.uadfun.listeners.GlobalKeyListener;

@Configuration
public class MainConfig {
  
  @Autowired
  GlobalKeyListener gkl;
  
  
  @PostConstruct
  public void init() {
    try {
      GlobalScreen.registerNativeHook();
    }
    catch (NativeHookException ex) {
      System.err.println("There was a problem registering the native hook.");
      System.err.println(ex.getMessage());

      System.exit(1);
    }

    GlobalScreen.addNativeKeyListener(gkl);
  }

}
