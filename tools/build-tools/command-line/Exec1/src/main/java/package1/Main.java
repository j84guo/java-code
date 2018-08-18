package package1;

import package1.service.MagicService;

public class Main{
  public static void main(String[] args){
    System.out.println("main class start");
    MagicService service = new MagicService();
    System.out.println("message: " + service.getMessage());
  }
}
