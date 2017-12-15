import java.n et.* ;
import java.text.* ;
import java.util.Date;
import java.io.* ;

public class DaytimeDate {

	// return response as Date
	public Date getDateFromNetwork() throws IOException, ParseException {

		// try with resources opens and closes resources
		try (Socket socket = new Socket("time.nist.gov", 13)) {
			socket.setSoTimeout(15000);

			InputStream in =socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(in , "ASCII");

			StringBuilder time = new StringBuilder();
			for (int c = reader.read(); c != -1; c = reader.read()) {
				time.append((char) c);
			}

			return parseDate(time.toString());
		}
	}

	// parse string using format returning date
	static Date parseDate(String s) throws ParseException {
		String[] pieces = s.split(" ");
		String dateTime = pieces[1] + " " + pieces[2] + " UTC";
		DateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
		return format.parse(dateTime);
	}
}
