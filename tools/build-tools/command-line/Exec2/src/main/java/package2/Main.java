package package2;

import package1.service.MagicService;

public class Main{
  public static void main(String[] args){
    System.out.println("Exec2 main");

    MagicService service = new MagicService();
    System.out.println("MagicService from package1");

    System.out.println(service.getMessage());
  }
}
