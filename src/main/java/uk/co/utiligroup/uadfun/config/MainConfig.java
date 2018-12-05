package uk.co.utiligroup.uadfun.config;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
    // Clear previous logging configurations.
    LogManager.getLogManager().reset();

    // Get the logger for "org.jnativehook" and set the level to off.
    Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
    logger.setLevel(Level.SEVERE);

    // Don't forget to disable the parent handlers.
    logger.setUseParentHandlers(false);
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
