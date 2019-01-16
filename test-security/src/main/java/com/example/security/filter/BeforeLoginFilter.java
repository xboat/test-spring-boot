package com.example.security.filter;

import com.example.security.util.SessionUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xboat date 2019-01-16
 */
public class BeforeLoginFilter implements Filter,AuthenticationEntryPoint {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("BeforeLoginFilter--------------->");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        if(request.getRequestURI().indexOf("login")>0){
            String user=  SessionUtil.getSessionAttribute(Const.USER_SESSION_KEY,request);
            if(user==null){
                user="黄盖";
                SessionUtil.setSessionAttribute(Const.USER_SESSION_KEY,user,request);
                response.sendRedirect("/home");
            }
            else{
                response.sendRedirect("/home");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("BeforeLoginFilter------------>commence");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.sendRedirect("/hello");
    }
}
