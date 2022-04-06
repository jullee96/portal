package com.hamonize.portal.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hamonize.portal.company.Company;
import com.hamonize.portal.company.CompanyRepository;
import com.hamonize.portal.company.CompanyService;
import com.hamonize.portal.file.FileRepository;
import com.hamonize.portal.file.FileVO;
import com.hamonize.portal.mail.MailSendService;
import com.hamonize.portal.product.ProductRepository;
import com.hamonize.portal.subscribe.Subscribe;
import com.hamonize.portal.subscribe.SubscribeRepostory;
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

    @Autowired
    MailSendService mss;

    @Autowired
    SubscribeRepostory sr;

    @Autowired
    ProductRepository pr;


    @RequestMapping("/detail")
    public String signup(HttpSession session, User vo, Model model) {
       SecurityUser user = (SecurityUser) session.getAttribute("userSession");
       Company newComVo = cr.findByUserid(user.getUserid());
       List<Subscribe> svo = sr.findAllByUserid(user.getUserid());

       model.addAttribute("companyInfo", newComVo);
       
       if(!svo.isEmpty()){
        svo.get(0).setProduct( pr.findByPdid(svo.get(0).getPdid()));
        model.addAttribute("subscribeInfo", svo.get(0));
 
       }

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
        
        if(!"".equals(vo.getBeforepasswd()) && vo.getBeforepasswd() != null){
            us.update(vo);
            cs.update(cvo);

            // update session user
            User newVo = ur.findByUserid(vo.getUserid()).get();
            SecurityUser updateUser = new SecurityUser(newVo);
        
            session.removeAttribute("userSession");
            session.setAttribute("userSession", updateUser);

        }else{ 
            logger.info("변경사항 없음");
        }

        return "redirect:/user/detail";
	}


    @RequestMapping("/images")
    public void imgView(HttpSession session, HttpServletResponse response, Model model)  throws IOException {
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        FileVO file = fr.findByUseridAndKeytype(user.getUserid(), "img");
         
        try {
            StringBuilder sb =new StringBuilder();
            
            if(file.getFilepath().contains("://")){
                sb = new StringBuilder(file.getFilepath());
            
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
    public Boolean passwdChk(@RequestParam("beforepasswd") String beforepasswd , HttpSession session, Model model)  throws IOException {
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
       
        logger.info("get beforepasswd {}",beforepasswd);
        
        logger.info("session {}", user.getPasswd());
        logger.info("encode >>> {}", SHA256Util.getEncrypt(beforepasswd, user.getSalt()));

        if(user.getPasswd().equals(SHA256Util.getEncrypt(beforepasswd, user.getSalt()))){
            return true;
        }else{
            return false;
        }
 	}


    @RequestMapping("/resign")
    @ResponseBody
    public String sendResignMessage(User vo) {
        String ret="f";
        try {
            ret = mss.sendResignConfirmMail(vo.getEmail(),vo.getUserid());
            return ret;
        } catch (UnsupportedEncodingException e) {
            ret = "f";
        
            return ret;
        }
    
    }
    
    

    
}
