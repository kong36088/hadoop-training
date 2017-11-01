package com.project.contact.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by jwl on 2016/4/22.
 */
public class LoginFilter extends HttpServlet implements Filter {
    private static final long serialVersionUID = 1L;

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");//登录人角色
        String url = request.getRequestURI();
        //不通过
        if ( username == null || "".equals(username)) {
                response.sendRedirect(request.getContextPath() + "/login/login.html");
                return;
        }
        arg2.doFilter(arg0, arg1);
        return;
    }

}
