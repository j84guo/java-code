public class MyDIFramework {

	public static void main(String[] args) {
		String msg = "Hi Pankaj";
		String email = "pankaj@abc.com";
		String phone = "4088888888";
		MessageServiceInjector injector = null;
		Consumer app = null;

		// injector creates consumer and injects with dependency
		// notice consumer classes are only resonsible for using the service

		//Send email
		injector = new EmailServiceInjector(); 
		app = injector.getConsumer(); 
		app.processMessages(msg, email);

		//Send SMS
		injector = new SMSServiceInjector();
		app = injector.getConsumer();
		app.processMessages(msg, phone);
	}

}
