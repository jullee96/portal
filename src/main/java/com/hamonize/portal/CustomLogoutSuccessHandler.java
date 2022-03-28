package com.hamonize.portal;

import java.io.IOException;
import java.util.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hamonize.portal.login.LoginHistory;
import com.hamonize.portal.login.LoginHistoryRepository;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    LoginHistoryRepository lhr;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
                logger.info("CustomLogoutSuccessHandler");
                LoginHistory lhvo = (LoginHistory)request.getSession().getAttribute("loginhistory");

                logger.info("domain >> {}",lhvo.getDomain());
                logger.info("userid >> {}",lhvo.getUserid());
                logger.info("seq >> {}",lhvo.getSeq());
                logger.info("logintime >> {}",lhvo.getLogindate());
                
                lhvo.setLogoutdate(LocalDateTime.now());
                Duration duration = Duration.between(lhvo.getLogindate(),LocalDateTime.now());
                long HH = duration.toHours();
                long MM = duration.toMinutesPart();
                long SS = duration.toSecondsPart();                 
                String timeInHHMMSS = String.format("%02d:%02d:%02d", HH, MM, SS);
                logger.info("timeInHHMMSS >> {}",timeInHHMMSS);
                SimpleDateFormat dateParser = new SimpleDateFormat("HH:mm:ss");
                
                try {
                    Date date = dateParser.parse(timeInHHMMSS);
                    logger.info("timestamp >> {}",date);
                    lhvo.setTimespent(date);
                    lhr.update(lhvo);

                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                

                

                
                // lhr.update(lhvo);
                if (authentication != null && authentication.getDetails() != null) {
                    try {
                         request.getSession().invalidate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } 
                response.setStatus(HttpServletResponse.SC_OK);
                response.sendRedirect("/");
    }        
}


