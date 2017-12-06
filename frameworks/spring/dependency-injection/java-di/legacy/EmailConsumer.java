public class EmailConsumer {
	private EmailService email = new EmailService();

	public void processMessages(String recipient, String message){
		// data validation, manipulation, etc.
		this.email.sendEmail(recipient, message);
	}
}
