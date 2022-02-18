package com.hamonize.portal.signup;

import java.util.List;
import java.util.Map;

import com.hamonize.portal.mail.MailSendService;
import com.hamonize.portal.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/signup")
public class SignupController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SignupRepository sr;  

    @Autowired
    SignupService ss;

    @Autowired
    MailSendService mss;

    @RequestMapping("")
    public String signup() {
        logger.info("<< signup page >>");

        return "/signup/signup";
	}


    /**
     * 회원가입 정보 저장 func
     * @param User vo
     * 
     * @return
     */

    @RequestMapping("/signup")
    public String save(User vo) {
        try {
            // save to db
            ss.save(vo);
            
            // genterate authKey && sent to auhorize email
           

        } catch (Exception e) {
            logger.error("회원가입 오류 : {}",e.getMessage());    
        }
        
        return "redirect:/login";
	}

    /**
     * 아이디 중복 확인 func
     * @param User vo
     * 
     * @return 중복여부 true or false
     */
    @PostMapping("/idDupCheck")
    @ResponseBody
    public boolean idDupCheck(User vo, Model model){
        List<User> chk = sr.findByUserid(vo.getUserid());
        
        boolean ret = false;
        if (chk.size() == 0 ){
            ret = true;
        }else{
            ret = false;
        }

        return ret;
    }

    //account_verifications
    @RequestMapping("/signupConfirm")
    public String signUpConfirm(@RequestParam Map<String, String> map, User vo, Model model) {
        try {
          
            // update email authKey
            

        } catch (Exception e) {
            logger.error("회원가입 오류 : {}",e.getMessage());    
        }
        
        return "redirect:/login";
	}


    
}
