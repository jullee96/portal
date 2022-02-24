package com.hamonize.portal.support;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hamonize.portal.file.FileRepository;
import com.hamonize.portal.file.FileVO;
import com.hamonize.portal.user.SecurityUser;
import com.hamonize.portal.user.User;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/support")
public class SupportController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FileRepository fr;

    @RequestMapping("/list")
    public String supportList(HttpSession session, User vo) {
        
        logger.info("\n\n\n <<< doamin 결제 페이지 >> ");
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");


        return "/support/list";
	}

    @RequestMapping("/view")
    public String supportView(HttpSession session, User vo) {
        
        logger.info("\n\n\n <<< doamin 결제 페이지 >> ");
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");


        return "/support/view";
	}

    @RequestMapping("/save")
    public String save(HttpSession session, Support vo ) {
        logger.info("\n\n\n <<< doamin 결제 페이지 >> ");
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");


        return "redirect:/support/list";
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
