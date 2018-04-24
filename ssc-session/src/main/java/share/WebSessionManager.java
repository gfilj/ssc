package share;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import share.storage.RedisSessionHandler;
import share.storage.StorageHandler;

@Service("WebSessionManager")
public class WebSessionManager {
	private static final Logger logger = LoggerFactory.getLogger(WebSessionManager.class);

	@Value("${cookie.readOnly}")
	private boolean readOnly;
	@Autowired
	private CookieHandler cookieHandler;
	@Autowired
	private StorageHandler sessionHandler;

	public WebSession getSession(HttpServletRequest request,HttpServletResponse response) {
		try {
		    WebSession session = null;
			String sessionId = this.cookieHandler.getSessionId(request,response);
			logger.info("getSession sessionId:"+sessionId);
			logger.info("readOnly:"+readOnly);
			if (sessionId == null) {
				session = new WebSession(request, response, sessionHandler,cookieHandler);
				if (!readOnly) {
					session.initWebSession().addSessionInCookie();
				}
			} else {
				session = new WebSession(sessionId, request, response, sessionHandler,cookieHandler);
			}
			logger.info("after getSession sessionId:"+session.getSessionId());
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
