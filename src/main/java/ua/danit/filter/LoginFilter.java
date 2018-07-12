package ua.danit.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if(req.getServletPath().equals("/login")){
            chain.doFilter(request,response);
            return;
        }

        Cookie[] cookies = req.getCookies();

        boolean userIdFound = false;

        if(cookies != null){
            for (Cookie cookie : cookies) {
                if("userId".equals(cookie.getName())){
                    userIdFound = true;
                    break;
                }
            }
        }

        if(userIdFound){
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

    }
}
