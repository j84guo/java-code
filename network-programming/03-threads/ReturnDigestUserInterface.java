import javax.xml.bind.*; // for DatatypeConverter

public class ReturnDigestUserInterface {

  // race condition
  public static void main_race_condition(String[] args) {
    for (String filename : args) {

      ReturnDigest dr = new ReturnDigest(filename);
      dr.start();

      StringBuilder result = new StringBuilder(filename);
      result.append(": ");
      byte[] digest = dr.getDigest();
      result.append(DatatypeConverter.printHexBinary(digest));
      System.out.println(result);
    }
  }

  // still a race condition, we can't guarantee the order of thread completion
  public static void main_lucky(String[] args) {
    ReturnDigest[] digests = new ReturnDigest[args.length];
    for (int i = 0; i < args.length; i++) {
      // Calculate the digest
      digests[i] = new ReturnDigest(args[i]);
      digests[i].start();
    }
    for (int i = 0; i < args.length; i++) {
      // Now print the result
      StringBuffer result = new StringBuffer(args[i]);
      result.append(": ");
      byte[] digest = digests[i].getDigest();
      result.append(DatatypeConverter.printHexBinary(digest));
      System.out.println(result);
    }
  }

  // inefficient polling technique
  public static void main_polling(String[] args) {
    ReturnDigest[] digests = new ReturnDigest[args.length];
    for (int i = 0; i < args.length; i++) {
      // Calculate the digest
      digests[i] = new ReturnDigest(args[i]);
      digests[i].start();
    }
    for (int i = 0; i < args.length; i++) {
      while (true) {
        // Now print the result
        byte[] digest = digests[i].getDigest();
        if (digest != null) {
          StringBuilder result = new StringBuilder(args[i]);
          result.append(": ");
          result.append(DatatypeConverter.printHexBinary(digest));
          System.out.println(result);
          break;
        }
      }
    }
  }

  public static void main(String[] args){
    System.out.println("Please see source code for examples of how NOT to return thread data.");
  }
}
