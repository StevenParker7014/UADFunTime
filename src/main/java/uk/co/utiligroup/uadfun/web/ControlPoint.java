package uk.co.utiligroup.uadfun.web;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FileUtils;
import uk.co.utiligroup.uadfun.audio.SoundPlay;
import uk.co.utiligroup.uadfun.pshell.MessageBox;
import uk.co.utiligroup.uadfun.pshell.WallPaperChanger;
import uk.co.utiligroup.uadfun.web.socket.Message;

@RestController
public class ControlPoint {
  
  public static final String workingDirectory = "work";
  
  @Autowired
  WallPaperChanger wallPaperChanger;
  
  @Autowired
  MessageBox messageBox;
  
  @Autowired
  SoundPlay soundPlay;
  
  @Autowired
  private SimpMessagingTemplate template;
  
  @GetMapping("/rest/sound")
  public String toggleSounds() {
    soundPlay.disabled = !soundPlay.disabled;
    template.convertAndSend("/topic/greetings", new Message("Sound " + (soundPlay.disabled ? "off" : "on")));
    return "Sound " + (soundPlay.disabled ? "off" : "on");
  }
  
  @PostMapping("/rest/wallpaper")
  public String changeWallpaper(@RequestParam("file") MultipartFile file, @RequestParam ("user") String user) throws Exception {
      File wp = new File(workingDirectory, file.getOriginalFilename());
      wp.getParentFile().mkdirs();
      FileUtils.copyInputStreamToFile(file.getInputStream(), wp);
      wallPaperChanger.updateUserWallPaper(user, wp.getAbsolutePath());
      return "{\"message\" : \"done\"}";
  }
  
  @PostMapping("/rest/message")
  public String messageBox(@RequestParam("title") String title, @RequestParam("message") String message) throws Exception{
    messageBox.popupMessage(title, message);
    return "{\"message\" : \"done\"}";
  }
  
}
