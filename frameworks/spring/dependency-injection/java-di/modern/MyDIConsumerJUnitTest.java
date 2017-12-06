import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyDIConsumerJUnitTest {

	private MessageServiceInjector injector;
	
	// uses an anonymous class to mock the injected service when testing the consumer
	@Before
	public void setUp(){
		//mock the injector (and hance the service) with anonymous class
		injector = new MessageServiceInjector() {
			
			@Override
			public Consumer getConsumer() {
				//mock the message service
				return new MyDIApplication(new MessageService() {
					
					@Override
					public void sendMessage(String msg, String rec) {
						System.out.println("Mock Message Service implementation");
						
					}
				});
			}
		};
	}
	
	@Test
	public void test() {
		Consumer consumer = injector.getConsumer();
		consumer.processMessages("Hi Pankaj", "pankaj@abc.com");
	}
	
	@After
	public void tear(){
		injector = null;
	}

}
