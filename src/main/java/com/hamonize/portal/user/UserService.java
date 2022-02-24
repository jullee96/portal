package com.hamonize.portal.user;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hamonize.portal.util.SHA256Util;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;


@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
    // HttpServletRequest request = servletContainer.getRequest();

    @Autowired
    UserRepository ur;

    public void update(User vo){
        String salt = SHA256Util.generateSalt();
        vo.setPasswd(SHA256Util.getEncrypt(vo.getPasswd(), salt));
        vo.setSalt(salt);
        vo.setUpdtDate(LocalDateTime.now()); 
        
        ur.updatePasswd(vo);
        
        logger.info("<< change value >>");
        logger.info("userid : {}",vo.getUserid());
        logger.info("password : {}",vo.getPasswd());
        logger.info("username : {}",vo.getUsername());
        logger.info("email : {}",vo.getEmail());

        // session 정보 업데이트
        // HttpSession session = request.getSession(true);
        // SecurityUser user = new SecurityUser(vo);
        
        // if (session != null) {
        
        //     session.removeAttribute("userSession");
        //     // session.setAttribute("userSession", user);
        // }
    }
}
