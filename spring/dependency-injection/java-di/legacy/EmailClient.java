public class EmailClient {
	public static void main(String[] args){
		EmailApplication app = new EmailApplication();
		app.processMessages("Hi friend", "friend@gmail.com");
	}
}
