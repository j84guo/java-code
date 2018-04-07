import org.junit.runner.JUnitCore; // runner class will run test classes
import org.junit.runner.Result; // Result information, like failures, run time, success of all test classes
import org.junit.runner.notification.Failure; // Failure information for a test case 

public class Runner1 {
	
	public static void main(String[] args) {

		// JUnitCore's static runClasses() method runs all test cases in classes specified
		Result result = JUnitCore.runClasses(Test1.class);
		
		for(Failure f : result.getFailures()) {
			System.out.println(f.toString());
		}

		System.out.println(result.wasSuccessful());
	}
}
