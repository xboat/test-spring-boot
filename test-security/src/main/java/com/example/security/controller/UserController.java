package com.example.security.controller;

import com.example.security.filter.Const;
import com.example.security.util.SessionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xboat date 2019-01-16
 */
@RestController
@RequestMapping("/")
public class UserController extends BaseController {
    @RequestMapping("/test")
    public String getModel(){
        return "test";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request){
        String user=SessionUtil.getSessionAttribute(Const.USER_SESSION_KEY,request);
        System.out.println("home");
        System.out.println("u------>"+user);
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "logout";
    }
}
