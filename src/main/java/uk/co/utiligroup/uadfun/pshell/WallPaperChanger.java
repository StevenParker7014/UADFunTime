package uk.co.utiligroup.uadfun.pshell;

import java.io.File;
import java.io.IOException;

import org.springframework.util.StringUtils;

public class WallPaperChanger {

  public static final String powerShellScript = "pshell/wallpaper.ps1"; // takes to args user , path to wallpaper

  public static void updateUserWallPaper(String user, String pathToWallpaper) throws IOException {
    if (StringUtils.isEmpty(user) || StringUtils.isEmpty(pathToWallpaper) || !new File(pathToWallpaper).exists()) {
      return;
    }

    File file = new File(powerShellScript);

    String[] commands =
      { "powershell.exe", "-executionpolicy", "bypass", "-File", file.getPath(), user, pathToWallpaper };

    ProcessBuilder pb = new ProcessBuilder(commands);
    pb.start();
  }
}
