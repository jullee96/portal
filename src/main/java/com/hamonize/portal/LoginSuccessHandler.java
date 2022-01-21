package com.hamonize.portal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hamonize.portal.user.SecurityUser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("\n\n<<< login success >>> ");
        logger.info("authentication username : {}",authentication.getName());
        logger.info("authentication getPrincipal : {}", authentication.getPrincipal() );
        logger.info("authentication getPrincipal type : {}", authentication.getPrincipal().getClass() );
        logger.info("authentication getDetails : {}", authentication.getDetails() );
         
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        HttpSession httpSession = request.getSession(true);

        httpSession.setAttribute("userSession", user);

        if(user.getUserid() != null){
            response.sendRedirect("/main");
        } else{
            response.sendRedirect("/");
        }

        clearAuthenticationAttributes(request);
        
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session==null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
