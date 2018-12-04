package uk.co.utiligroup.uadfun.web;

import java.io.File;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FileUtils;
import uk.co.utiligroup.uadfun.audio.SoundPlay;
import uk.co.utiligroup.uadfun.pshell.MessageBox;
import uk.co.utiligroup.uadfun.pshell.WallPaperChanger;

@RestController
@RequestMapping("/rest")
public class ControlPoint {
  
  public static final String workingDirectory = "work";
  
  @GetMapping("/sound")
  public String toggleSounds() {
    SoundPlay.disabled = !SoundPlay.disabled;
    return "Sound " + (SoundPlay.disabled ? "off" : "on");
  }
  
  @PostMapping("/wallpaper/{user}")
  public String changeWallpaper(@RequestParam("file") MultipartFile file, @PathVariable("user") String user) throws Exception {
      File wp = new File(workingDirectory, file.getOriginalFilename());
      wp.getParentFile().mkdirs();
      FileUtils.copyInputStreamToFile(file.getInputStream(), wp);
      WallPaperChanger.updateUserWallPaper(user, wp.getAbsolutePath());
      return "{\"message\" : \"done\"}";
  }
  
  @GetMapping("/message/{title}/{message}")
  public String messageBox(@PathVariable("title") String title, @PathVariable("message") String message) throws Exception{
    MessageBox.popupMessage(title, message);
    return "{\"message\" : \"done\"}";
  }
  
}
