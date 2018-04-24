package share;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebCookieHandler implements CookieHandler {
	private static final Logger logger = LoggerFactory.getLogger(WebCookieHandler.class);
	private String cookieName = "_#pe_nid";
	private String domain;
	private String path ;
	private int cookieMaxAge = -1;
	private boolean secure = false;
	private boolean httpOnly = true;

	public void setSessionId(String sessionId, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(this.cookieName, sessionId);
		if ((this.domain != null) && (!this.domain.isEmpty())) {
			cookie.setDomain(this.domain);
			logger.debug("domain is " + this.domain);
		}
		if ((this.path != null) && (!this.path.isEmpty())) {
			cookie.setPath(this.path);
			logger.info("1 path  is " + this.path);
		} else {
			String contextPath = request.getContextPath();
			if (contextPath == null) {
				cookie.setPath("/");
				logger.info("2 path  is  / ");
			} else {
				//cookie.setPath(contextPath);
				cookie.setPath("/");
				logger.info("3 path  is  =="+contextPath+"11111");
			}
		}
		logger.info("cookie's path:"+cookie.getPath());
		if (this.cookieMaxAge > 0) {
			cookie.setMaxAge(this.cookieMaxAge);
		} else {
			cookie.setMaxAge(-1);
		}
		cookie.setSecure(this.secure);
		cookie.setHttpOnly(httpOnly);
		response.addCookie(cookie);
	}

	public void removeSessionId(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(this.cookieName, "");
		if ((this.domain != null) && (!this.domain.isEmpty())) {
			cookie.setDomain(this.domain);
		}
		if ((this.path != null) && (!this.path.isEmpty())) {
			cookie.setPath(this.path);
		}
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		String sessionId = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(this.cookieName)) {
					sessionId = cookie.getValue();
					break;
				}
			}
		}
		return sessionId;
	}

	public String getCookieName() {
		return this.cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	public String getCookieDomain() {
		return this.domain;
	}

	public void setCookieDomain(String cookieDomain) {
		this.domain = cookieDomain;
	}

	public int getCookieMaxAge() {
		return this.cookieMaxAge;
	}

	public void setCookieMaxAge(int cookieMaxAge) {
		this.cookieMaxAge = cookieMaxAge;
	}

	public String getCookiePath() {
		return this.path;
	}

	public void setCookiePath(String cookiePath) {
		this.path = cookiePath;
	}

	public boolean isCookieSecure() {
		return this.secure;
	}

	public void setCookieSecure(boolean cookieSecure) {
		this.secure = cookieSecure;
	}

	public boolean isHttpOnly() {
		return httpOnly;
	}

	public void setHttpOnly(boolean httpOnly) {
		this.httpOnly = httpOnly;
	}
	
}
