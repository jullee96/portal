package com.hamonize.portal.signup;

import com.hamonize.portal.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class SignupController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SignupService sr;


    @RequestMapping("/signup")
    public String signup() {
        logger.info("<< signup page >>");

        return "/signup/signup";
	}

    @RequestMapping("/signup/signup")
    public String save(User vo) {
        logger.info("signup user id >> {}",vo.getUserid());
        logger.info("signup user email >> {}",vo.getEmail());
        logger.info("signup user password >> {}",vo.getPasswd());

        try {
            sr.save(vo);
            
        } catch (Exception e) {
            logger.error("회원가입 오류 : {}",e.getMessage());    
        }
        
        return "redirect:/login";
	}
}
