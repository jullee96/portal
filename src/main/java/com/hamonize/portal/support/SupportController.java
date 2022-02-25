package com.hamonize.portal.support;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.hamonize.portal.file.FileRepository;
import com.hamonize.portal.file.FileVO;
import com.hamonize.portal.user.SecurityUser;
import com.hamonize.portal.user.User;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/support")
public class SupportController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FileRepository fr;

    @Autowired
    SupportRepository sr;

    @RequestMapping("/list")
    public String supportList(HttpSession session, Support vo, Model model) {
        logger.info("\n\n\n <<< list >> ");
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");

        List<Support> list = sr.findAll();
        model.addAttribute("list", list);
        return "/support/list";
	}

    @GetMapping("/apply")
    public String supportCreate(Support vo, HttpSession session, Model model) {
        
        logger.info("\n\n\n <<< 1:1문의 상세 >> ");
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
      
        return "/support/view";
	}


    @GetMapping("/view")
    public String supportView(Support vo, HttpSession session, Model model) {
        logger.info("seq : ",vo.getSeq());

        logger.info("\n\n\n <<< 1:1문의 상세 >> ");
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
            logger.info("seq >> {}", vo.getSeq());
            Support edit = sr.findBySeq(vo.getSeq());
                
            model.addAttribute("edit", edit);
    
        return "/support/view";
	}

    @RequestMapping("/save")
    @ResponseBody
    public Integer save(HttpSession session, Support vo ) {
        logger.info("\n\n\n <<< support 저장 >> ");
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        vo.setUserid(user.getUserid());
        Support ret = new Support();
        vo.setInsdate(LocalDateTime.now());
   
        ret = sr.save(vo);
      
        return ret.getSeq();
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session, User vo) {
        
        logger.info("\n\n\n <<< doamin 결제 페이지 >> ");
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");


        return "redirect:/support/list";
	}

    @RequestMapping("/getImageUrl")
    public void imgView(@RequestParam("seq") Integer seq, HttpSession session, HttpServletResponse response, Model model)  throws IOException {
        
       SecurityUser user = (SecurityUser) session.getAttribute("userSession");
       logger.info("file path : {}", seq);

       FileVO file = fr.findByUseridAndSeq(user.getUserid(), seq );
        
        logger.info("file path : {}", file.getFilepath());
        
        StringBuilder sb = new StringBuilder("file:"+ file.getFilepath());
        URL fileUrl = new URL(sb.toString());
        logger.info("fileUrl >> {}",fileUrl);

        IOUtils.copy(fileUrl.openStream(), response.getOutputStream());
	}

}
