package talenti.pro.filter;

public class UserContext {

	private static final ThreadLocal<String> currentUser = new ThreadLocal<>();

	public static void setUsuario(String login) {
		currentUser.set(login);
	}

	public static String getUsuario() {
		return currentUser.get();
	}

	public static void clear() {
		currentUser.remove();
	}

}
