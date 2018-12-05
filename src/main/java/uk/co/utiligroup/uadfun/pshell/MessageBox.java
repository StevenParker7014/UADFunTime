package uk.co.utiligroup.uadfun.pshell;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import uk.co.utiligroup.uadfun.web.socket.Message;

@Service
public class MessageBox {
  
  public static final String powerShellScript = "pshell/message.ps1"; // takes message / title

  @Autowired
  private SimpMessagingTemplate template;
  
  public void popupMessage(String title, String message) throws IOException {

    File file = new File(powerShellScript);

    String[] commands =
      { "powershell.exe", "-executionpolicy", "bypass", "-File", file.getPath(), message, title };

    ProcessBuilder pb = new ProcessBuilder(commands);
    pb.start();
    template.convertAndSend("/topic/greetings", new Message(String.format("Message Box displayed [Title: %s] [Message: %s] ", title, message)));
  }

}
