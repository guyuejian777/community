package com.dajian.community.controller;

import com.dajian.community.model.User;
import com.dajian.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            return null;
        }
        String token =null;
        for (int i=0; i<cookies.length; i++){
            if (cookies[i].getName().equals("token")){
                token = cookies[i].getValue();
            }
            break;
        }
        User user = userService.queryUserByToken(token);
        if (user == null){
            return null;
        }
        request.getSession().setAttribute("user", user);

        return "index";
    }
}
