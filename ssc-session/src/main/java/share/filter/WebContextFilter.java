package share.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import share.WebContext;
import share.WebSession;
import share.WebSessionManager;

public class WebContextFilter implements Filter {
	
	private ServletContext ctx;
	private WebApplicationContext wac;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.ctx = config.getServletContext();
		this.wac = WebApplicationContextUtils.getWebApplicationContext(this.ctx);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse =  (HttpServletResponse) res;
		//白名单
		Set<String> urlSet = WhiteListFilter.getWhiltListConfig();
		if (urlSet.contains(httpRequest.getServletPath())) {
			chain.doFilter(req, res);
			return;
		}
		
		try {
	        WebSessionManager sessionManager = (WebSessionManager) this.wac.getBean(WebSessionManager.class);
            WebSession session = sessionManager.getSession(httpRequest,httpResponse);
            session.restMaxInactiveInterval();
            WebContext.set(session);
			chain.doFilter(req, res);
		} finally {
			WebContext.set(null);
		}
	}

	@Override
	public void destroy() {

	}

}
