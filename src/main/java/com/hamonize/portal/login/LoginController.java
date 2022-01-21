package com.hamonize.portal.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String login() {
        logger.info("<< login page >> ");
	     return "/login/login";
	}

    @RequestMapping("/login")
    public String loginProc() {
        logger.info("<< login proc >> ");

        return "/admin/dashboard";
	}

    @RequestMapping("/logout")
    public String logoutProc(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        logger.info("<< logout proc >> ");
	
		request.getSession().invalidate();
		request.getSession(true);
		
		return "redirect:/";
	}
}
