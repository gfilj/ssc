package share;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import share.storage.RedisSessionHandler;
import share.storage.StorageHandler;


public class WebSessionManager {
	private static final Logger logger = LoggerFactory.getLogger(WebSessionManager.class);

	private boolean readOnly;
	private CookieHandler cookieHandler;
	private StorageHandler sessionHandler;

	public WebSession getSession(HttpServletRequest request,HttpServletResponse response) {
		try {
		    WebSession session = null;
			String sessionId = this.cookieHandler.getSessionId(request,response);
			if (sessionId == null) {
				session = new WebSession(request, response, sessionHandler,cookieHandler);
				if (!readOnly) {
					session.initWebSession().addSessionInCookie();
				}
			} else {
				session = new WebSession(sessionId, request, response, sessionHandler,cookieHandler);
			}
			
			response.addHeader("Cache-Control", "private");
			return session;
		} catch (Exception e) {
			logger.error("获取session 异常:", e);
			throw new SessionException("获取session 异常", e);
		}
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public CookieHandler getCookieHandler() {
		return cookieHandler;
	}

	public void setCookieHandler(CookieHandler cookieHandler) {
		this.cookieHandler = cookieHandler;
	}

	public StorageHandler getSessionHandler() {
		return sessionHandler;
	}

	public void setSessionHandler(RedisSessionHandler sessionHandler) {
		this.sessionHandler = sessionHandler;
	}
}
