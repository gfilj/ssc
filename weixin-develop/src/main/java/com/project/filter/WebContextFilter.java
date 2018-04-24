package com.project.filter;

import com.project.common.util.LogUtil;
import org.apache.commons.logging.Log;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import share.WebContext;
import share.WebSession;
import share.WebSessionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * $myDescription
 * Create by Fenix_Bao on 2018/4/1.
 */
@Component
@Order(1)
@WebFilter(urlPatterns = "/*",filterName = "WebContextFilter")
public class WebContextFilter implements Filter {

    private ServletContext ctx;
    private WebApplicationContext wac;
    private Log logger = LogUtil.getLogger(getClass());

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
//        Set<String> urlSet = WhiteListFilter.getWhiltListConfig();
//        if (urlSet.contains(httpRequest.getServletPath())) {
//            chain.doFilter(req, res);
//            return;
//        }
        logger.info("in WebContextFilter:"+httpRequest.getContextPath()+"1111");

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
