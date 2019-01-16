package com.example.security.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author xboat date 2019-01-16
 */
public class BaseController{

        protected HttpServletRequest getRequest() {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }

        protected HttpSession getSession() {
            return getRequest().getSession();
        }
}
