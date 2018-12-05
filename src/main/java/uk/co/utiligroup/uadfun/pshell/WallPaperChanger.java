package uk.co.utiligroup.uadfun.pshell;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import uk.co.utiligroup.uadfun.web.socket.Message;

@Service
public class WallPaperChanger {

  public static final String powerShellScript = "pshell/wallpaper.ps1"; // takes to args user , path to wallpaper


  @Autowired
  private SimpMessagingTemplate template;
  
  public void updateUserWallPaper(String user, String pathToWallpaper) throws IOException {
    if (StringUtils.isEmpty(user) || StringUtils.isEmpty(pathToWallpaper) || !new File(pathToWallpaper).exists()) {
      return;
    }

    File file = new File(powerShellScript);

    String[] commands =
      { "powershell.exe", "-executionpolicy", "bypass", "-File", file.getPath(), user, pathToWallpaper };

    ProcessBuilder pb = new ProcessBuilder(commands);
    pb.start();
    template.convertAndSend("/topic/greetings", new Message(String.format("Attempted to set Wallpaper [%s] for user [%s]", new File(pathToWallpaper).getName(), user)));
  }
}
