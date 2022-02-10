package com.hamonize.portal;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hamonize.portal.subscribe.Subscribe;
import com.hamonize.portal.subscribe.SubscribeRepostory;
import com.hamonize.portal.user.SecurityUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RequestCache reqCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
  
    private RequestCache requestCache = new HttpSessionRequestCache();
		
    @Autowired
    SubscribeRepostory sr;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
         
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        HttpSession httpSession = request.getSession(true);
        logger.info("onAuthenticationSuccess user domain : {}",user.getDomain());
        httpSession.setAttribute("userSession", user);
        
        String redirectUrl = (String) httpSession.getAttribute("url");
        logger.info("redirectUrl : ",redirectUrl);


        // // Security가 요청을 가로챈 경우 사용자가 원래 요청했던 URI 정보를 저장한 객체
		SavedRequest savedRequest = requestCache.getRequest(request, response);
        
        if (savedRequest != null) {
			String uri = savedRequest.getRedirectUrl();
			System.out.println("savedRequest >>>>>>> "+uri);
		}

        
        
        
        if(user.getUserid() != null){
            response.sendRedirect("/"); 
            // if(savedRequest != null ){
            //     response.sendRedirect("/main"); 
                
            // }else{
            //     response.sendRedirect("/main");
            // }
        } else{
            response.sendRedirect("/login");
        }

        clearAuthenticationAttributes(request);
        
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
