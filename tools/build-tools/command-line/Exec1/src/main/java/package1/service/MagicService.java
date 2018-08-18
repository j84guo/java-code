package package1.service;

public class MagicService{
  private final String message;

  public MagicService(){
    this.message = "hello world";
  }

  public String getMessage(){
    return message;
  }
}
