package uk.co.utiligroup.uadfun.web.socket;

import java.time.LocalDateTime;

public class Message {

  private String message;
  private LocalDateTime created = LocalDateTime.now();
  
  public Message(String message) {
    super();
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

}
