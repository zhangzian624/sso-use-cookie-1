package com.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private RestTemplate restTemplate;
    private final String LOGIN_INFO_URL = "http://localhost:9000/login/info?token=";

    public ViewController() {
    }

    @GetMapping("/index")
    public String toIndex(@CookieValue(value = "TOKEN",required = false) Cookie cookie, HttpSession session) {
        if (cookie != null) {
            String token = cookie.getValue();
            if (!StringUtils.isEmpty(token)) {
                Map map = (Map)this.restTemplate.getForObject("http://localhost:9000/login/info?token=" + token, Map.class, new Object[0]);
                session.setAttribute("loginUser", map);
            }
        }

        return "index";
    }
}
