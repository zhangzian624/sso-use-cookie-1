package com.sso.controller;

import com.sso.pojo.User;
import com.sso.utils.LoginCacheUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/login")
    //传一个target地址值，可以为空，以及session和可以为空的cookie
    public String toLogin(@RequestParam(required = false,defaultValue = "") String target,
                          HttpSession session, @CookieValue(required = false,value = "TOKEN") Cookie cookie){
        //判断cookie是否为空，不为空则获取cookie的值，根据cookie值调用LoginCacherUtil类获取对应User，如果user不为空则重定向target地址
        if (cookie!=null){
            String value=cookie.getValue();
            User user= LoginCacheUtil.loginUser.get(value);
            if (user!=null){
                return "redirect:"+target;
            }
        }
        //移除session里的msg,如果target为空则给target赋首页地址值,给session中添加target信息,重定向到login方法里边去
        session.removeAttribute("msg");
        if (StringUtils.isEmpty(target)){
            target="http://localhost:9001/view/index";
        }
        session.setAttribute("target",target);
        return  "login";
    }
}
