import junit.framework.*;

// a suite is a collection of test classes
// this class wraps junit's test suite class
public class TestSuite1 {

   public static void main(String[] a) {
      
      // add test classes to the suite
      TestSuite suite = new TestSuite(Test1.class, Test2.class, Test3.class );
      
      // suite results are stored in result object
      TestResult result = new TestResult();
      suite.run(result);

      // print results
      System.out.println("Number of test cases = " + result.runCount());
   }

}
