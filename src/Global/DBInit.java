package Global;

public class DBInit {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/web_community_db";
	private static final String USER = "web_service";
	private static final String PW = "1234";

	public static String getUrl() {
		return URL;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPw() {
		return PW;
	}

}
