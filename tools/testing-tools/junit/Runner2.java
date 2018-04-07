import org.junit.runner.JUnitCore; // tes trunner 
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

// this class is really a wrapper aroudn the test runner 
public class Runner2 {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(Test2.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
   }
}  
