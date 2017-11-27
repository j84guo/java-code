public class EmailApplication {
	public static void main(String[] args){
		EmailConsumer consumer = new EmailApplication();
		consumer.processMessages("Hi friend", "friend@gmail.com");
	}
}
