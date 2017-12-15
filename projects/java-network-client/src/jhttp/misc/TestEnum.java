enum HttpVerb {
	GET, PUT, DELETE, POST, OPTION
}

public class TestEnum {

	public static void main(String[] args) {
		HttpVerb verb = HttpVerb.GET;
		if (verb.toString() == "GET") System.out.println(HttpVerb.valueOf("GET"));
	}
}
