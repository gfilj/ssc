package share;

/**
 * share.WebSession 上下文保存到 当前执行线程
 */
public class WebContext {

	private static ThreadLocal<WebSession> instance = new ThreadLocal<WebSession>();

	public static WebSession getSession() {
		return (WebSession) instance.get();
	}

	public static void set(WebSession webSession) {
		instance.set(webSession);
	}
}
