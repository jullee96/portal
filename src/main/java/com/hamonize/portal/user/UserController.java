package com.hamonize.portal.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hamonize.portal.company.Company;
import com.hamonize.portal.company.CompanyRepository;
import com.hamonize.portal.company.CompanyService;
import com.hamonize.portal.file.FileRepository;
import com.hamonize.portal.file.FileVO;
import com.hamonize.portal.util.SHA256Util;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FileRepository fr;

    @Autowired
    UserService us;

    @Autowired
    UserRepository ur;

    @Autowired
    CompanyService cs;

    @Autowired
    CompanyRepository cr;

    @RequestMapping("/detail")
    public String signup(HttpSession session, User vo, Model model) {
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
       logger.info("user id >>> {}",user.getUserid());
       Company newComVo = cr.findByUserid(user.getUserid());
       model.addAttribute("companyInfo", newComVo);

       FileVO file = fr.findByUseridAndKeytype(user.getUserid(), "img");
        try {
            if( !"".equals(file.getFilepath().toString()) ){
                session.setAttribute("profileImg", file.getFilepath());
            }
                
        } catch (NullPointerException e) {
            logger.error("profileimg 없음 ");
        }

        return "/user/detail";
	}

    @RequestMapping("/update")
    public String save(HttpSession session, HttpServletResponse response, User vo, Company cvo, Model model ) throws IOException {
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        vo.setUserid(user.getUserid());
        vo.setDomain(user.getDomain());
        cvo.setUserid(user.getUserid());
        
        // 수정실패시 출력 메세지
        response.setContentType("text/html; charset=UTF-8");
        // String message = getExceptionMessage(exception);
            
        PrintWriter out = response.getWriter();

    
        if(!"".equals(vo.getBefore_passwd()) && vo.getBefore_passwd() != null){
            us.update(vo);
            cs.update(cvo);


            // update session user
            User newVo = ur.findByUserid(vo.getUserid()).get();
            
            SecurityUser updateUser = new SecurityUser(newVo);
            session.removeAttribute("userSession");
            session.setAttribute("userSession", updateUser);

        }else{ 
            // out.println("<script>alert(''); location.href='/login';</script>");
            // out.flush();
            logger.info("변경사항 없음");
        }

        return "redirect:/user/detail";
	}


    @RequestMapping("/images")
    public void imgView(HttpSession session, HttpServletResponse response, Model model)  throws IOException {
        logger.info("<<<<<<<<<<<< imgView images >>>>>>>>>");
        
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        FileVO file = fr.findByUseridAndKeytype(user.getUserid(), "img");
         
        try {
           
            logger.info("file path : {}", file.getFilepath());
            StringBuilder sb =new StringBuilder();
            
            if(file.getFilepath().contains("://")){
                sb = new StringBuilder(file.getFilepath());
                logger.info("......;;; : {}", sb.toString());
            
            } else{
                sb = new StringBuilder("file:"+ file.getFilepath());
            }

            URL fileUrl = new URL(sb.toString());
            IOUtils.copy(fileUrl.openStream(), response.getOutputStream());
            
            
        } catch (NullPointerException e) {
            
            logger.error("NullPointerException", e);
        }
     
	}

    @RequestMapping("/passwdChk")
    @ResponseBody
    public Boolean passwdChk(@RequestParam("before_passwd") String before_passwd , HttpSession session, Model model)  throws IOException {
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
       
        logger.info("get before_passwd {}",before_passwd);
        
        logger.info("session {}", user.getPasswd());
        logger.info("encode >>> {}", SHA256Util.getEncrypt(before_passwd, user.getSalt()));

        if(user.getPasswd().equals(SHA256Util.getEncrypt(before_passwd, user.getSalt()))){
            return true;
        }else{
            return false;
        }
 
        
	}


}
