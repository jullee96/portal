package com.hamonize.portal.login;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hamonize.portal.user.SecurityUser;
import com.hamonize.portal.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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
        // SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        // logger.info("session id: {}", session.getId());
        
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
    
    // @GetMapping("/request")
    // public String getCookie(HttpSession session) {
    //     String sessionKey = session.getId();
    //     SecurityUser user = (SecurityUser) session.getAttribute("userSession");
     
    //     logger.info("get userId = {}",user.getUserid());

    //     session.setAttribute("ID", user.getUserid());
    //     logger.info("set userId = {}",user.getUserid());
        
    //     RestTemplate restTemplate = new RestTemplate();
    //     HttpHeaders header = new HttpHeaders();
        
    //     header.add("Cookie", "SESSION="+sessionKey);
    //     HttpEntity<String> requestEntity = new HttpEntity<>(null, header);
        
    //     ResponseEntity<String> cookieValue = restTemplate.exchange("http://localhost:8081/request",HttpMethod.GET ,requestEntity ,String.class);
      
    //     System.out.println("server1_sessionKey : "+session.getId()+"\nserver2_sessionKey : "+cookieValue.getBody());
    //     System.out.println("server1_sessionKey : "+session.getId()+"\nserver2_sessionKey : "+cookieValue.getHeaders());

    //     return "server1_sessionKey : "+session.getId()+"<br>server2_sessionKey : "+cookieValue.getBody();
    // }

    // @GetMapping(path = "/setsession")
    // public String setsessionAPI() {
    //     String value = "ttesttt";
        
    //     httpSession.setAttribute("KEY", value);
    //     String returnValue = LocalDateTime.now().toString() + " \n<br>session set id : " + httpSession.getId() + " \n<br>session set Value : " + value;
    //     return returnValue;
    // }

}
