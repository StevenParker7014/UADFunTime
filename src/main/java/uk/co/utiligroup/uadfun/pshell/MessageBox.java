package uk.co.utiligroup.uadfun.pshell;

import java.io.File;
import java.io.IOException;

public class MessageBox {
  
  public static final String powerShellScript = "pshell/message.ps1"; // takes message / title

  public static void popupMessage(String title, String message) throws IOException {

    File file = new File(powerShellScript);

    String[] commands =
      { "powershell.exe", "-executionpolicy", "bypass", "-File", file.getPath(), message, title };

    ProcessBuilder pb = new ProcessBuilder(commands);
    pb.start();
  }

}
