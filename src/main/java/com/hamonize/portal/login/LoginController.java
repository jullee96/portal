package com.hamonize.portal.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hamonize.portal.user.SecurityUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public int a = 0;
    @Autowired
    HttpSession httpSession;

    @RequestMapping("")
    public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        SavedRequest save = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        logger.info("session id: {}", session.getId());
        
        // if( a % 10 == 0 ){
        //     if("".equals(save.getRedirectUrl())){
        //         String url = save.getRedirectUrl();
        //         session.setAttribute("url", url);   
        //     }
            
        // }

        a += 1;
       
        return "/login/login";
	}

    @RequestMapping("/login")
    public String loginProc() {

        return "/main/main";
	}

    @RequestMapping("/logout")
    public String logoutProc(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
    	request.getSession().invalidate();
		request.getSession(true);
        session.removeAttribute("KEY");

		return "redirect:/";
	}

    @RequestMapping("/kakao")
    public String kakaoLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        logger.info("<< kakao proc >> ");

		return "redirect:/";
	}
    
}
