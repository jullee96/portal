package com.hamonize.portal.main;

import javax.servlet.http.HttpSession;
import com.hamonize.portal.user.SecurityUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String home() {
        return "/main/home";
	}

    @RequestMapping("/home")
    public String home1() {
        return "/main/home2";
	}

    @RequestMapping("/main")
    public String main(HttpSession session) {
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        return "/main/main";
    }
}
