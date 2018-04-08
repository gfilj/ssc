package share;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieHandler {
    public abstract void setSessionId(String paramString,
                                      HttpServletRequest paramHttpServletRequest,
                                      HttpServletResponse paramHttpServletResponse)
                    throws ServletException;

    public abstract void removeSessionId(
            HttpServletRequest paramHttpServletRequest,
            HttpServletResponse paramHttpServletResponse)
                    throws ServletException;

    public abstract String getSessionId(
            HttpServletRequest paramHttpServletRequest,
            HttpServletResponse paramHttpServletResponse)
                    throws ServletException;

    public abstract String getCookieName();

    public abstract void setCookieName(String paramString);

    public abstract String getCookieDomain();

    public abstract void setCookieDomain(String paramString);

    public abstract int getCookieMaxAge();

    public abstract void setCookieMaxAge(int paramInt);

    public abstract String getCookiePath();

    public abstract void setCookiePath(String paramString);

    public abstract boolean isCookieSecure();

    public abstract void setCookieSecure(boolean paramBoolean);
}
