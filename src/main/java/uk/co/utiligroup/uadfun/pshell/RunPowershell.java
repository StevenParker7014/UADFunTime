package uk.co.utiligroup.uadfun.pshell;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import org.apache.commons.io.FileUtils;
import uk.co.utiligroup.uadfun.web.socket.Message;

@Component
public class RunPowershell {

  @Autowired
  private SimpMessagingTemplate template;

  public void executeScript(String script) throws IOException {
    // save script to temp location

    File file = File.createTempFile(UUID.randomUUID().toString(), ".ps1");
    FileUtils.write(file, script, StandardCharsets.UTF_8);
    
    try {
      String[] commands =
        { "powershell.exe", "-executionpolicy", "bypass", "-File", file.getPath() };

      ProcessBuilder pb = new ProcessBuilder(commands);
      pb.start();
      template
        .convertAndSend("/topic/greetings",
                        new Message(String.format("Attempted to run script %s%s ", System.lineSeparator(), script)));

    } finally {
      file.deleteOnExit();
    }
  }

}
