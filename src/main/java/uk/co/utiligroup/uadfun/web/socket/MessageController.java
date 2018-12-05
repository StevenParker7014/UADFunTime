package uk.co.utiligroup.uadfun.web.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {
  
  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Message greeting(Hello hello) throws Exception {
      Thread.sleep(1000); // simulated delay
      return new Message(HtmlUtils.htmlEscape(hello.getName()));
  }
  

}
