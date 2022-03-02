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
        
        if(vo.getPasswd() == null || "".equals(vo.getPasswd())){
            User tmpUsr = ur.findByUserid(vo.getUserid()).get();
            vo.setPasswd(tmpUsr.getPasswd());
            vo.setSalt(tmpUsr.getSalt());
            
        } else{
            vo.setPasswd(SHA256Util.getEncrypt(vo.getPasswd(), salt));
            vo.setSalt(salt);
        }

        
        vo.setUpdtDate(LocalDateTime.now()); 
        ur.save(vo);


    }
}
