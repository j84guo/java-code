import org.junit.Test; // identifies a test case method 
import static org.junit.Assert.assertEquals; // check that two objects/primitives are equal

// test class 
public class Test1 {

	@Test
	public void testJar() {
		String str = "Junit is working fine";
		assertEquals("Junit is working fine", str);
	}

}
