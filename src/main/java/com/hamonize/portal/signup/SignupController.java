package com.hamonize.portal.signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            // genterate authKey && sent to auhorize email
            vo.setAuthkey(mss.getKey(6));
            vo.setStatus("A");
            logger.info("vo.getAuthkey() > {}", vo.getAuthkey());
            mss.sendAuthMail(vo.getEmail(),vo.getAuthkey());
            ss.save(vo);
           
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


    /**
     * 아이디 중복 확인 func
     * @param User vo
     * 
     * @return 중복여부 true or false
     */
    @PostMapping("/emailDupCheck")
    @ResponseBody
    public boolean emailDupCheck(User vo, Model model){
        List<User> chk = sr.findByEmail(vo.getEmail());
        
        boolean ret = false;
        if (chk.size() == 0 ){
            ret = true;
        }else{
            ret = false;
        }

        return ret;
    }

    /**
     * 회원 가입시 이메일 확인
     * authkey > 조회후 일치하면 상태값 > A로 변경
     * role도 guest > user로 변경
     * @param map
     * @param vo
     * @param model
     * @return
     */
    @RequestMapping("/signUpConfirm")
    public String signUpConfirm(@RequestParam Map<String, String> map, User vo, Model model) {
        try {
            logger.info("map >> {}", map.get("email"));
            logger.info("authkey >> {}", map.get("authkey"));

            // update email authKey && role
            vo.setEmail(map.get("email").trim());
            vo.setAuthkey(map.get("authkey").trim());    
            vo.setRole("ROLE_USER");
            int ret = sr.updateAuthkey(map.get("email").trim(),"A",vo.getRole());
            logger.info("ret {}", ret);
        } catch (Exception e) {
            logger.error("회원가입 오류 : {}",e.getMessage());    
        }
        
        return "redirect:/login";
	}

    /**
     * 회원탈퇴 이메일 확인시 
     * 유저 상태값 A -> IA로 
     * 변경하는 메서드
     * 
     * @param session
     * @param request
     * @param response
     * @param vo
     * @return
     * @throws IOException
     */
    @RequestMapping("/resignConfirm")
    public String resignConfirm(HttpSession session, HttpServletRequest request, HttpServletResponse response, User vo)throws IOException {
        String msg ="";
        vo.setUpdtdate(LocalDateTime.now());
        vo.setStatus("IA");
        
        if(sr.updateStatus(vo)==1){
            msg = "탈퇴 완료";
        } else{
            msg = "탈퇴 실패, 관리자에게 문의";
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('"+msg+"'); location.href='/login';</script>");
        out.flush();

        request.getSession().invalidate();
		request.getSession(true);

        session.removeAttribute("userSession");
        session.removeAttribute("profileImg");
     
        return "/login";
        
    }
}
