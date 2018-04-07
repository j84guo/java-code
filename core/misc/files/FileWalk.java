import java.io.File;

// note that java.nio provides alternatives for file system interactions
public class FileWalk {

  public static void main(String... args) {
      File[] files = new File("/home/jackson/dev/java-code/core/misc").listFiles();
      showFiles(files);
  }

  // depth first search
  public static void showFiles(File[] files) {
      for (File file : files) {
          if (file.isDirectory()) {
              System.out.println("Directory: " + file.getName());
              showFiles(file.listFiles()); // Calls same method again.
          } else {
              System.out.println("File: " + file.getName());
          }
      }
  }
}
