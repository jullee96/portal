package com.hamonize.portal.user;

import java.time.LocalDateTime;

import com.hamonize.portal.util.SHA256Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository ur;

    public void update(User vo){
        String salt = SHA256Util.generateSalt();
        vo.setPasswd(SHA256Util.getEncrypt(vo.getPasswd(), salt));
        vo.setSalt(salt);
        vo.setUpdtDate(LocalDateTime.now()); 
        
        ur.save(vo);

        logger.info("<< change value >>");
        logger.info("userid : {}",vo.getUserid());
        logger.info("password : {}",vo.getPasswd());
        logger.info("username : {}",vo.getUsername());
        logger.info("email : {}",vo.getEmail());

        logger.info("com_no : {}",vo.getComNo());

    }
}
