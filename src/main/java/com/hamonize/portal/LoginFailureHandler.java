package com.hamonize.portal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
                
            exception.printStackTrace();
            String message = getExceptionMessage(exception);
            logger.info("login fail... : {}",request.getParameter("userid"));
            logger.info("msg : {}",message);
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();
            out.println("<script>alert('"+message+"'); location.href='/login';</script>");
            out.flush();

    }


    private String getExceptionMessage(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException) {
            return "비밀번호불일치";
        } else if (exception instanceof UsernameNotFoundException) {
            return "계정없음";
        } else if (exception instanceof AccountExpiredException) {
            return "계정만료";
        } else if (exception instanceof CredentialsExpiredException) {
            return "비밀번호만료";
        } else if (exception instanceof DisabledException) {
            return "계정비활성화";
        } else if (exception instanceof LockedException) {
            return "계정잠김";
        } else {
            return "아이디 또는 비밀번호를 확인해주세요";
        }
    }
    
}
